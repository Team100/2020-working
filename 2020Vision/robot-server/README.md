# Robot Server
This allows for the robot to receive and operate on data from the co-processor.
We use [Protocol Buffers](https://developers.google.com/protocol-buffers) to efficiently transmit the location data to the RoboRIO.
We opted to use them over JSON or just sending text because they are smaller and can be serialized/deserialized faster.
The message structure is defined in [`messages.proto`](./messages.proto).
The message is sent over a raw UDP socket to ensure minimum latency which allows for either the client or server to fail and have the other continue running.

## Language Bindings
The message types can be generated for Python and Java with the following command:
```shell script
protoc --java_out=. --python_out=. message.proto
```
