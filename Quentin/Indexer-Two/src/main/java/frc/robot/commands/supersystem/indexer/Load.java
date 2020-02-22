/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerStageOne;
import frc.robot.subsystems.IndexerStageTwo;
import frc.robot.subsystems.Indexer;

public class Load extends CommandBase {
  /**
   * subsystems used.
   */
  IndexerStageOne stageOne;
  IndexerStageTwo stageTwo;
  Indexer indexer;

  /**
   * Creates a new Load.
   */
  public Load(IndexerStageOne stageOne, IndexerStageTwo stageTwo, Indexer indexer) {
    this.stageOne = stageOne;
    this.stageTwo = stageTwo;
    this.indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(indexer.indexerState){
      case EMPTY:
        new Empty(stageOne, stageTwo, indexer);
        break;

      case ONE_BALL:
        break;

      case TWO_BALL:
        break;

      case LOW_THREE_BALL:
       break;

      case HIGH_THREE_BALL:
       break;

      case FOUR_BALL:
        break;

      case FIVE_BALL:
        break;

      case READY_SHOOT:
        break;

      case SHOOTING:
        break;

      case JAMMED:
        break;

      default:

    }
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
