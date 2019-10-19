package frc.robot.commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Home command
 */
public class Home extends Command {
    public Home() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.fletcher.zero();
        Robot.drivetrain.frederick.zero();
        Robot.drivetrain.blake.zero();
        Robot.drivetrain.brian.zero();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.drivetrain.updatePeriodic();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return (
            Robot.drivetrain.fletcher.getCurrentPos() == Constants.FL_TURN_ABS_ZERO;
        );
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