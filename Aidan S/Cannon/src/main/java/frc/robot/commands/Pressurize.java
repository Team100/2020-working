/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.PressureBox;

public class Pressurize extends CommandBase {
  PressureBox pressureBox;
  boolean done = false;

  /**
   * Creates a new Pressurize.
   */
  public Pressurize(PressureBox pb) {
    // Use addRequirements() here to declare subsystem dependencies.
    pressureBox = pb;

    addRequirements(pressureBox);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pressureBox.closePiston();
    pressureBox.startFilling();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (pressureBox.checkPressure() >= Constants.PressureBox.TARGET_PRESSURE) {
      done = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pressureBox.stopFilling();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
