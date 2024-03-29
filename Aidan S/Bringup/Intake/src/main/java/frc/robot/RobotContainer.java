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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private Joystick rightStick = new Joystick(Constants.OI.RIGHT_JOYSTICK_PORT);
    private JoystickButton pivotUp = new JoystickButton(rightStick, Constants.OI.PIVOT_UP);
    private JoystickButton pivotDown = new JoystickButton(rightStick, Constants.OI.PIVOT_DOWN);
    private JoystickButton pivotZero = new JoystickButton(rightStick, Constants.OI.PIVOT_ZERO);
    private JoystickButton spin = new JoystickButton(rightStick, Constants.OI.SPIN);

    // The robot's subsystems and commands are defined here...
    private final Intake intake = new Intake();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

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
        pivotUp.whenHeld(new InstantCommand(intake::up));
        pivotDown.whenHeld(new InstantCommand(intake::down));
        pivotZero.whenHeld(new InstantCommand(intake::zero));
        spin.whenHeld(new Spin(intake));
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
