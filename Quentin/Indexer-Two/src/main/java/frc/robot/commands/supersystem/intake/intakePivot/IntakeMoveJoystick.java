/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.intake.intakePivot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakePivot;

public class IntakeMoveJoystick extends CommandBase {
  IntakePivot pivot;
  Joystick joystick;

  /**
   * Creates a new IntakeMoveJoystick.
   */
  public IntakeMoveJoystick(IntakePivot pivot, Joystick joystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.pivot = pivot;
    this.joystick = joystick;
    addRequirements(this.pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pivot.pivot.drivePercentOutput(joystick.getRawAxis(0));
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
