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

public class Turret extends SubsystemBase {

  public FRCTalonSRX turretMotor;

  public static enum ActionState{
    MOVING,
    STOPPED
  }
  public ActionState actionState;
  /**
   * Creates a new Turret.
   */
  public Turret() {
    turretMotor = new FRCTalonSRX.FRCTalonSRXBuilder()
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
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
