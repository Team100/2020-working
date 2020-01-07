/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.frclib.AutoHelperFunctions;

import java.util.List;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DTConstants;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class CreateAutoNavigationCommand {
    public static Command createAutoNavigatiCommand(Drivetrain drivetrain, Pose2d start, List<Translation2d> waypoints,Pose2d end){
        var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.DTConstants.KS,
                                       Constants.DTConstants.KV,
                                       Constants.DTConstants.KA),
            Constants.DTConstants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(Constants.Auto.MAX_VELOCITY,
                             Constants.Auto.MAX_ACCELERATION)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.DTConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, waypoints, end, config);
    

    RamseteCommand ramseteCommand = new RamseteCommand(
        trajectory,
        drivetrain.getPose(),
        Constants.DTConstants.RAMSETE_B, Constants.DTConstants.RAMSETE_ZETA),
        new SimpleMotorFeedforward(Constants.DTConstants.KS,
                                   Constants.DTConstants.KV,
                                   Constants.DTConstants.KA),
        Constants.DTConstants.kDriveKinematics,
        drivetrain.getWheelSpeeds(),
        new PIDController(Constants.DTConstants.KP, 0, 0),
        new PIDController(Constants.DTConstants.KP, 0, 0),
        // RamseteCommand passes volts to the callback
        drivetrain::tankDriveVolts,
        
        drivetrain
    );

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_robotDrive.tankDriveVolts(0, 0))
    }
}
