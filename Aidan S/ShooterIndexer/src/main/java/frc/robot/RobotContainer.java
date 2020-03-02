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
import frc.robot.commands.IndexMoveEnd;
import frc.robot.commands.IndexMoveFoward;
import frc.robot.commands.IndexMoveUnJam;
import frc.robot.commands.IndexMoveUp;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
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
  // private final Turret turret = new Turret();
  private final Shooter shooter = new Shooter();
  private final Indexer indexer = new Indexer();
  private final Joystick controller = new Joystick(0);
  private JoystickButton indexMoveFoward;
  private JoystickButton indexMoveUnJam;
  private JoystickButton indexMoveEnd;
  private JoystickButton indexMoveUp;
  private JoystickButton indexMoveS1;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    indexMoveFoward = new JoystickButton(controller, Constants.OI.INDEX_FORWARD);
    indexMoveUnJam = new JoystickButton(controller, Constants.OI.INDEX_UNJAM);
    indexMoveEnd = new JoystickButton(controller, Constants.OI.INDEX_END);
    indexMoveUp = new JoystickButton(controller, Constants.OI.INDEX_UP);
    indexMoveS1 = new JoystickButton(controller, Constants.OI.INDEX_STAGE1);

    // Configure the button bindings
    configureButtonBindings();
    shooter.setDefaultCommand(new Shoot(shooter, controller));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    indexMoveFoward.whileHeld(new IndexMoveFoward(indexer));
    indexMoveUnJam.whileHeld(new IndexMoveUnJam(indexer));
    indexMoveEnd.whileHeld(new IndexMoveEnd(indexer));
    indexMoveUp.whileHeld(new IndexMoveUp(indexer, true));
    indexMoveS1.whileHeld(new IndexMoveUp(indexer, false));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
