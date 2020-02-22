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

public class FourBall extends CommandBase {
  /**
   * subsystems used.
   */
  IndexerStageOne stageOne;
  IndexerStageTwo stageTwo;
  Indexer indexer;

  /**
   * Creates a new FourBall.
   */
  public FourBall(IndexerStageOne stageOne, IndexerStageTwo stageTwo, Indexer indexer) {
    this.stageOne = stageOne;
    this.stageTwo = stageTwo;
    this.indexer = indexer;
    this.indexer.frontSwitchState = false;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.stageOne);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("initialized FourBall");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    stageOne.indexerStageOne
        .drivePercentOutput(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexer.frontSwitchState = false;
    indexer.indexerState = Indexer.IndexerStates.FIVE_BALL;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !(indexer.frontSensor.get());
  }
}
