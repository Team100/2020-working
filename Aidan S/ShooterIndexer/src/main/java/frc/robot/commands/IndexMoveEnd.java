package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexMoveEnd extends CommandBase {
  private Indexer indexer;

  public IndexMoveEnd(Indexer i) {
    indexer = i;
    addRequirements(indexer);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    indexer.TillStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}