/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private static final boolean m_is_Victor = true;
  private static final boolean m_is_NEO = false;
  private static final boolean m_is_TalonSRX = false;
  private static final int LEFT_VICTOR = 2;
  private static final int RIGHT_VICTOR = 3;
  private static final int LEFT_NEO = 1;
  private static final int RIGHT_NEO =15;
  private static final int LEFT_TALON_SRX = 0;
  private static final int RIGHT_TALON_SRX = 15;

    private DifferentialDrive m_myRobot;
  private Joystick m_gamepad;
  private SpeedController m_leftMotor;
  private SpeedController m_rightMotor;
  

  @Override
  public void robotInit() {
    if (m_is_Victor) {
      m_leftMotor = new Victor(LEFT_VICTOR);
      m_rightMotor = new Victor(RIGHT_VICTOR);
    }
    else if (m_is_NEO) {
      m_leftMotor = new CANSparkMax(LEFT_NEO, MotorType.kBrushless);
      m_rightMotor = new CANSparkMax(RIGHT_NEO, MotorType.kBrushless);
    } else if (m_is_TalonSRX) {
      m_leftMotor = new WPI_TalonSRX(LEFT_TALON_SRX);
      m_rightMotor = new WPI_TalonSRX(RIGHT_TALON_SRX);
    }
    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_gamepad = new Joystick(0);
    
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(m_gamepad.getY(), m_gamepad.getThrottle());
  }
}
