import math

TARGET_HEIGHT = 17


# Get the horizontal angle to a point rounded to 4 decimal places
def horizontal_angle(config, x):
    return round((x - (config.camera.width/2) - 0.5) * (config.camera.fov.horizontal / config.camera.width), 4)


# Get the vertical angle to a point rounded to 4 decimal places
def vertical_angle(config, y):
    return -round((y - (config.camera.height/2) - 0.5) * (config.camera.fov.vertical / config.camera.height), 4)


# Get the distance to a point
def distance(depth, x, y):
    return depth.get_distance(x, y)


# Get average of array
def average(array):
    s = 0
    for i in array:
        s += i
    return s / len(array)
