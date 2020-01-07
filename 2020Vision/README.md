# Vision
This contains the vision code for the 2020 season.
We use an initial image ([`capture.png`](./capture.png)) as a pattern to match against each frame.
This is done to ensure that the contour we detect is actually the target we intend to do calculations on.
For angle calculations, we calculate the horizontal number of degrees per pixel along with the center offset.
For distance calculations, we use the Intel RealSense which provides accurate distance up to 18m.

## Protocol Buffers
To transport the data between the coprocessor and RoboRIO, we use a raw TCP socket and [protocol buffers](https://developers.google.com/protocol-buffers).
We opted to use protocol buffers over JSON or just sending text because they are smaller and can be serialized/deserialized faster.
The message definition can be found in [`message.proto`](./message.proto), and the classes for Python and Java can be generated with the following command:
```shell script
protoc --java_out=. --python_out=. message.proto
```

## Configuration
The configuration is done in [`config.json`](./config.json) which provides filtering and camera configuration.
To get the horizontal and vertical FOV, take the diagonal FOV and aspect ratio from the camera's datasheet and approximate each with [this site](https://vrguy.blogspot.com/2013/04/converting-diagonal-field-of-view-and.html).

| Key | Type | Description |
|-----------------------|------------------|-----------------------------------------------------|
| camera.port | integer | Which port the camera to use is on |
| camera.width | integer | The width in pixels of the image |
| camera.height | integer | The height in pixels of the image |
| camera.fov.horizontal | float | The horizontal FOV of the image |
| camera.fov.vertical | float | The vertical FOV of the image |
| debug | boolean | Whether to enable debug mode or not |
| hsv.sat | array of integers | The saturation upper and lower bounds for filtering |
| hsv.hue | array of integers | The hue upper and lower bounds for filtering |
| hsv.val | array of integers | The value upper and lower bounds for filtering |
| filters.min_area | integer | The minimum area of a contour to consider |
| socket.enabled | boolean | Whether to send the data or not |
| socket.host | string | Host of the UDP server to connect to |
| socket.port | integer | Port on the host to connect to |

## To Do
- Replace `capture.png` with an image that only has the green reflection from the tape
