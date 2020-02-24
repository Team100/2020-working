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
import edu.wpi.first.wpilibj2.command.button.InternalButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.commands.Do_State_1;
import frc.robot.commands.Do_State_2;
import frc.robot.commands.Do_State_3;
import frc.robot.commands.Do_State_4;
import frc.robot.commands.Do_State_5;

/**
 * Add your docs here.
 */
public class SampleStateMachine implements Sendable {
    public enum StateEnum {
        INIT, STATE_1, STATE_2, STATE_3, STATE_4, STATE_5, COMPLETE, STATE_ERROR
    }

    public class State1Trigger extends Trigger {
        SampleStateMachine machine;

        public State1Trigger(SampleStateMachine machine) {
            this.machine = machine;
        }
        @Override
        public boolean get() {
            return false;
        }
        
    }

    public class State2Trigger  extends Trigger{
        SampleStateMachine machine;

        public State2Trigger(SampleStateMachine machine) {
            this.machine = machine;
        }
        
        @Override
        public boolean get() {
            return false;
        }
    }

    public class State3Trigger  extends Trigger{
        SampleStateMachine machine;

        public State3Trigger(SampleStateMachine machine) {
            this.machine = machine;
        }

        @Override
        public boolean get() {
            return false;
        }
    }

    public class State4Trigger extends Trigger {
        SampleStateMachine machine;

        public State4Trigger(SampleStateMachine machine) {
            this.machine = machine;
        }        
        
        @Override
        public boolean get() {
            return false;
        }
    }

    public class State5Trigger extends Trigger {
        SampleStateMachine machine;

        public State5Trigger(SampleStateMachine machine) {
            this.machine = machine;
        } 

        @Override
        public boolean get() {
            return false;
        }
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
    private final Trigger m_state_Trigger_1 = new State1Trigger(this);
    private final Trigger m_state_Trigger_2 = new State2Trigger(this);
    private final Trigger m_state_Trigger_3 = new State3Trigger(this);
    private final Trigger m_state_Trigger_4 = new State4Trigger(this);
    private final Trigger m_state_Trigger_5 = new State5Trigger(this);
    private final Trigger m_error_Trigger = new StateErrorTrigger(this);
    private final InternalButton m_internalButton_1 = new InternalButton();
    private final InternalButton m_internalButton_2 = new InternalButton();
    private final InternalButton m_internalButton_3 = new InternalButton();
    private final InternalButton m_internalButton_4 = new InternalButton();
    private final InternalButton m_internalButton_5 = new InternalButton();
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
        m_internalButton_1.whenPressed(new Do_State_2(this, m_subsystemB));
        m_internalButton_2.whenPressed(new Do_State_3(this, m_subsystemA, m_subsystemB));
        m_internalButton_3.whenPressed(new Do_State_4(this, m_subsystemA, m_subsystemB));
        m_internalButton_4.whenPressed(new Do_State_5(this, m_subsystemA, m_subsystemB));
        m_internalButton_5.whenPressed(() -> startTimer());
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

    public void setInternalButton_1(boolean state) {
        m_internalButton_1.setPressed(state);
    }

    public void setInternalButton_2(boolean state) {
        m_internalButton_2.setPressed(state);
    }
    public void setInternalButton_3(boolean state) {
        m_internalButton_3.setPressed(state);
    }
    public void setInternalButton_4(boolean state) {
        m_internalButton_4.setPressed(state);
    }
    public void setInternalButton_5(boolean state) {
        m_internalButton_5.setPressed(state);
    }


    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addStringProperty ("CurrentState", this::getCurrentStateString, null);
        builder.addDoubleProperty ("Timer", this.m_Timer::get, null);
        builder.addBooleanProperty ("Error Trigger", m_error_Trigger::get, null);
        builder.addBooleanProperty ("InternalButton1", m_internalButton_1::get, null);
        builder.addBooleanProperty ("InternalButton2", m_internalButton_2::get, null);
        builder.addBooleanProperty ("InternalButton3", m_internalButton_3::get, null);
        builder.addBooleanProperty ("InternalButton4", m_internalButton_4::get, null);
        builder.addBooleanProperty ("InternalButton5", m_internalButton_5::get, null);

    }

    
}
