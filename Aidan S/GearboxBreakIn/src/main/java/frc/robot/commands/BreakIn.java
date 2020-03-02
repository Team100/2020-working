/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Gearbox;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class BreakIn extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Gearbox gearbox;
    private final Timer timer;
    private double setpoint;
    private boolean done = true;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public BreakIn(Gearbox g) {
        gearbox = g;
        timer = new Timer();
        setpoint = 0;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(gearbox);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        done = false;
        setpoint = Constants.Gearbox.POWER;

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = Constants.Gearbox.TIME;

            public void run() {
                SmartDashboard.putNumber("Time Left", i--);
                if (i < 0) {
                    timer.cancel();
                    setpoint = 0;
                    done = true;
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        gearbox.set(setpoint);
        SmartDashboard.putBoolean("done", done);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        gearbox.set(0);
        done = true;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
