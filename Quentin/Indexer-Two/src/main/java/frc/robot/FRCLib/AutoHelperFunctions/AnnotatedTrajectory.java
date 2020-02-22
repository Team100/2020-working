/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.AutoHelperFunctions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

public class AnnotatedTrajectory extends Trajectory {
    public AnnotatedTrajectory(Trajectory trajectory) {
        super(trajectory.getStates());
    }

    @Override
    public State sample(double timeSeconds) {
        State current = super.sample(timeSeconds);
        System.out.println("TRAJECTORY" + current.toString());
        SmartDashboard.putNumber("Traj_Sec", current.timeSeconds);
        SmartDashboard.putNumber("Traj_Vel m/s", current.velocityMetersPerSecond);
        SmartDashboard.putNumber("Traj_Accel m/s/s", current.accelerationMetersPerSecondSq);
        SmartDashboard.putNumber("Traj_Pos_X m", current.poseMeters.getTranslation().getX());
        SmartDashboard.putNumber("Traj_Pos_Y m", current.poseMeters.getTranslation().getY());
        SmartDashboard.putNumber("Traj_Angle deg", current.poseMeters.getRotation().getDegrees());
        SmartDashboard.putNumber("Traj_Curvatured rad/m", current.curvatureRadPerMeter);

        return current;
    }

}