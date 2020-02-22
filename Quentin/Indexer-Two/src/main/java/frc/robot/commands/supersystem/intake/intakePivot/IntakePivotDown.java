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

public class IntakePivotDown extends CommandBase {
  /**
   * Subsystems used
   */
  public IntakePivot pivot;

  /**
   * Creates a new IntakePivotDown.
   */
  public IntakePivotDown(IntakePivot pivot) {
    this.pivot = pivot;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("initialized IntakePivotDown");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.pivot.pivot
        .drivePercentOutput(Constants.IntakeConstants.IntakeMotors.IntakePivot.PERCENT_OUTPUT_DOWN);
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
