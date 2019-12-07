/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants.*;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    public Joystick leftStick, rightStick, gamepad;

    OI(){
        JoystickButton zero = null;
        JoystickButton homeRel = null;
        JoystickButton homeAbs = null;

        switch(Constants.CONTROL_TYPE) {
            case JOYSTICKS:
                leftStick = new Joystick(0);
                rightStick = new Joystick(1);
                break;
            case GAMEPAD:
                gamepad = new Joystick(0);
                zero = new JoystickButton(gamepad, 4);
                homeRel = new JoystickButton(gamepad, 1);
                homeAbs = new JoystickButton(gamepad, 3);
                break;
        }
        
        zero.whenPressed(new Zero());
        homeRel.whenPressed(new HomeRelative());
        homeAbs.whenPressed(new HomeAbsolute());
    }
    
    public double getStickValue(StickType stick, StickDirection dir) {
        switch(Constants.CONTROL_TYPE) {
            case JOYSTICKS:
                if (stick == StickType.LEFT && dir == StickDirection.X) return leftStick.getX();
                if (stick == StickType.LEFT && dir == StickDirection.Y) return -leftStick.getY();
                if (stick == StickType.RIGHT && dir == StickDirection.X) return rightStick.getX();
                if (stick == StickType.RIGHT && dir == StickDirection.Y) return -rightStick.getY();
            case GAMEPAD:
                if (stick == StickType.LEFT && dir == StickDirection.X) return gamepad.getRawAxis(0);
                if (stick == StickType.LEFT && dir == StickDirection.Y) return -gamepad.getRawAxis(1);
                if (stick == StickType.RIGHT && dir == StickDirection.X) return gamepad.getRawAxis(2);
                if (stick == StickType.RIGHT && dir == StickDirection.Y) return -gamepad.getRawAxis(3);
            default: return 0;
        }
    }
}