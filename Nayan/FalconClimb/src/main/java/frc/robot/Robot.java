/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private WPI_TalonFX left = new WPI_TalonFX(0);
  private WPI_TalonFX right = new WPI_TalonFX(15);
  private Joystick controller;

  @Override
  public void robotInit() {
    left.configFactoryDefault();
    right.configFactoryDefault();
    left.setNeutralMode(NeutralMode.Coast);
    right.setNeutralMode(NeutralMode.Coast);
    left.configPeakOutputForward(0.5);
    right.configPeakOutputForward(0.5);
    left.configPeakOutputReverse(-0.5);
    right.configPeakOutputReverse(-0.5);
    m_myRobot = new DifferentialDrive(left, right);
    controller = new Joystick(0);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.arcadeDrive(controller.getY()*0.5, -controller.getZ()*0.5);
  }
}
