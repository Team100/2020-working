# Ball Detection
This contains the ball detection code for the 2020 season.
We use the trained model `edgetpu.tflite` on the [Google Coral USB Accelerator](https://www.coral.ai/products/accelerator/) to identify the power cells in each video frame.
The model was trained using the [`powercell-detection`](https://hub.docker.com/repository/docker/akrantz/powercell-detection) image.
