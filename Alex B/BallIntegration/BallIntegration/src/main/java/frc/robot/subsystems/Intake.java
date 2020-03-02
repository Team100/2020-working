/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;

public class Intake extends SubsystemBase {

  public FRCTalonSRX pivot;
  public FRCTalonSRX spin;


  public static enum ActionState{
    INTAKING,
    NOT_INTAKING
  }
  public ActionState actionState;

  public static enum LocationState{
    MOVING,
    STATIONARY
  }
  public LocationState locationState;

  public static enum ValidAngles{
    DOWN,
    PARALLEL,
    UP,
    UNCERTAIN
  }
  public ValidAngles currentAngle;

  /**
   * Creates a new Intake.
   */
  public Intake() {
    pivot = new FRCTalonSRX.FRCTalonSRXBuilder()
    .withCanID(Constants.IntakeConstants.IntakeMotors.IntakePivot.CAN_ID)
    .withInverted(Constants.IntakeConstants.IntakeMotors.IntakePivot.INVERT)
    .withFeedbackPort(Constants.IntakeConstants.IntakeMotors.IntakePivot.FEEDBACK_PORT)
    .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakePivot.SENSOR_PHASE)
    .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakePivot.TIMEOUT)
    .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakePivot.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakePivot.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakePivot.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_REVERSE)
    .build();

    spin = new FRCTalonSRX.FRCTalonSRXBuilder()
    .withCanID(Constants.IntakeConstants.IntakeMotors.IntakeSpin.CAN_ID)
    .withInverted(Constants.IntakeConstants.IntakeMotors.IntakeSpin.INVERT)
    .withFeedbackPort(Constants.IntakeConstants.IntakeMotors.IntakeSpin.FEEDBACK_PORT)
    .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakeSpin.SENSOR_PHASE)
    .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakeSpin.TIMEOUT)
    .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakeSpin.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakeSpin.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakeSpin.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.IntakeConstants.IntakeMotors.IntakeSpin.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakeSpin.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakeSpin.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakeSpin.PEAK_OUTPUT_REVERSE)
    .build();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
