/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navigation.pathgen;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.CustomClasses.StoredTrajectory;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.*;

public class PathGenerator extends Command {
  String name;

  Waypoint[] points;
  Trajectory trajectory;
  public PathGenerator(String name, Waypoint[] points) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.name = name;
    this.points = points;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.Auto.DT, Constants.Auto.MAX_VELOCITY, Constants.Auto.MAX_ACCELERATION, Constants.Auto.MAX_JERK);
    
    trajectory = Pathfinder.generate(points, config);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.straightpath = new StoredTrajectory(this.name, this.trajectory);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
