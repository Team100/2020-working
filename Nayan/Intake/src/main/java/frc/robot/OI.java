package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;


public class OI {

    private Joystick gamepad;
 
    
    private JoystickButton gamepad;

    public OI() {
        gamepad = new Joystick(0);

        JoystickButton IndexMove = new JoystickButton(gamepad, 1);

        IndexMove.whenPressed(new IndexerFoward());
    }

    public Joystick gampad() {
        return gamepad;
    }
    

}