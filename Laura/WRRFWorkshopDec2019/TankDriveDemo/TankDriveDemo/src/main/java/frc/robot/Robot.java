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
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  // private static final boolean IS_VICTOR = true;
  // private static final boolean IS_NEO = false;
  // private static final boolean IS_TALON_SRX = false;
  // private static final int LEFT_VICTOR = 2;
  // private static final int RIGHT_VICTOR = 3;
  // private static final int LEFT_NEO = 1;
  // private static final int RIGHT_NEO =15;
  // private static final int LEFT_TALON_SRX = 0;
  // private static final int RIGHT_TALON_SRX = 15;

    private DifferentialDrive m_myRobot;
  private Joystick m_gamepad;
  private SpeedController m_leftMotor;
  private SpeedController m_rightMotor;
  private double m_throttleRatio = 1.0;
  

  @Override
  public void robotInit() {
    String controllerType = Preferences.getInstance().getString("SpeedControllerType", "Victor");
    int leftID = Preferences.getInstance().getInt("LeftID", 2);
    int rightID = Preferences.getInstance().getInt("RightID", 3);
    m_throttleRatio = Preferences.getInstance().getDouble("ThrottleRatio", 1.0);
    
    if (controllerType == CANSparkMax.class.getName()) {
      m_leftMotor = new CANSparkMax(leftID, MotorType.kBrushless);
      m_rightMotor = new CANSparkMax(rightID, MotorType.kBrushless);
    } 
    else if (controllerType == WPI_TalonSRX.class.getName()) {
      m_leftMotor = new WPI_TalonSRX(leftID);
      m_rightMotor = new WPI_TalonSRX(rightID);
    }
    else {
      m_leftMotor = new Victor(leftID);
      m_rightMotor = new Victor(rightID);
    }
    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_gamepad = new Joystick(0);
    
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(m_gamepad.getY()*m_throttleRatio, m_gamepad.getThrottle()*m_throttleRatio);
    SmartDashboard.putNumber ("LeftMotorVoltage", m_leftMotor.get());
    SmartDashboard.putNumber ("RightMotorVoltage", m_rightMotor.get());
    SmartDashboard.putString ("MotorType", m_leftMotor.getClass().getName());
    SmartDashboard.putNumber ("ThrottleRatio", m_throttleRatio);
  }
}
