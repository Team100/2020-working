{
    # Ports for the left-side motors
    "leftMotorPorts": [1],
    # Ports for the right-side motors
    "rightMotorPorts": [15],
    # NOTE: Inversions of the slaves (i.e. any motor *after* the first on
    # each side of the drive) are *with respect to their master*.  This is
    # different from the other poject types!
    # Inversions for the left-side motors
    "leftMotorsInverted": [False],
    # Inversions for the right side motors
    "rightMotorsInverted": [False],
    # The total gear reduction between the motor and the wheels, expressed as
    # a fraction [motor turns]/[wheel turns]
    "gearing": 1/6,
    # Wheel diameter (in units of your choice - will dictate units of analysis)
    "wheelDiameter": 0.333,
    # Your gyro type (one of "NavX", "Pigeon", "ADXRS450", "AnalogGyro", or "None")
    "gyroType": "ADXRS450",
    # Whatever you put into the constructor of your gyro
    # Could be:
    # "SPI.Port.kMXP" (MXP SPI port for NavX or ADXRS450),
    # "I2C.Port.kOnboard" (Onboard I2C port for NavX)
    # "0" (Pigeon CAN ID or AnalogGyro channel),
    # "new WPI_TalonSRX(3)" (Pigeon on a Talon SRX),
    # "" (NavX using default SPI, ADXRS450 using onboard CS0, or no gyro)
    "gyroPort": "",
}

