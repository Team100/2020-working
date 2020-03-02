    /*----------------------------------------------------------------------------*/
    /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
    /* Open Source Software - may be modified and shared by FRC teams. The code   */
    /* must be accompanied by the FIRST BSD license file in the root directory of */
    /* the project.                                                               */
    /*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Turret;
import frc.robot.Constants;
import frc.robot.Server;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RotateTurret extends CommandBase {
    private final Turret subsystem;
    private final Joystick controller;
    private final JoystickButton toggleButton;
    private final JoystickButton resetButton;
    private boolean mockTarget = false;
    /**
     * Creates a new RotateTurret.
     */
    public RotateTurret(Turret s, Joystick j) {
        subsystem = s;
        controller = j;
        toggleButton = new JoystickButton(controller, 1);
        resetButton = new JoystickButton(controller, 4);
        addRequirements(subsystem);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        
        toggleButton.whenPressed(new InstantCommand(() -> mockTarget = !mockTarget));
        resetButton.whenPressed(new InstantCommand(() -> subsystem.talon.setSelectedSensorPosition(0)));
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(mockTarget){
            SmartDashboard.putBoolean("Target Exists", true);
            subsystem.setTarget(controller.getDirectionDegrees()*Constants.Turret.TICKS_PER_DEG, true);
        } else {
            if(Server.target != null) {
                if(Server.target.getDistance() != -1) {
                    SmartDashboard.putNumber("Camera HAngle", Server.target.getHAngle());
                    SmartDashboard.putBoolean("Target Exists", true);
                    double h = Server.target.getHAngle();
                    subsystem.setTarget(Math.signum(h)*Math.pow(Math.abs(h), 1.5)/2, false);
                } else {
                SmartDashboard.putNumber("Camera HAngle", 999.999999);
                SmartDashboard.putBoolean("Target Exists", false);
                // if (subsystem.talon.getSelectedSensorPosition() > Constants.Turret.ALLOWABLE_ERROR)
                // subsystem.setTarget(subsystem.talon.getSelectedSensorPosition()+1, true);
                // if (subsystem.talon.getSelectedSensorPosition() < Constants.Turret.ALLOWABLE_ERROR)
                // subsystem.setTarget(subsystem.talon.getSelectedSensorPosition()-1, true);
                }
            } else {
                subsystem.setTarget(subsystem.talon.getSelectedSensorPosition(), true);
                SmartDashboard.putBoolean("Target Exists", false);
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
