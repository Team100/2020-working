import argparse
import cv2
import numpy as np
import sys

from PIL import Image, ImageDraw

import detect


def set_input(interpreter, image):
    width, height = detect.input_size(interpreter)
    h, w, _ = image.shape
    scale = min(width / w, height / h)
    w, h = int(w * scale), int(h * scale)
    resized = cv2.resize(image, (w, h))

    tensor = detect.input_tensor(interpreter)
    tensor.fill(0)
    _, _, channel = tensor.shape
    tensor[:h, :w] = np.reshape(resized, (h, w, channel))
    return scale, scale


parser = argparse.ArgumentParser(formatter_class=argparse.ArgumentDefaultsHelpFormatter)
parser.add_argument("-m", "--model", required=True, help="File path of .tflite file")
parser.add_argument("-d", "--device", default=0, help="Camera id to read from", type=int)
parser.add_argument("-l", "--labels", help="File path of labels file")
parser.add_argument("-t", "--threshold", type=float, default=0.4, help="Score threshold for detected objects")
parser.add_argument("-o", "--output", type=str, default="none", choices=["none", "video", "text"])
args = parser.parse_args()

camera = cv2.VideoCapture(args.device)
if not camera.isOpened():
    print("Invalid device id for camera")
    sys.exit(1)

labels = detect.load_labels(args.labels) if args.labels else {}
interpreter = detect.make_interpreter(args.model)
interpreter.allocate_tensors()

try:
    while True:
        _, frame = camera.read()
        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

        scale = set_input(interpreter, rgb)
        interpreter.invoke()
        objs = detect.get_output(interpreter, args.threshold, scale)

        if args.output == "text":
            print(f"{len(objs)} power cells with [", end="")
            for i, obj in enumerate(objs):
                print(round(obj.score, 2), end=", " if i != len(objs)-1 else "")
            print("] confidence")
        
        if args.output == "video":
            for obj in objs:
                cv2.putText(frame, f"{labels.get(obj.id, obj.id)}: {obj.score:.2f}", (obj.bbox.xmin, obj.bbox.ymin - 5), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 255))
                cv2.rectangle(frame, (obj.bbox.xmin, obj.bbox.ymin), (obj.bbox.xmax, obj.bbox.ymax), (0, 0, 255), 2)

            cv2.imshow("Video", frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                cv2.destroyAllWindows()
                camera.release()
                break
except KeyboardInterrupt:
    cv2.destroyAllWindows()
    camera.release()
