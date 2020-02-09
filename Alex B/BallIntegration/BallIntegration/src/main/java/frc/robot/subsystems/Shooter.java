/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonFX;

public class Shooter extends CommandBase {

  public FRCTalonFX master;
  public FRCTalonFX follower;

  public static enum ShooterState{
    SHOOTING,
    RECOVERING,
    STOPPED
  }

  public ShooterState shooterState;
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    // Use addRequirements() here to declare subsystem dependencies.
    master = new FRCTalonFX.FRCTalonFXBuilder()
    .withCanID(Constants.ShooterConstants.ShooterMotors.ShooterMaster.CAN_ID)
    .withInverted(Constants.ShooterConstants.ShooterMotors.ShooterMaster.INVERT)
    .withFeedbackPort(Constants.ShooterConstants.ShooterMotors.ShooterMaster.FEEDBACK_PORT)
    .withSensorPhase(Constants.ShooterConstants.ShooterMotors.ShooterMaster.SENSOR_PHASE)
    .withTimeout(Constants.ShooterConstants.ShooterMotors.ShooterMaster.TIMEOUT)
    .withCurrentLimitEnabled(Constants.ShooterConstants.ShooterMotors.ShooterMaster.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.ShooterConstants.ShooterMotors.ShooterMaster.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.ShooterConstants.ShooterMotors.ShooterMaster.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.ShooterConstants.ShooterMotors.ShooterMaster.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.ShooterConstants.ShooterMotors.ShooterMaster.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.ShooterConstants.ShooterMotors.ShooterMaster.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.ShooterConstants.ShooterMotors.ShooterMaster.PEAK_OUTPUT_REVERSE)
    .build();

    follower = new FRCTalonFX.FRCTalonFXBuilder()
    .withCanID(Constants.ShooterConstants.ShooterMotors.ShooterFollower.CAN_ID)
    .withInverted(Constants.ShooterConstants.ShooterMotors.ShooterFollower.INVERT)
    .withFeedbackPort(Constants.ShooterConstants.ShooterMotors.ShooterFollower.FEEDBACK_PORT)
    .withSensorPhase(Constants.ShooterConstants.ShooterMotors.ShooterFollower.SENSOR_PHASE)
    .withTimeout(Constants.ShooterConstants.ShooterMotors.ShooterFollower.TIMEOUT)
    .withCurrentLimitEnabled(Constants.ShooterConstants.ShooterMotors.ShooterFollower.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.ShooterConstants.ShooterMotors.ShooterFollower.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.ShooterConstants.ShooterMotors.ShooterFollower.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.ShooterConstants.ShooterMotors.ShooterFollower.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.ShooterConstants.ShooterMotors.ShooterFollower.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.ShooterConstants.ShooterMotors.ShooterFollower.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.ShooterConstants.ShooterMotors.ShooterFollower.PEAK_OUTPUT_REVERSE)
    .build();


    follower.follow(master);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
