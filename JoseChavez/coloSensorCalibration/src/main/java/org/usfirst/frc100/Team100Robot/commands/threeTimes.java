/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands;

import org.usfirst.frc100.Team100Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class threeTimes extends Command {

private boolean done = false;

  public threeTimes() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.controlPanelSpinner);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.controlPanelSpinner.resetTo0();
    Robot.controlPanelSpinner.spin(1);
    done = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.controlPanelSpinner.spin(1);
    if (Robot.controlPanelSpinner.getRevolutionsCounter()>=7){
      done = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.controlPanelSpinner.spin(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
