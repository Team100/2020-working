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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

  @Override
  public void robotInit() {
    m_myRobot = new DifferentialDrive(new WPI_TalonSRX(0), new WPI_TalonSRX(15));
    m_Stick = new Joystick(0);
    m_potentiometer=new AnalogInput(1);
  }

  @Override
  public void teleopPeriodic() {
  if (isArcadeDrive){
    m_myRobot.arcadeDrive(m_Stick.getY()/1.5, m_Stick.getX() / 1.5 * -1);
  }
  else{
    m_myRobot.tankDrive(m_Stick.getY()/2, m_Stick.getThrottle()/2);
  }
   // m_myRobot.arcadeDrive(m_Stick.getY()/1.5, m_Stick.getTwist()/1.5*-1);
  // System.out.println(m_potentiometer.getVoltage());
  SmartDashboard.putNumber("pot", m_potentiometer.getVoltage());
  SmartDashboard.putNumber("left_Y", m_Stick.getY());
  SmartDashboard.putNumber("right_X", m_Stick.getThrottle());

  preferences = Preferences.getInstance();
  isArcadeDrive = preferences.getBoolean("aracdeDrive", true);

}

 
}
