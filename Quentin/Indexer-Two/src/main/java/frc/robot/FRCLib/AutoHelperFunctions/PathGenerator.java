/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.AutoHelperFunctions;

import java.util.List;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class PathGenerator {
    public static Command createAutoNavigationCommand(Drivetrain m_drivetrain, Pose2d start,
            List<Translation2d> waypoints, Pose2d end) {
        System.out.println("Creating Auto Command");
        var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(Constants.DrivetrainConstants.DrivetrainParameters.KS,
                        Constants.DrivetrainConstants.DrivetrainParameters.KV,
                        Constants.DrivetrainConstants.DrivetrainParameters.KA),
                Constants.DrivetrainConstants.DrivetrainParameters.kDriveKinematics, 10);

        // Create config for trajectory
        TrajectoryConfig config = new TrajectoryConfig(Constants.DrivetrainConstants.AutonConstants.MAX_VELOCITY,
                Constants.DrivetrainConstants.AutonConstants.MAX_ACCELERATION)
                        // Add kinematics to ensure max speed is actually obeyed
                        .setKinematics(Constants.DrivetrainConstants.DrivetrainParameters.kDriveKinematics)
                        // Apply the voltage constraint
                        .addConstraint(autoVoltageConstraint);

        // An example trajectory to follow. All units in meters.
        AnnotatedTrajectory trajectory = new AnnotatedTrajectory(
                TrajectoryGenerator.generateTrajectory(start, waypoints, end, config));
        System.out.println("Generated Trajectory");
        RamseteCommand ramseteCommand = new RamseteCommand(trajectory, m_drivetrain::getPose,
                new CustomRamseteController(Constants.DrivetrainConstants.DrivetrainParameters.RAMSETE_B,
                        Constants.DrivetrainConstants.DrivetrainParameters.RAMSETE_ZETA),

                Constants.DrivetrainConstants.DrivetrainParameters.kDriveKinematics,

                m_drivetrain::tankDriveVelocity, m_drivetrain);

        // Run path following command, then stop at the end.
        System.out.println("Finished Creating Auto Command");
        return ramseteCommand.andThen(() -> m_drivetrain.tankDrivePercentOutput(0, 0));
    }
}
