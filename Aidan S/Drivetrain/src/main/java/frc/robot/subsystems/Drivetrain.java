/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private TalonFX leftMaster, leftFollower, rightMaster, rightFollower;
  private double leftOutput, rightOutput;

  /**
   * Creates a new ExampleSubsystem.
   */
  public Drivetrain() {
    leftMaster = new TalonFX(Constants.Drivetrain.LEFT_MASTER_CANID);
    leftFollower = new TalonFX(Constants.Drivetrain.LEFT_FOLLOWER_CANID);
    rightMaster = new TalonFX(Constants.Drivetrain.RIGHT_MASTER_CANID);
    rightFollower = new TalonFX(Constants.Drivetrain.RIGHT_FOLLOWER_CANID);

    leftFollower.follow(leftMaster);
    rightFollower.follow(rightMaster);

    configureMotorControllers();
  }

  private void configureMotorControllers() {
    TalonFX[] motorControllers = {leftMaster, leftFollower, rightMaster, rightFollower};
    for(TalonFX mc: motorControllers) {
      mc.configFactoryDefault();
      mc.configPeakOutputForward(Constants.Drivetrain.PEAK_OUTPUT);
      mc.configPeakOutputReverse(-Constants.Drivetrain.PEAK_OUTPUT);
      mc.setNeutralMode(NeutralMode.Coast);
      // mc.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration());
    }
  }

  public void set(double left, double right) {
    leftOutput = left;
    rightOutput = right;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    leftMaster.set(ControlMode.PercentOutput, leftOutput*Constants.Drivetrain.PEAK_OUTPUT);
    rightMaster.set(ControlMode.PercentOutput, -rightOutput*Constants.Drivetrain.PEAK_OUTPUT);
  }
}
