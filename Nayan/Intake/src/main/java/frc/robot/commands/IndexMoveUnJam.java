package frc.robot.commands;

import frc.robot.*;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class IndexMoveUnJam extends CommandBase {
  // private final Indexer m_subsystem;


  // public IndexMove(Indexer subsystem) {
  // //   m_subsystem = subsystem;
  // //   // Use addRequirements() here to declare subsystem dependencies.
  //   addRequirements(subsystem);
    
  // }

  public IndexMoveUnJam() {
    Indexer.unJam();
}

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Indexer.unJam();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //    leftSpx.set(ControlMode.PercentOutput, 0.00);
    //rightSpx.set(ControlMode.PercentOutput, 0.00);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
