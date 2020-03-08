/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.HUDSystem.Positions;
import frc.robot.Light.Colors;
import frc.robot.Light.Speeds;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private Joystick m_gamepad;
  private boolean m_gamepadToggle = false;
  private int m_gamepadCount = 0;
  private HUDSystem HUD;

  @Override
  public void robotInit() {
    m_myRobot = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(1));
    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
    // m_gamepad = new Joystick (2);
    HUD = new HUDSystem(new Joystick(2));
    HUD.changeLight(Positions.TOPLEFT, Colors.AMBER, Speeds.SLOW);
    HUD.changeLight(Positions.TOPRIGHT, Colors.AMBER, Speeds.SLOW);
    HUD.changeLight(Positions.BOTTOMLEFT, Colors.GREEN, Speeds.FAST);
    HUD.changeLight(Positions.BOTTOMRIGHT, Colors.RED, Speeds.STEADY);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
  }

  @Override
  public void robotPeriodic() {
    // old blink system
  //   m_gamepadCount += 1;
  //   /* play with rate to get best fast, slow blink */
  //   /* time of 50 gives a blink per second */
  //   if (m_gamepadCount > 50) {
  //     m_gamepadCount = 0;
  //     if (m_gamepadToggle) {
  //       int hex1 = 0X03;
  //       int hex2 = 0x04;
  //       int hex3 = hex1 + hex2;
  //       System.out.println(hex3);
  //       m_gamepad.setOutputs(hex3);
  //        /* first 6 bits (0x3f) control */
  //     } else {
  //       m_gamepad.setOutputs(0x00);
  //     }
  //   }
  //   m_gamepadToggle = !m_gamepadToggle;
  // new blink system
    HUD.periodicRun();
  }
  
}
