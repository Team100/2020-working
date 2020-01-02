/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Vector;

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
  private static final int LEFT_VICTOR = 2;
  private static final int RIGHT_VICTOR = 3;
  // private static final int LEFT_NEO = 1;
  // private static final int RIGHT_NEO =15;
  // private static final int LEFT_TALON_SRX = 0;
  // private static final int RIGHT_TALON_SRX = 15;
  private static final String CONTROLLER_TYPE_KEY = "SpeedControllerType";
  private static final String LEFT_ID_KEY = "LeftID";
  private static final String RIGHT_ID_KEY = "RightID";
  private static final String THROTTLE_RATIO_KEY = "ThrottleRatio";

    private DifferentialDrive m_myRobot;
  private Joystick m_gamepad;
  private SpeedController m_leftMotor;
  private SpeedController m_rightMotor;
  private double m_throttleRatio = 1.0;
  

  @Override
  public void robotInit() {
    getFromPrefs();
    
    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_gamepad = new Joystick(0);
    
  }

  private void getFromPrefs() {
    Vector<String> keys = Preferences.getInstance().getKeys();
    String controllerType;
    int leftID;
    int rightID;

    if(!keys.contains(CONTROLLER_TYPE_KEY)){
      controllerType = "Victor";
      Preferences.getInstance().putString(CONTROLLER_TYPE_KEY, controllerType);      
    } else {
      controllerType = Preferences.getInstance().getString(CONTROLLER_TYPE_KEY, "Victor");
    }
   
    if (!keys.contains(LEFT_ID_KEY)) {
      leftID = LEFT_VICTOR;
      Preferences.getInstance().putInt(LEFT_ID_KEY, leftID);
    } else {
      leftID = Preferences.getInstance().getInt(LEFT_ID_KEY, LEFT_VICTOR);
    }
    
    if (!keys.contains(RIGHT_ID_KEY)) {
      rightID = RIGHT_VICTOR;
      Preferences.getInstance().putInt(RIGHT_ID_KEY, rightID);
    } else {
      rightID = Preferences.getInstance().getInt(RIGHT_ID_KEY, RIGHT_VICTOR);
    }
    
    if (!keys.contains(THROTTLE_RATIO_KEY)) {
      m_throttleRatio = 1.0;
      Preferences.getInstance().putDouble(THROTTLE_RATIO_KEY, m_throttleRatio);
    } else {
      m_throttleRatio = Preferences.getInstance().getDouble(THROTTLE_RATIO_KEY, 1.0);
    }
        
    if (controllerType.compareTo(CANSparkMax.class.getSimpleName()) == 0) {
      m_leftMotor = new CANSparkMax(leftID, MotorType.kBrushless);
      m_rightMotor = new CANSparkMax(rightID, MotorType.kBrushless);
    } 
    else if (controllerType.compareTo(WPI_TalonSRX.class.getSimpleName())  == 0){
      m_leftMotor = new WPI_TalonSRX(leftID);
      m_rightMotor = new WPI_TalonSRX(rightID);
    }
    else {
      m_leftMotor = new Victor(leftID);
      m_rightMotor = new Victor(rightID);
    }
  }

  @Override
  public void teleopInit() {
    m_throttleRatio = Preferences.getInstance().getDouble(THROTTLE_RATIO_KEY, 1.0);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(m_gamepad.getY()*m_throttleRatio, m_gamepad.getThrottle()*m_throttleRatio, false);
    SmartDashboard.putNumber ("LeftMotorVoltage", m_leftMotor.get());
    SmartDashboard.putNumber ("RightMotorVoltage", m_rightMotor.get());
    SmartDashboard.putString ("MotorType", m_leftMotor.getClass().getSimpleName());
    SmartDashboard.putNumber ("ThrottleRatio", m_throttleRatio);
  }
}
