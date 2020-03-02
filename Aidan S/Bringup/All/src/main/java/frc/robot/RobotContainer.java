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
    private Joystick leftStick = new Joystick(Constants.OI.LEFT_JOYSTICK_PORT);
    private Joystick rightStick = new Joystick(Constants.OI.RIGHT_JOYSTICK_PORT);

    private JoystickButton forward = new JoystickButton(leftStick, Constants.OI.TURRET_FORWARD);
    private JoystickButton reverse = new JoystickButton(leftStick, Constants.OI.TURRET_REVERSE);
    private JoystickButton zero = new JoystickButton(rightStick, Constants.OI.TURRET_ZERO);

    private JoystickButton increase = new JoystickButton(leftStick, Constants.OI.SHOOTER_INCREASE);
    private JoystickButton decrease = new JoystickButton(leftStick, Constants.OI.SHOOTER_DECREASE);
    private JoystickButton stop = new JoystickButton(leftStick, Constants.OI.SHOOTER_STOP);

    private JoystickButton pivotUp = new JoystickButton(rightStick, Constants.OI.PIVOT_UP);
    private JoystickButton pivotDown = new JoystickButton(rightStick, Constants.OI.PIVOT_DOWN);
    private JoystickButton pivotZero = new JoystickButton(rightStick, Constants.OI.PIVOT_ZERO);
    private JoystickButton spin = new JoystickButton(rightStick, Constants.OI.SPIN);

    private JoystickButton s1forward = new JoystickButton(leftStick, Constants.OI.STAGE_ONE_FORWARD);
    private JoystickButton s1reverse = new JoystickButton(leftStick, Constants.OI.STAGE_ONE_REVERSE);
    private JoystickButton s2forward = new JoystickButton(rightStick, Constants.OI.STAGE_TWO_FORWARD);
    private JoystickButton s2reverse = new JoystickButton(rightStick, Constants.OI.STAGE_TWO_REVERSE);
    
    private JoystickButton cpforward = new JoystickButton(leftStick, Constants.OI.SPINNER_FORWARD);
    private JoystickButton cpreverse = new JoystickButton(leftStick, Constants.OI.SPINNER_REVERSE);

    // The robot's subsystems and commands are defined here...
    private final Turret turret = new Turret();
    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Drivetrain drivetrain = new Drivetrain();
    private final Spinner spinner = new Spinner();
    private final Stage m_stageOne = new Stage(Constants.StageOne.MOTOR_CAN_ID, 1.0);
    private final Stage m_stageTwo = new Stage(Constants.StageTwo.MOTOR_CAN_ID, -1.0);

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        drivetrain.setDefaultCommand(new Drive(drivetrain, leftStick, rightStick));

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
        forward.whenHeld(new InstantCommand(turret::forward));
        reverse.whenHeld(new InstantCommand(turret::reverse));
        zero.whenHeld(new InstantCommand(turret::zero));
        
        increase.whenHeld(new InstantCommand(shooter::increase));
        decrease.whenHeld(new InstantCommand(shooter::decrease));
        stop.whenHeld(new InstantCommand(shooter::stop));

        pivotUp.whileHeld(new InstantCommand(intake::up));
        pivotDown.whileHeld(new InstantCommand(intake::down));
        pivotZero.whenHeld(new InstantCommand(intake::zero));
        spin.whenHeld(new Spin(intake));

        s1forward.whenHeld(new Forward(m_stageOne));
        s1reverse.whenHeld(new Reverse(m_stageOne));
        s2forward.whenHeld(new Forward(m_stageTwo));
        s2reverse.whenHeld(new Reverse(m_stageTwo));

        cpforward.whenHeld(new CPForward(spinner));
        cpreverse.whenHeld(new CPReverse(spinner));
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
