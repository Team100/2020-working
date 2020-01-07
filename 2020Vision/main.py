import calculations
from collections import namedtuple
from copy import deepcopy
import cv2
from json import load as parse_json, JSONDecodeError
from message_pb2 import Message
from os import system as execute
import pipeline
import socket
from watchdog.observers import Observer
from watchdog.events import PatternMatchingEventHandler

config = parse_json(open("config.json"), object_hook=lambda d: namedtuple("Config", d.keys())(*d.values()))


# Create live config updater
class FileWatcher(PatternMatchingEventHandler):
    def __init__(self):
        PatternMatchingEventHandler.__init__(self, patterns=["config.json"], ignore_directories=True, case_sensitive=False)

    def on_modified(self, event):
        global config
        temp_cfg = deepcopy(config)
        try:
            config = parse_json(open("config.json"), object_hook=lambda d: namedtuple("Config", d.keys())(*d.values()))
        except JSONDecodeError:
            config = temp_cfg


def debug_draw(f):
    if config.debug:
        f()


# Start file watcher
observer = Observer()
observer.schedule(FileWatcher(), path=".", recursive=False)
observer.start()

# Configure camera streamer
execute(f"./set-exposure.sh {config.camera.port}")
camera = cv2.VideoCapture(config.camera.port)

# Connect to socket transmission socket
robot_socket = None
if config.socket.enabled:
    robot_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    robot_socket.connect((config.socket.host, config.socket.port))

# Read the image for pattern matching
matching_contour = pipeline.process(cv2.imread("capture.png"), config)[0]

# Allow for identification/tracking between frames
last_bounding_area = -1
frame_tracking_skip_count = 0

try:
    while True:
        _, frame = camera.read()

        # Determine contours similar to template
        contours = pipeline.process(frame, config)
        contours = list(filter(lambda c: cv2.matchShapes(matching_contour, c, cv2.CONTOURS_MATCH_I3, 0) < 3, contours))
        debug_draw(lambda: cv2.drawContours(frame, contours, -1, (255, 0, 0), 2))

        # Ensure contours exist
        if len(contours) == 0:
            frame_tracking_skip_count += 1
            if frame_tracking_skip_count > 15:
                last_bounding_area = -1
        else:
            frame_tracking_skip_count = 0

            contour = None

            # Determine most similar contour to previous frame
            if last_bounding_area != -1:
                contours.sort(key=lambda c: abs(cv2.contourArea(c) - last_bounding_area) / last_bounding_area)
                contour = contours[0]
            else:
                contours.sort(key=cv2.contourArea)
                contour = contours[0]

            last_bounding_area = cv2.contourArea(contour)

            # Get centers
            m = cv2.moments(contour)
            cx = int(m["m10"] / m["m00"])
            cy = int(m["m01"] / m["m00"])
            debug_draw(lambda: cv2.circle(frame, (cx, cy), 5, (255, 255, 255)))

            # Calculate horizontal angle to target
            h_angle = calculations.horizontal_angle(config, cx)
            debug_draw(lambda: cv2.putText(frame, f"Horizontal Angle: {h_angle}", (25, 400), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 1))

            # Calculate vertical angle to target
            v_angle = calculations.vertical_angle(config, cy)
            debug_draw(lambda: cv2.putText(frame, f"Vertical Angle: {v_angle}", (25, 415), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 1))

            # Retrieve distance to target
            distance = calculations.distance(config, 1)
            debug_draw(lambda: cv2.putText(frame, f"Distance: {distance}", (25, 430), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 1))

            # Serialize and send data to robot
            if config.socket.enabled and robot_socket:
                msg = Message()
                msg.v_angle = v_angle
                msg.h_angle = h_angle
                msg.distance = distance
                robot_socket.sendall(msg.SerializeToString())

        # Display image frame for debugging
        if config.debug:
            cv2.imshow("Frame", frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                cv2.destroyAllWindows()
                break

except KeyboardInterrupt:
    # Tear stuff down
    camera.release()
    if robot_socket:
        robot_socket.close()
    observer.stop()
    observer.join()
