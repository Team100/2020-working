import math

TARGET_HEIGHT = 17


# Get the horizontal angle to a point rounded to 4 decimal places
def horizontal_angle(config, x):
    return round((x - (config.camera.width/2) - 0.5) * (config.camera.fov.horizontal / config.camera.width), 4)


# Get the vertical angle to a point rounded to 4 decimal places
def vertical_angle(config, y):
    return round((y - (config.camera.height/2) - 0.5) * (config.camera.fov.vertical / config.camera.height), 4)


# Get the distance to the target
# This is going to be replaced by the Intel RealSense
def distance(config, y_from_horizontal):
    # Convert to radians
    rad_horizontal = y_from_horizontal / 6 / 57.3
    return round((config.camera.height / math.tan(rad_horizontal)), 4)
