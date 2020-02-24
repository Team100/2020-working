/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SampleStateMachine;
import frc.robot.subsystems.SubsystemA;

public class Do_State_1 extends CommandBase {
  /**
   * Creates a new Do_State_1.
   */
  private final SampleStateMachine m_machine;
  private final SubsystemA m_subsystemA;
  private final Timer m_localTimer = new Timer();

  public Do_State_1(SampleStateMachine machine, SubsystemA subsysA) {
    m_machine = machine;
    m_subsystemA = subsysA;
    addRequirements(subsysA);
    
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_machine.setCurrentState(SampleStateMachine.StateEnum.STATE_1);
    m_localTimer.reset();
    m_localTimer.start();
    m_machine.setInternalButton_1(false);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystemA.setMotorOutput(6.0); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_machine.setInternalButton_1(true);
    m_subsystemA.setMotorOutput(0.0);
    m_machine.setCurrentState (SampleStateMachine.StateEnum.COMPLETE);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_localTimer.get() > Constants.TIMEOUT);
  }
}
