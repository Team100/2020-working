package frc.robot.supporting;

import jaci.pathfinder.Trajectory;

public class PathDebugging{
    public static void printTrajectory(Trajectory trajectory){
        for(int i = 0; i < trajectory.length(); i++){
            Trajectory.Segment seg = trajectory.get(i);
            System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f", seg.dt, seg.x, seg.y, seg.position, seg.velocity, seg.acceleration, seg.jerk, seg.heading);
        }
    }
}
