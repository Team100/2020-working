/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.indexer.indexStageOne;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Indexer.ActionState;
import frc.robot.subsystems.IndexerStageOne;

public class IndexerStageOneStop extends CommandBase {
  /**
   * Creates a new IndexerStageOneStop.
   */
  public IndexerStageOne indexer;

  public IndexerStageOneStop(IndexerStageOne indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexer = indexer;
    addRequirements(this.indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.indexer.actionState = ActionState.STOP;

    indexer.indexerStageOne.drivePercentOutput(0);

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
