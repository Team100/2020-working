package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexMoveUp extends CommandBase {
  private Indexer indexer;
  private boolean both;

  public IndexMoveUp(Indexer i, boolean b) {
    both = b;
    indexer = i;
    addRequirements(indexer);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (both) indexer.MoveUp();
    else indexer.moveStageOne();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}