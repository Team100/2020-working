/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;




/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_Stick;
  private AnalogInput m_potentiometer;
  private Preferences preferences;
  private boolean isArcadeDrive;
  private double Velocity;
  private boolean rightStick;
  private static final int leftDeviceID = 1; 
  private static final int rightDeviceID = 15;
  private CANSparkMax m_leftMotor;
  private CANSparkMax m_rightMotor;
  private int firmwareVersion;
  private Button buttonY;


  



  
  


  @Override
  public void robotInit() {
   // m_myRobot = new DifferentialDrive(new WPI_TalonSRX(0), new WPI_TalonSRX(15));
    m_Stick = new Joystick(0);
    buttonY = new JoystickButton(m_Stick, 4);
    m_potentiometer=new AnalogInput(1); 
    preferences = Preferences.getInstance();
    isArcadeDrive = preferences.getBoolean("arcadeDrive", true);
    preferences.putBoolean("arcadeDrive", isArcadeDrive);
    Velocity = preferences.getDouble("Velocity", 1.5);
    if (Velocity > 2) {
      Velocity = 2;
    } 
    if (Velocity < 1) {
      Velocity = 1;
    }
    preferences.putDouble("Velocity", Velocity);
    rightStick = preferences.getBoolean("rightStick", true);
    preferences.putBoolean("rightStick", rightStick);
    m_leftMotor = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
    m_rightMotor = new CANSparkMax(rightDeviceID, MotorType.kBrushless);
    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    //firmwareVersion = com.revrobotics.CANSparkMaxLowLevel.getFirmwareVersion();
    firmwareVersion = m_leftMotor.getFirmwareVersion();
    
    

    
  }

  @Override
  public void teleopPeriodic() {

  if (isArcadeDrive) {
    m_myRobot.arcadeDrive(m_Stick.getY()/Velocity, m_Stick.getX() / Velocity * -1);
  }
  else{
    m_myRobot.tankDrive(m_Stick.getY()/Velocity, m_Stick.getThrottle()/Velocity);
  }
  if (rightStick) {
    m_myRobot.arcadeDrive(m_Stick.getThrottle()/Velocity, m_Stick.getTwist()/Velocity);

  }
  if (buttonY.get()) {
    m_rightMotor.getEncoder().setPosition(0);
    m_leftMotor.getEncoder().setPosition(0);
  }
  
   // m_myRobot.arcadeDrive(m_Stick.getY()/1.5, m_Stick.getTwist()/1.5*-1);
  // System.out.println(m_potentiometer.getVoltage());
  SmartDashboard.putNumber("pot", m_potentiometer.getVoltage());
  SmartDashboard.putNumber("left_Y", m_Stick.getY());
  SmartDashboard.putNumber("right_X", m_Stick.getThrottle());
  SmartDashboard.putNumber("FWVersion", firmwareVersion);
  SmartDashboard.putNumber("rightPosition", m_rightMotor.getEncoder().getPosition());
  SmartDashboard.putNumber("leftPosition", m_leftMotor.getEncoder().getPosition());

  
}
 @Override
 public void teleopInit() {
  preferences = Preferences.getInstance();
  isArcadeDrive = preferences.getBoolean("arcadeDrive", true);
  Velocity = preferences.getDouble("Velocity", 1.5);
  rightStick = preferences.getBoolean("rightStick", true);
  if (Velocity > 2) {
    Velocity = 2;
  } 
  if (Velocity < 1) {
    Velocity = 1;
  }
 }
 @Override
 public void autonomousInit() {

 }
}

  

  
