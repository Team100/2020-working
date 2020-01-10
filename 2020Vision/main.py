import calculations
from collections import namedtuple
from copy import deepcopy
import cv2
from json import load as parse_json, JSONDecodeError
from message_pb2 import Message
import numpy as np
import pipeline
import pyrealsense2 as rs
import socket
import subprocess
import sys
from watchdog.observers import Observer
from watchdog.events import PatternMatchingEventHandler

config = parse_json(open("config.json"), object_hook=lambda d: namedtuple("Config", d.keys())(*d.values()))


def set_exposure():
    devices = subprocess.run("ls /dev/video*", stdout=subprocess.PIPE, shell=True).stdout.decode("utf-8").strip().split("\n")
    realsense = []
    for device in devices:
        properties = {property_string.split("=")[0]: property_string.split("=")[1] for property_string in subprocess.run(f"udevadm info -n {device} -q property", stdout=subprocess.PIPE, shell=True).stdout.decode("utf-8").strip().split("\n")}
        if properties["ID_VENDOR_ID"] == "8086" and properties["MAJOR"] == "81":
            realsense.append(device)

    if len(realsense) == 0:
        print("No RealSense camera connected! Please connect one and restart.")
        sys.exit(1)

    subprocess.run(f"v4l2-ctl --device /dev/video{realsense[-2][-1]} --set-ctrl=exposure_auto=1", shell=True, check=True)
    subprocess.run(f"v4l2-ctl --device /dev/video{realsense[-2][-1]} --set-ctrl=exposure_absolute={config.camera.exposure}", shell=True, check=True)


# Create live config updater
class FileWatcher(PatternMatchingEventHandler):
    def __init__(self):
        PatternMatchingEventHandler.__init__(self, patterns=["*.json"], ignore_directories=True, case_sensitive=False)

    def on_modified(self, event):
        global config
        temp_cfg = deepcopy(config)
        try:
            config = parse_json(open("config.json"), object_hook=lambda d: namedtuple("Config", d.keys())(*d.values()))
        except JSONDecodeError:
            config = temp_cfg

        set_exposure()


def debug_draw(f):
    if config.debug:
        f()


# Start file watcher
observer = Observer()
observer.schedule(FileWatcher(), path=".", recursive=False)
observer.start()

# Configure camera streamer
set_exposure()
camera_config = rs.config()
camera_config.enable_stream(rs.stream.depth, config.camera.width, config.camera.height, rs.format.z16, config.camera.frame_rate)
camera_config.enable_stream(rs.stream.color, config.camera.width, config.camera.height, rs.format.bgr8, config.camera.frame_rate)
camera = rs.pipeline()
camera.start(camera_config)

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

# Rolling average for distance
distances = []

try:
    while True:
        # Get depth and color frames
        frames = camera.wait_for_frames()
        depth = frames.get_depth_frame()
        color = frames.get_color_frame()
        if not depth or not color:
            continue

        # Convert to numpy array
        frame = np.asanyarray(color.get_data())

        # Determine contours similar to template
        contours = pipeline.process(frame, config)
        contours = list(filter(lambda c: cv2.matchShapes(matching_contour, c, cv2.CONTOURS_MATCH_I3, 0) < config.filters.hu_distance, contours))
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
            debug_draw(lambda: cv2.drawContours(frame, [contour], -1, (0, 255, 0), 2))

            # Get centers
            m = cv2.moments(contour)
            cx = int(m["m10"] / m["m00"])
            cy = int(m["m01"] / m["m00"])
            debug_draw(lambda: cv2.line(frame, (0, config.camera.height//2), (config.camera.width, config.camera.height//2), (255, 255, 255)))
            debug_draw(lambda: cv2.line(frame, (config.camera.width//2, 0), (config.camera.width//2, config.camera.height), (255, 255, 255)))
            debug_draw(lambda: cv2.circle(frame, (cx, cy), 5, (255, 255, 255)))

            # Calculate horizontal angle to target
            h_angle = calculations.horizontal_angle(config, cx)
            debug_draw(lambda: cv2.putText(frame, f"Horizontal Angle: {h_angle}", (25, 400), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 1))

            # Calculate vertical angle to target
            v_angle = calculations.vertical_angle(config, cy)
            debug_draw(lambda: cv2.putText(frame, f"Vertical Angle: {v_angle}", (25, 420), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 1))

            # Calculate point for distance retrieval
            bottom = contour[contour[:, :, 1].argmax()][0][1]

            # Retrieve distance to target
            while len(distances) > config.filters.rolling_average_frames:
                distances.pop(0)
            distances.append(calculations.distance(depth, cx, bottom))
            distance = calculations.average(distances)
            debug_draw(lambda: cv2.putText(frame, f"Distance: {distance}", (25, 440), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 1))

            # Serialize and send data to robot
            if config.socket.enabled and robot_socket:
                msg = Message()
                msg.v_angle = v_angle
                msg.h_angle = h_angle
                msg.distance = distance
                encoded = msg.SerializeToString()
                try:
                    robot_socket.sendall(bytes([len(encoded)]) + encoded)
                except ConnectionRefusedError:
                    pass

        # Display image frame for debugging
        if config.debug:
            cv2.imshow("Frame", frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                cv2.destroyAllWindows()
                break

except KeyboardInterrupt:
    pass
finally:
    # Tear stuff down
    camera.stop()
    if robot_socket:
        robot_socket.close()
    observer.stop()
    observer.join()
