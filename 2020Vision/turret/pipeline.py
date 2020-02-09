import cv2


# Find and filter contours with OpenCV
def process(frame, config):
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    hsvFiltered = cv2.inRange(hsv,
                              (config.hsv.hue[0], config.hsv.saturation[0], config.hsv.value[0]),
                              (config.hsv.hue[1], config.hsv.saturation[1], config.hsv.value[1]))

    contours, _ = cv2.findContours(hsvFiltered, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    return list(filter(lambda c: cv2.contourArea(c) > config.filters.min_area, contours))
