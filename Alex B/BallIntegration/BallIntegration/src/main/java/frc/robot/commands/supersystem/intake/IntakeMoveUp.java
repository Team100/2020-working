/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.LocationState;
import frc.robot.subsystems.Intake.ValidAngles;

public class IntakeMoveUp extends CommandBase {

  public Intake intake;
  /**
   * Creates a new IntakeMoveUp.
   */
  public IntakeMoveUp(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.intake = intake;
    addRequirements(this.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.currentAngle = ValidAngles.UNCERTAIN;
    intake.locationState = LocationState.MOVING;
    intake.pivot.driveMotionMagic(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_UP_DEGREES);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.currentAngle = ValidAngles.UP; //TODO Account for failure context
    intake.locationState = LocationState.STATIONARY;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
