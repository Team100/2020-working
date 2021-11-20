/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Breech;
import frc.robot.subsystems.PressureBox;
import frc.robot.subsystems.Safety;

public class Fire extends CommandBase {
  PressureBox pressureBox;
  Breech breech;
  Safety safety;
  boolean done = false;

  /**
   * Creates a new Fire.
   */
  public Fire(PressureBox pb, Breech b, Safety s) {
    pressureBox = pb;
    breech = b;
    safety = s;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(pressureBox);
    addRequirements(breech);
    addRequirements(safety);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Make sure breech is loaded and closed
    if (breech.isOpen() || !breech.isLoaded()) {
      done = true;
      return;
    }

    // Make sure no one is standing in front of the barrel
    if (safety.objectInRange()) {
      done = true;
      return;
    }

    // Make sure we have enough pressure
    if (pressureBox.checkPressure() < Constants.PressureBox.FIRING_PRESSURE) {
      done = true;
      return;
    }

    pressureBox.openPiston();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // wait a few seconds here
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pressureBox.closePiston();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
