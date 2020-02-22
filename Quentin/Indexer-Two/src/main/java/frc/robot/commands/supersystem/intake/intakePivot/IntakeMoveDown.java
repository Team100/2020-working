/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.intake.intakePivot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakePivot;

public class IntakeMoveDown extends CommandBase {

  public IntakePivot pivot;

  /**
   * Creates a new IntakeMoveDown.
   */
  public IntakeMoveDown(IntakePivot pivot) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.pivot = pivot;
    addRequirements(this.pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pivot.currentAngle = IntakePivot.ValidAngles.UNCERTAIN;
    pivot.locationState = IntakePivot.LocationState.MOVING;
    pivot.pivot.driveMotionMagic(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_DOWN_DEGREES);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pivot.currentAngle = IntakePivot.ValidAngles.DOWN; // TODO Account for failure context
    pivot.locationState = IntakePivot.LocationState.STATIONARY;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
