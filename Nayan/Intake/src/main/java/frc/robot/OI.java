package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IndexMove;
import frc.robot.commands.IndexMoveUnJam;
import frc.robot.commands.IndexMoveEnd;
import frc.robot.commands.IndexMoveUp;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

public class OI {

    private Joystick stick;
    private JoystickButton IndexMoveFoward;
    private JoystickButton IndexMoveUnJam;
    private JoystickButton IndexMoveEnd;
    private JoystickButton IndexMoveUp;

    public OI() {
        stick = new Joystick(0);
        IndexMoveFoward = new JoystickButton(stick, 1);
        IndexMoveUnJam = new JoystickButton(stick, 2);
        IndexMoveEnd = new JoystickButton(stick, 3);
        IndexMoveUp = new JoystickButton(stick, 4);

        IndexMoveFoward.whileHeld(new IndexMove());
        IndexMoveUnJam.whileHeld(new IndexMoveUnJam());
        IndexMoveEnd.whileHeld(new IndexMoveEnd());
        IndexMoveUp.whileHeld(new IndexMoveUp());
    }

    public Joystick stick() {
        return stick;
    }
    

}