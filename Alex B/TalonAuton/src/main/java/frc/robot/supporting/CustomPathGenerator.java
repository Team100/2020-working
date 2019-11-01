/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.supporting;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.CustomClasses.StoredTrajectory;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class CustomPathGenerator {

  public static Trajectory generate(Waypoint[] points){
    Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.Auto.DT, Constants.Auto.MAX_VELOCITY, Constants.Auto.MAX_ACCELERATION, Constants.Auto.MAX_JERK);
    Trajectory trajectory = Pathfinder.generate(points, config);

    System.out.println("DONE");
    return trajectory;

  }

  public static Trajectory getLeftTrajectory(Trajectory trajectory){
    TankModifier modifier = new TankModifier(trajectory);
    modifier.modify(Constants.RobotCharacteristics.WHEELBASE_WIDTH);
    return modifier.getLeftTrajectory();
  }

  public static Trajectory getRightTrajectory(Trajectory trajectory){
    TankModifier modifier = new TankModifier(trajectory);
    modifier.modify(Constants.RobotCharacteristics.WHEELBASE_WIDTH);
    return modifier.getRightTrajectory();
  }

}
