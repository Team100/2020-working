// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc100.Team100Robot;

import org.usfirst.frc100.Team100Robot.commands.*;
import org.usfirst.frc100.Team100Robot.commands.Drivetrain.Drive;
import org.usfirst.frc100.Team100Robot.commands.Drivetrain.Shift.ShiftToHigh;
import org.usfirst.frc100.Team100Robot.commands.Drivetrain.Shift.ShiftToLow;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
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

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    private Joystick leftStick;
    private Joystick rightStick;
    private Joystick manipulatorControl;
    private Joystick buttonBoard;
    
    private JoystickButton shiftLow;
    private JoystickButton shiftHigh;
    private JoystickButton spitCargo;
    private JoystickButton hatchUp;
    private JoystickButton hatchDown;
    private JoystickButton intakeCargo;
    private JoystickButton cargoUp;
    private JoystickButton cargoDown;
    private JoystickButton climberToggle;
    private JoystickButton hatchClamp;
    private JoystickButton elevatorStageUp;
    private JoystickButton hatchRelease;
    private JoystickButton elevatorStageDown;
    private JoystickButton restartHoming;

    // ButtonBoard

    private JoystickButton hatchIntake;
    private JoystickButton cargoIntake;
    private JoystickButton cargoLevel3Reverse;
    private JoystickButton hatchLevel3;
    private JoystickButton cargoLevel2;
    private JoystickButton cargoLevel3;
    private JoystickButton hatchLevel2;
    private JoystickButton home;
    private JoystickButton cargoHPIntake;
    private JoystickButton score;
    private JoystickButton cargoLevel1;
    public JoystickButton autoSwitch;
    private JoystickButton forceGlobalRehome;
    private JoystickButton hatchLevel1;
    
    

    public OI() {

        /*
         * INIT Joysticks
         */
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);
        manipulatorControl = new Joystick(2);
        buttonBoard = new Joystick(3);

        
        /* 
         * Joystick 0 (Left Stick)
         */

        shiftLow = new JoystickButton(leftStick, 3);
        shiftLow.whenPressed(new ShiftToLow());
        
        hatchUp = new JoystickButton(leftStick, 4);
 
        hatchDown = new JoystickButton(leftStick, 5);

        //Joystick 1 (Right Stick)
        intakeCargo = new JoystickButton(rightStick, 1);
        
        shiftHigh = new JoystickButton(rightStick, 3);
        shiftHigh.whenPressed(new ShiftToHigh());
        

        //Joystick 2 (Manipulator Control)
        climberToggle = new JoystickButton(manipulatorControl, 4);
        hatchClamp = new JoystickButton(manipulatorControl, 5);
        elevatorStageUp = new JoystickButton(manipulatorControl, 6);
        hatchRelease = new JoystickButton(manipulatorControl, 7);
        elevatorStageDown = new JoystickButton(manipulatorControl, 8);

        //Button Board
        hatchIntake= new JoystickButton(buttonBoard,1);
        cargoIntake= new JoystickButton(buttonBoard,2);
        cargoLevel3Reverse= new JoystickButton(buttonBoard,3);
        hatchLevel3= new JoystickButton(buttonBoard,4);
        cargoLevel2= new JoystickButton(buttonBoard,5);
        cargoLevel3= new JoystickButton(buttonBoard,6);
        hatchLevel2= new JoystickButton(buttonBoard,7);
        home= new JoystickButton(buttonBoard,8);
        cargoHPIntake= new JoystickButton(buttonBoard,10);
        forceGlobalRehome = new JoystickButton(buttonBoard, 11);
        
        score= new JoystickButton(buttonBoard,12);
        autoSwitch = new JoystickButton(buttonBoard, 14); // On for manual; off for tele
        cargoLevel1= new JoystickButton(buttonBoard,15);
        hatchLevel1= new JoystickButton(buttonBoard,16);



      

        // //SmartDashboard Buttons
        //SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        //SmartDashboard.putData("DriveCMD", new Drive());
        //SmartDashboard.putData("HighGear", new HighGear());
        //SmartDashboard.putData("LowGear", new LowGear());
    }

    public Joystick getLeftStick() {
        return leftStick;
    }

    public Joystick getRightStick() {
        return rightStick;
    }

    public Joystick getManipulatorControl() {
        return manipulatorControl;
    }
    

}

