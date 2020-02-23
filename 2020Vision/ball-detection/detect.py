import cv2
import json
import socket
import sys

from messages_pb2 import PowerCells
import util

config = json.load(open("config.json"))

camera = cv2.VideoCapture(config["camera"]["device"])
if not camera.isOpened():
    print("Invalid device id for camera")
    sys.exit(1)

if config["socket"]["enabled"]:
    robot_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    robot_socket.connect((config["socket"]["host"], config["socket"]["port"]))

labels = util.load_labels(config["labels"]) if config["labels"] else {}
interpreter = util.make_interpreter(config["models"], config["runner"])
interpreter.allocate_tensors()

try:
    while True:
        _, frame = camera.read()
        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

        scale = util.set_input(interpreter, rgb)
        interpreter.invoke()
        objs = util.get_output(interpreter, config["threshold"], scale)

        if config["output"] == "text":
            print(f"{len(objs)} power cells with [", end="")
            for i, obj in enumerate(objs):
                print(round(obj.score, 2), end=", " if i != len(objs) - 1 else "")
            print("] confidence")

        if config["output"] == "video":
            for obj in objs:
                cv2.putText(frame, f"{labels.get(obj.id, obj.id)}: {obj.score:.2f}", (obj.bbox.xmin, obj.bbox.ymin - 5),
                            cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 255))
                cv2.rectangle(frame, (obj.bbox.xmin, obj.bbox.ymin), (obj.bbox.xmax, obj.bbox.ymax), (0, 0, 255), 2)
                cv2.circle(frame, (int(obj.bbox.xmin + (obj.bbox.xmax - obj.bbox.xmin) / 2),
                                   int(obj.bbox.ymin + (obj.bbox.ymax - obj.bbox.ymin) / 2)), 2, (0, 0, 255), 2)

            cv2.imshow("Video", frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                cv2.destroyAllWindows()
                camera.release()
                break

        if config["socket"]["enabled"]:
            msg = PowerCells(found=len(objs) != 0)
            for obj in objs:
                msg.boxes.append(obj.bbox.message)
                msg.angles.append(
                    util.calculate_angle(obj.bbox, config["camera"]["image_width"], config["camera"]["horizontal_fov"]))
            encoded = msg.SerializeToString()
            try:
                robot_socket.sendall(bytes([1, len(encoded)]) + encoded)
            except ConnectionRefusedError:
                pass

except KeyboardInterrupt:
    cv2.destroyAllWindows()
    camera.release()
