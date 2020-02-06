/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Drive;
import frc.robot.commands.UnlockClimber;
import frc.robot.commands.WinchDown;
import frc.robot.commands.WinchNormal;
import frc.robot.commands.WinchUp;
import frc.robot.commands.LockClimber;
import frc.robot.subsystems.ClimberLock;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Winch;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final Joystick joystick;
  public final JoystickButton unlockButton;
  public final JoystickButton lockButton;
  public final JoystickButton winchDown;
  public final JoystickButton winchUp;

  public final Drivetrain drivetrain;
  public final ClimberLock climberLock;
  public final Winch winch;

  public final Drive drive;
  public final UnlockClimber unlockClimber;
  public final LockClimber lockClimber;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    joystick = new Joystick(Constants.Robot.JOYSTICK_PORT);
    unlockButton = new JoystickButton(joystick, 1);
    lockButton = new JoystickButton(joystick, 3);
    winchDown = new JoystickButton(joystick, 2);
    winchUp = new JoystickButton(joystick, 4);

  
    

    drivetrain = new Drivetrain();
    climberLock = new ClimberLock();
    winch = new Winch();

    winchUp.whileHeld(new WinchUp(winch));
    winchDown.whileHeld(new WinchDown(winch));

    drive = new Drive(drivetrain, joystick);
    unlockClimber = new UnlockClimber(climberLock);
    lockClimber = new LockClimber(climberLock);

    unlockButton.whenPressed(unlockClimber);
    lockButton.whenPressed(lockClimber);

    drivetrain.setDefaultCommand(drive);
    winch.setDefaultCommand(new WinchNormal(winch));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
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
    return null; // m_autoCommand;
  }
}
