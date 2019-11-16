/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navigation.pathnav;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.supporting.CustomPathGenerator;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class ProcessDriving extends Command {
  public Trajectory t;
  public Trajectory leftT;
  public Trajectory rightT;

  public EncoderFollower leftEncFollower;
  public EncoderFollower rightEncFollower;
  public boolean done;
  public ProcessDriving(Trajectory t) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    this.t = t;
    this.done = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initializing ProcessDriving");
    this.done = false;

    leftT = CustomPathGenerator.getLeftTrajectory(t);
    rightT = CustomPathGenerator.getRightTrajectory(t);

    leftEncFollower = new EncoderFollower(leftT);
    rightEncFollower = new EncoderFollower(rightT);

    leftEncFollower.configureEncoder(Robot.drivetrain.getLeftDrivetrainTicks(), Constants.DTConstants.TICKS_PER_REV, Constants.DTConstants.WHEEL_DIAMETER);
    leftEncFollower.configurePIDVA(Constants.LeftLeader.KP, Constants.LeftLeader.KI, Constants.LeftLeader.KD, Constants.LeftLeader.KV, Constants.LeftLeader.KA);

    rightEncFollower.configureEncoder(Robot.drivetrain.getRightDrivetrainTicks(), Constants.DTConstants.TICKS_PER_REV, Constants.DTConstants.WHEEL_DIAMETER);
    rightEncFollower.configurePIDVA(Constants.RightLeader.KP, Constants.RightLeader.KI, Constants.RightLeader.KD, Constants.RightLeader.KV, Constants.RightLeader.KA);
    System.out.println("ProcessDriving initialized");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("ProcessDriving Execute");
    // Based on https://wpilib.screenstepslive.com/s/currentCS/m/84338/l/1021631-integrating-path-following-into-a-robot-program
    double leftSpeed = leftEncFollower.calculate(Robot.drivetrain.getLeftDrivetrainTicks());
    double rightSpeed = rightEncFollower.calculate(Robot.drivetrain.getRightDrivetrainTicks());
    double heading = 0; //TODO Comment this out
    //double heading = gyro.getAngle();
    double desiredHeading = Pathfinder.r2d(leftEncFollower.getHeading());
    double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - heading);
    headingDifference = headingDifference % 360;
    if (Math.abs(headingDifference) > 180.0) {
      headingDifference = (headingDifference > 0) ? headingDifference - 360 : headingDifference + 360;
    }
    //double turn =  0.8 * (-1.0/80.0) * headingDifference;
    double turn = 0;
    Robot.drivetrain.driveInVelocityMode(leftSpeed + turn, rightSpeed - turn);

    SmartDashboard.putNumber("Left Intended", leftSpeed+turn);
    SmartDashboard.putNumber("Right Intended", rightSpeed-turn);
    SmartDashboard.putNumber("DT Left Velocity", Robot.drivetrain.leftLeader.getSelectedSensorVelocity());
    SmartDashboard.putNumber("DT Right Velocity", Robot.drivetrain.rightLeader.getSelectedSensorVelocity());
    System.out.println("Left: "+ (leftSpeed+turn));
    System.out.println("Right: "+(rightSpeed-turn));



  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return leftEncFollower.isFinished() || rightEncFollower.isFinished() || this.done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.driveInVelocityMode(0,0);
    System.out.println("END");
    System.out.println(leftEncFollower.isFinished());
    System.out.println(rightEncFollower.isFinished());
    System.out.println(this.done);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("Interrupt ProcessDriving");
    end();
  }
}
