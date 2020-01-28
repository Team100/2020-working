package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IndexMove;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

public class OI {

    Joystick stick = new Joystick(0);
    Button IndexMove = new JoystickButton(stick, 1);

    public OI() {
        IndexMove.whileHeld(new IndexMove());
    }

    public Joystick stick() {
        return stick;
    }
    

}