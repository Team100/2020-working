/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.commands.Do_State_1;
import frc.robot.commands.Do_State_2;
import frc.robot.commands.Do_State_3;
import frc.robot.commands.Do_State_4;
import frc.robot.commands.Do_State_5;
import frc.robot.triggers.State1Trigger;
import frc.robot.triggers.State2Trigger;
import frc.robot.triggers.State3Trigger;
import frc.robot.triggers.State4Trigger;
import frc.robot.triggers.State5Trigger;

/**
 * Add your docs here.
 */
public class SampleStateMachine implements Sendable {
    public enum StateEnum {
        INIT, STATE_1, STATE_2, STATE_3, STATE_4, STATE_5, COMPLETE, STATE_ERROR
    }

    public class StateErrorTrigger extends Trigger {
        SampleStateMachine machine;

        public StateErrorTrigger(SampleStateMachine machine) {
            this.machine = machine;
        }

        @Override
        public boolean get() {
            boolean finished = machine.getTimerVal() > Constants.TIMEOUT;
            return finished;
        }

        

    }

    private boolean stateMachineIsActive = false;
    private Timer m_Timer = new Timer();
    private StateEnum m_currentState = StateEnum.INIT;
    private final Trigger m_state_Trigger_1 = new State1Trigger();
    private final Trigger m_state_Trigger_2 = new State2Trigger();
    private final Trigger m_state_Trigger_3 = new State3Trigger();
    private final Trigger m_state_Trigger_4 = new State4Trigger();
    private final Trigger m_state_Trigger_5 = new State5Trigger();
    private final Trigger m_error_Trigger = new StateErrorTrigger(this);
    private final SubsystemA m_subsystemA;
    private final SubsystemB m_subsystemB;

    public SampleStateMachine(SubsystemA asubsystem, SubsystemB bsubsystem) {
        m_subsystemA = asubsystem;
        m_subsystemB = bsubsystem;
        String name = this.getClass().getSimpleName();
        SendableRegistry.addLW(this, name, name);
        
        // m_state_Trigger_1.whenActive(new Do_State_1(this));
        // m_state_Trigger_2.whenActive(new Do_State_2());
        // m_state_Trigger_3.whenActive(new Do_State_3());
        // m_state_Trigger_4.whenActive(new Do_State_4());
        // m_state_Trigger_5.whenActive(new Do_State_5());
        m_error_Trigger.whenActive(new Do_State_1(this, m_subsystemA));
        startTimer();
    }

    public StateEnum getCurrentState() {
        return m_currentState;
    }

    public void setCurrentState(StateEnum state) {
        m_currentState = state;
    }

    public void startTimer() {
        m_Timer.reset();
        m_Timer.start();
    }

    public double getTimerVal() {
        return m_Timer.get();
    }

    public String getCurrentStateString() {
        return m_currentState.toString();
    }


    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addStringProperty ("CurrentState", this::getCurrentStateString, null);
        builder.addDoubleProperty ("Timer", this.m_Timer::get, null);
        builder.addBooleanProperty ("Error Trigger", m_error_Trigger::get, null);
    }

    
}
