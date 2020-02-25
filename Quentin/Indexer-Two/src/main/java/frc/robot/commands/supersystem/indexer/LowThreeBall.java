/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.supersystem.indexer;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IndexerStageOne;
import frc.robot.subsystems.IndexerStageTwo;

public class LowThreeBall extends CommandBase {
  /**
   * subsystems used.
   */
  IndexerStageOne stageOne;
  IndexerStageTwo stageTwo;
  Indexer indexer;

  /**
   * Creates a new LowThreeBall.
   */
  public LowThreeBall(IndexerStageOne stageOne, IndexerStageTwo stageTwo, Indexer indexer) {
    this.stageOne = stageOne;
    this.stageTwo = stageTwo;
    this.indexer = indexer;
    this.indexer.frontSwitchState = false;
    this.indexer.rearSwitchState = false;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.stageOne);
    addRequirements(this.stageTwo);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("initialized LowThreeBall");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    stageOne.indexerStageOne
        .drivePercentOutput(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
    stageTwo.indexerStageTwo
        .drivePercentOutput(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_FORWARD);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexer.frontSwitchState = false;
    indexer.rearSwitchState = false;
    indexer.indexerState = Indexer.IndexerStates.HIGH_THREE_BALL;

    // conditionally invoke next state
    if (Constants.IndexerConstants.IndexerMotionParameters.CONTINUOUS_FEED) {
      System.out.println("Loading Hi Three ball state");
      new HighThreeBall(stageOne, stageTwo, indexer).schedule();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !(indexer.rearSensor.get());
  }
}
