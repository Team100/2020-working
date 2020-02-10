/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonFX;

public class Drivetrain extends SubsystemBase {
  private FRCTalonFX leftMaster, leftFollower, rightMaster, rightFollower;
  private double leftOutput, rightOutput;

  /**
   * Creates a new ExampleSubsystem.
   */
  public Drivetrain() {
    leftMaster = new FRCTalonFX.FRCTalonFXBuilder().withCanID(Constants.Drivetrain.LEFT_MASTER_CANID).build();
    leftFollower = new FRCTalonFX.FRCTalonFXBuilder().withCanID(Constants.Drivetrain.LEFT_FOLLOWER_CANID).build();
    rightMaster = new FRCTalonFX.FRCTalonFXBuilder().withCanID(Constants.Drivetrain.RIGHT_MASTER_CANID).build();
    rightFollower = new FRCTalonFX.FRCTalonFXBuilder().withCanID(Constants.Drivetrain.RIGHT_FOLLOWER_CANID).build();

    leftFollower.motor.follow(leftMaster.motor);
    rightFollower.motor.follow(rightMaster.motor);

    configureMotorControllers();
  }

  private void configureMotorControllers() {
    FRCTalonFX[] motorControllers = {leftMaster, leftFollower, rightMaster, rightFollower};
    for(FRCTalonFX mc: motorControllers) {
      mc.reset();
      mc.setPeakOutputForward(Constants.Drivetrain.PEAK_OUTPUT);
      mc.setPeakOutputReverse(-Constants.Drivetrain.PEAK_OUTPUT);
      mc.setNeutralMode(NeutralMode.Coast);
    }
  }

  public void set(double left, double right) {
    leftOutput = left;
    rightOutput = right;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    leftMaster.drivePercentOutput(leftOutput*Constants.Drivetrain.PEAK_OUTPUT);
    rightMaster.drivePercentOutput(-rightOutput*Constants.Drivetrain.PEAK_OUTPUT);
  }
}
