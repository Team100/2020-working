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

public class BreechOpen extends CommandBase {
  Breech breech;
  boolean done = false;

  /**
   * Creates a new Pressurize.
   */
  public BreechOpen(Breech b) {
    // Use addRequirements() here to declare subsystem dependencies.
    breech = b;

    addRequirements(breech);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    breech.open();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      done = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
