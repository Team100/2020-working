{
    # Class names of motor controllers used.
    # Options:
    # 'WPI_TalonSRX'
    # 'WPI_TalonFX' (for Falcon 500 motors)
    # 'WPI_VictorSPX'
    # Note: The first motor on each side should always be a Talon SRX/FX, as the
    # VictorSPX does not support encoder connections
    "rightControllerTypes": ["WPI_TalonSRX"],
    "leftControllerTypes": ["WPI_TalonSRX"],
    # Ports for the left-side motors
    "leftMotorPorts": [0],
    # Ports for the right-side motors
    "rightMotorPorts": [15],
    # Inversions for the left-side motors
    "leftMotorsInverted": [True],
    # Inversions for the right side motors
    "rightMotorsInverted": [True],
    # Wheel diameter (in units of your choice - will dictate units of analysis)
    "wheelDiameter": 0.333,
    # If your robot has only one encoder, remove all of the right encoder fields
    # Encoder pulses-per-revolution (*NOT* cycles per revolution!)
    # This value should be the pulses per revolution *of the wheels*, and so
    # should take into account gearing between the encoder and the wheels
    "encoderPPR": 3072,
    # Whether the left encoder is inverted
    "leftEncoderInverted": False,
    # Whether the right encoder is inverted:
    "rightEncoderInverted": True,
    # Your gyro type (one of "NavX", "Pigeon", "ADXRS450", "AnalogGyro", or "None")
    "gyroType": "ADXRS450",
    # Whatever you put into the constructor of your gyro
    # Could be:
    # "SPI.Port.kMXP" (MXP SPI port for NavX or ADXRS450),
    # "I2C.Port.kOnboard" (Onboard I2C port for NavX)
    # "0" (Pigeon CAN ID or AnalogGyro channel),
    # "new WPI_TalonSRX(3)" (Pigeon on a Talon SRX),
    # "leftSlave" (Pigeon on the left slave Talon SRX/FX),
    # "" (NavX using default SPI, ADXRS450 using onboard CS0, or no gyro)
    "gyroPort": "",
}







