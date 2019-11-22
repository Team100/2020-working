/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HomeRelative extends Command {

  boolean frZero = false;
  boolean flZero = false;
  boolean blZero = false;
  boolean brZero = false;

  public HomeRelative() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.frederickTurn.set(ControlMode.Position, 0);
    Robot.drivetrain.fletcherTurn.set(ControlMode.Position, 0);
    Robot.drivetrain.blakeTurn.set(ControlMode.Position, 0);
    Robot.drivetrain.brianTurn.set(ControlMode.Position, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    frZero = (int)(Robot.drivetrain.frederickTurn.getSelectedSensorPosition()) == 0;
    flZero = (int)(Robot.drivetrain.fletcherTurn.getSelectedSensorPosition()) == 0;
    blZero = (int)(Robot.drivetrain.blakeTurn.getSelectedSensorPosition()) == 0;
    brZero = (int)(Robot.drivetrain.brianTurn.getSelectedSensorPosition()) == 0;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return flZero && frZero && blZero && brZero;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
