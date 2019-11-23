/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class HomeAbsolute extends Command {

  boolean frZero = false;
  boolean flZero = false;
  boolean blZero = false;
  boolean brZero = false;

  public HomeAbsolute() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.frederickTurn.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 20);
    Robot.drivetrain.fletcherTurn.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 20);
    Robot.drivetrain.blakeTurn.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 20);
    Robot.drivetrain.brianTurn.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 20);

    Robot.drivetrain.fletcherTurn.setSensorPhase(false);
    Robot.drivetrain.blakeTurn.setSensorPhase(false);
    Robot.drivetrain.frederickTurn.setSensorPhase(false);
    Robot.drivetrain.brianTurn.setSensorPhase(false);

    Robot.drivetrain.frederickTurn.set(ControlMode.PercentOutput, 0.05);
    Robot.drivetrain.fletcherTurn.set(ControlMode.PercentOutput, 0.05);
    Robot.drivetrain.blakeTurn.set(ControlMode.PercentOutput, 0.05);
    Robot.drivetrain.brianTurn.set(ControlMode.PercentOutput, 0.05);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    frZero = (int)(Robot.drivetrain.frederickTurn.getSelectedSensorPosition()) - Constants.FR_TURN_ZERO <= 1;
    flZero = (int)(Robot.drivetrain.fletcherTurn.getSelectedSensorPosition()) - Constants.FL_TURN_ZERO <= 1;
    blZero = (int)(Robot.drivetrain.blakeTurn.getSelectedSensorPosition()) - Constants.BL_TURN_ZERO <= 1;
    brZero = (int)(Robot.drivetrain.brianTurn.getSelectedSensorPosition()) - Constants.BR_TURN_ZERO <= 1;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return flZero && frZero && blZero && brZero;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.frederickTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.fletcherTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.blakeTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.brianTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    
    Robot.drivetrain.fletcherTurn.setSensorPhase(true);
    Robot.drivetrain.blakeTurn.setSensorPhase(true);
    Robot.drivetrain.frederickTurn.setSensorPhase(true);
    Robot.drivetrain.brianTurn.setSensorPhase(true);

    Robot.drivetrain.fletcherTurn.setSelectedSensorPosition(0);
    Robot.drivetrain.blakeTurn.setSelectedSensorPosition(0);
    Robot.drivetrain.frederickTurn.setSelectedSensorPosition(0);
    Robot.drivetrain.brianTurn.setSelectedSensorPosition(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
