# 2020 Vision
This contains the visio code for the 2020 season. 
There is the target tracking code and ball detection image.

## Robot Server
The glue code that allows for the RoboRIO to operate on the data from the co-processor.
To read more, see the [`robot-server`](./robot-server) folder.

## Turret Tracking
The turret tracking code identifies the U-shaped retroreflective tape that borders the bottom part of the target.
To read more, see the [`turret`](./turret) folder.

## Ball Detection
The ball detection code uses the trained models to identify the power cells.
To read more, see the [`ball-detection`](./ball-detection) folder.

## Ball Detection Training
The ball detection image builds the Docker image to re-train SSD MobileNet v1 or v2.
To read more, see the [`ball-detection-training`](./ball-detection-training) folder.