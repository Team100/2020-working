/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Breech;
import frc.robot.subsystems.PressureBox;

public class Reload extends CommandBase {
  PressureBox pressureBox;
  Breech breech;
  boolean done = false;

  /**
   * Creates a new Reload.
   */
  public Reload(PressureBox pb, Breech b) {
    pressureBox = pb;
    breech = b;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(pressureBox);
    addRequirements(breech);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    breech.open();
    // wait here
    breech.eject();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (breech.isLoaded()) {
      done = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // wait a few seconds
    breech.close();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
