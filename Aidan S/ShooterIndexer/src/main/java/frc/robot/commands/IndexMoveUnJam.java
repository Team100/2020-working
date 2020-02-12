package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexMoveUnJam extends CommandBase {
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Indexer.unJam();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
