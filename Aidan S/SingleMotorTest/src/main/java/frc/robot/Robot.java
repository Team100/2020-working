/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This sample program shows how to control a motor using a joystick. In the
 * operator control part of the program, the joystick is read and the value is
 * written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and speed controller inputs also
 * range from -1 to 1 making it easy to work together.
 */
public class Robot extends TimedRobot {
  private static final int kMotorPort = 11;
  private static final int kJoystickPort = 0;

  private double positionSetpoint;
  // private double p = 0;
  // private double i = 0;
  // private double d = 0;
  // private double f = 0;

  private WPI_TalonSRX mc;
  private Joystick m_joystick;

  @Override
  public void robotInit() {
    mc = new WPI_TalonSRX(kMotorPort);
    m_joystick = new Joystick(kJoystickPort);

    SmartDashboard.putNumber("Setpoint", positionSetpoint);

    // mc.configFactoryDefault();
    mc.setNeutralMode(NeutralMode.Brake);
    mc.configAllowableClosedloopError(0, 5);
    mc.setSensorPhase(true);
    mc.configNominalOutputForward(0);
    mc.configNominalOutputReverse(0);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Encoder", mc.getSelectedSensorPosition());
    SmartDashboard.putNumber("Voltage", mc.getMotorOutputVoltage());
    positionSetpoint = SmartDashboard.getNumber("Setpoint", 0);

    // p = SmartDashboard.getNumber("P", p);
    // i = SmartDashboard.getNumber("I", i);
    // d = SmartDashboard.getNumber("D", d);
    // f = SmartDashboard.getNumber("F", f);
    // SmartDashboard.putNumber("P", p);
    // SmartDashboard.putNumber("I", i);
    // SmartDashboard.putNumber("D", d);
    // SmartDashboard.putNumber("F", f);
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Error", mc.getClosedLoopError());
    
    // mc.config_kI(0, p);
    // mc.config_kI(0, i);
    // mc.config_kD(0, d);
    // mc.config_kF(0, f);

    mc.set(ControlMode.Position, positionSetpoint);
  }
}
