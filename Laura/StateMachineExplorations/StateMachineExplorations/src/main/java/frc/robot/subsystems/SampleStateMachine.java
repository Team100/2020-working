/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Trigger;
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

    }

    private boolean stateMachineIsActive = false;
    private StateEnum m_currentState = StateEnum.INIT;
    private final Trigger m_state_Trigger_1 = new State1Trigger();
    private final Trigger m_state_Trigger_2 = new State2Trigger();
    private final Trigger m_state_Trigger_3 = new State3Trigger();
    private final Trigger m_state_Trigger_4 = new State4Trigger();
    private final Trigger m_state_Trigger_5 = new State5Trigger();
    private final Trigger m_error_Trigger = new StateErrorTrigger();

    public SampleStateMachine() {
        m_state_Trigger_1.whenActive(new Do_State_1());
        m_state_Trigger_2.whenActive(new Do_State_2());
        m_state_Trigger_3.whenActive(new Do_State_3());
        m_state_Trigger_4.whenActive(new Do_State_4());
        m_state_Trigger_5.whenActive(new Do_State_5());
    }

    public StateEnum getCurrentState() {
        return m_currentState;
    }

    public void setCurrentState(StateEnum state) {
        m_currentState = state;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        String name = this.getClass().getSimpleName();
        SendableRegistry.addLW(this, name, name);
        

    }

    
}
