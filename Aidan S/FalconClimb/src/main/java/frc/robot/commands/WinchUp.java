/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Winch;

public class WinchUp extends CommandBase {

public Winch winch;

  /**
   * Creates a new WinchUp.
   */
  public WinchUp(Winch winch) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.winch = winch;
    addRequirements(winch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    winch.drive(0.3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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
