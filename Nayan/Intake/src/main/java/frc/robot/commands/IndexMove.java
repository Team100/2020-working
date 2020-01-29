
package frc.robot.commands;

import frc.robot.*;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class IndexMove extends CommandBase {
  // private final Indexer m_subsystem;


  // public IndexMove(Indexer subsystem) {
  // //   m_subsystem = subsystem;
  // //   // Use addRequirements() here to declare subsystem dependencies.
  //   addRequirements(subsystem);
    
  // }

  public IndexMove() {
    Indexer.foward();
    SmartDashboard.putString("MoveFowardCalled", "yes");
}

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putString("initialized", "yes");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      Indexer.foward();
      SmartDashboard.putString("MoveFowardCalled", "yes");
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
