/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Constants.StickDirection;
import frc.robot.Constants.StickType;

/**
 * Drive command
 */
public class Drive extends Command {
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.drivetrain.swerveDrive.move(  Robot.oi.getStickValue(StickType.LEFT, StickDirection.Y),   //Straight
                                            Robot.oi.getStickValue(StickType.LEFT, StickDirection.X),   //Strafe
                                            Robot.oi.getStickValue(StickType.RIGHT, StickDirection.X),  //Rotate
                                            (double)(Robot.ahrs.getFusedHeading()));                    //NavX
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drivetrain.swerveDrive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
