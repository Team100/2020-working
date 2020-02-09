# Object Detection Training Image
This repository contains the necessary files to build the Docker image for training a neural network to detect power cells.
This uses transfer learning to re-train an optimized neural network, specifically SSD MobileNet v1/v2.

## Using the Image
To use the image, see the [Docker Hub](https://hub.docker.com/r/akrantz/powercell-detection) page.

## Building the Image
To build the image, clone this repository and change into this directory.
Then, with Docker installed, run:
```
docker build -t <some_name>:<some_tag> .
```
It will take approximately 10mins to build, depending on the speed of your computer.
