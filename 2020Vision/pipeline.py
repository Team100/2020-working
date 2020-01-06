import cv2


# Find and filter contours with OpenCV
def process(frame, config):
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    hsvFiltered = cv2.inRange(hsv,
                              (config.hsv.hue[0], config.hsv.sat[0], config.hsv.val[0]),
                              (config.hsv.hue[1], config.hsv.sat[1], config.hsv.val[1]))

    contours, _ = cv2.findContours(hsvFiltered, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    return list(filter(lambda c: cv2.contourArea(c) > config.filters.min_area, contours))
