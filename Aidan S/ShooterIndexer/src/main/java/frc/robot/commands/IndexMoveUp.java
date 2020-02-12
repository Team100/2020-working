package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexMoveUp extends CommandBase {
  private boolean both;

  public IndexMoveUp(boolean b) {
    both = b;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (both) Indexer.MoveUp();
    else Indexer.moveStageOne();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}