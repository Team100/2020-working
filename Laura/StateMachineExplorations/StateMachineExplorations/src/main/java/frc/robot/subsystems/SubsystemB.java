/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SubsystemB extends SubsystemBase {
  private VictorSP m_motor = new VictorSP(Constants.MOTOR_B_PORT);
  private DigitalInput m_dig_in_1 = new DigitalInput(Constants.DIG_IN_PORT_B1);
  private DigitalInput m_dig_in_2 = new DigitalInput(Constants.DIG_IN_PORT_B2);
  private AnalogInput m_analog_in = new AnalogInput(Constants.ANALOG_IN_PORTB);
  
  public SubsystemB() {
    // Add stuff for Live Windows (Test Mode)
    addChild("Command", this);
    addChild("DigIn1", m_dig_in_1);
    addChild("DigIn2", m_dig_in_2);
    addChild("AnIn", m_analog_in);
    addChild("Motor", m_motor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorOutput(double volts) {
    m_motor.setVoltage (volts);
  }

  public boolean getDigIn1() {
    return m_dig_in_1.get();
  }

  public boolean getDigIn2() {
    return m_dig_in_2.get();
  }

  public double getAnInVolts() {
    return m_analog_in.getVoltage();
  }
}
