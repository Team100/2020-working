/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.commands.ExampleCommand;
import frc.robot.frclib.AutoHelperFunctions.CustomRamseteControllerAbstraction;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    Pose2d start = new Pose2d(0.0, 0.0, Rotation2d.fromDegrees(0));
    List<Translation2d> waypoints = List.of();
    Pose2d end = new Pose2d(2.0, 0.0, Rotation2d.fromDegrees(0));
    //return createAutoNavigationCommand(start, waypoints, end);
    return m_autoCommand;
  }

  public Command createAutoNavigationCommand(Pose2d start, List<Translation2d> waypoints, Pose2d end) {
    System.out.println("Creating Auto Command");
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(Constants.DTConstants.KS, Constants.DTConstants.KV, Constants.DTConstants.KA),
        m_drivetrain.driveKinematics, 10);

    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(Constants.Auto.MAX_VELOCITY, Constants.Auto.MAX_ACCELERATION)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(m_drivetrain.driveKinematics)
        // Apply the voltage constraint
        .addConstraint(autoVoltageConstraint);

    System.out.println("Generating");
    // An example trajectory to follow. All units in meters.
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, waypoints, end, config);
    System.out.println("Generated Trajectory");
    try {
      RamseteCommand ramseteCommand = new RamseteCommand(trajectory, m_drivetrain::getPose,
          new CustomRamseteControllerAbstraction(Constants.DTConstants.RAMSETE_B, Constants.DTConstants.RAMSETE_ZETA),

          m_drivetrain.driveKinematics,

          m_drivetrain::tankDriveVelocity, m_drivetrain);

      // Run path following command, then stop at the end.
      System.out.println("Finished Creating Auto Command");
      return ramseteCommand.andThen(() -> m_drivetrain.tankDriveSpeed(0, 0));
    } catch (Exception e) {
      System.out.println("Entered Catch");
      System.out.println(e.getLocalizedMessage());
      System.exit(254);
      return null;
    }

  }
}
