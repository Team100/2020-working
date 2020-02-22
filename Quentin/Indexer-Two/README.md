# 2020-roboRIO

## Intro
This code is for internal development of the 2020 Robot Code.

## Structure
The code is based on the 2020 *new* Command Subsystem format.

### Subsystems

Our subsystems are responsible for keeping track of their current motion state. You can see a complete list of the subsystems in `./src/main/java/frc/robot/subsystems`. They also have commands in `./src/main/java/frc/robot/commands`. If the subsystem is part of the **supersystem**, their commands will be in `./src/main/java/frc/robot/commands/supersystem`.

#### Supersystem Subsystems
These subsystems are very closely related to each other. It consists of the:
- Indexer
- Intake
- Shooter
- Turret

These subsystems should report any state elements that affect other subsystems to `frc.robot.GlobalManager.SupersystemManager`, which is reponsible for state orchestration that affects other subsystems. This is a replacement of poorly-organized global state management in the 2019 code (which for the most part was tracked in the `Elevator` subsystem).

All other states (ones that do not need to be reported globally) should be reported to the subsytem class.


### FRCLib

When possible, use the FRCLib motor controllers. They consist of `FRCTalonSRX`, `FRCVictorSPX`, and `FRCTalonFX`. They can be instantiated using a Builder like follows:

```java
indexerStageOne = new FRCTalonSRX.FRCTalonSRXBuilder()
    .withCanID(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.CAN_ID)
    .withInverted(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.INVERT)
    .withFeedbackPort(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.FEEDBACK_PORT)
    .withSensorPhase(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.SENSOR_PHASE)
    .withTimeout(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.TIMEOUT)
    .withCurrentLimitEnabled(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.PEAK_OUTPUT_REVERSE)
    .build();
```