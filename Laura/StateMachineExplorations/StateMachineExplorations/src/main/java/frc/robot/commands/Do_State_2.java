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
import frc.robot.subsystems.SubsystemB;

public class Do_State_2 extends CommandBase {
  private final SampleStateMachine m_machine;
  private final SubsystemB m_subsystemB;
  private final Timer m_localTimer = new Timer();
/**
   * Creates a new Do_State_2.
   */
  public Do_State_2(SampleStateMachine machine, SubsystemB subsysB) {
    m_machine = machine;
    m_subsystemB = subsysB;
    addRequirements(subsysB);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_machine.setCurrentState(SampleStateMachine.StateEnum.STATE_2);
    m_localTimer.reset();
    m_localTimer.start();
    m_machine.setInternalButton_2(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystemB.setMotorOutput(6.0); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_machine.setInternalButton_2(true);
    m_subsystemB.setMotorOutput(0.0);
    m_machine.setCurrentState (SampleStateMachine.StateEnum.COMPLETE);
}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_localTimer.get() > Constants.TIMEOUT);

  }
}
