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
import frc.robot.subsystems.SubsystemB;

public class Do_State_5 extends CommandBase {
  private final SampleStateMachine m_machine;
  private final SubsystemA m_subsystemA;
  private final SubsystemB m_subsystemB;
  private final Timer m_localTimer = new Timer();

  private double m_desiredVoltage = 0.0;
  private double m_desiredVoltageIncr = Constants.MTR_INCRAMENT;
  private boolean m_do_up = true;
  /**
   * Creates a new Do_State_5.
   */
  public Do_State_5(SampleStateMachine machine, SubsystemA subsysA, SubsystemB subsysB) {
    m_machine = machine;
    m_subsystemA = subsysA;
    m_subsystemB = subsysB;
    addRequirements(subsysA, subsysB);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_machine.setCurrentState(SampleStateMachine.StateEnum.STATE_5);
    m_localTimer.reset();
    m_localTimer.start();
    m_machine.setInternalButton_5(false);
    m_desiredVoltage = 0.0;
    m_do_up = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_do_up) {
      m_desiredVoltage += m_desiredVoltageIncr;
      if (m_desiredVoltage > 12.0) {
        m_desiredVoltage = 12.0;
        m_do_up = false;
      }
    } else {
      m_desiredVoltage -= m_desiredVoltageIncr;
      if (m_desiredVoltage < 0.0) {
        m_desiredVoltage = 0.0;
        m_do_up = true;
      }
    }
    m_subsystemA.setMotorOutput(m_desiredVoltage); 
    m_subsystemB.setMotorOutput(m_desiredVoltage); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_machine.setInternalButton_5(true);
    m_subsystemA.setMotorOutput(0.0);
    m_subsystemB.setMotorOutput(0.0);
    m_machine.setCurrentState (SampleStateMachine.StateEnum.COMPLETE);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_localTimer.get() > Constants.TIMEOUT);
  }
}