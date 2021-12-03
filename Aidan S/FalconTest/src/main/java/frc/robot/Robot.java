/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private WPI_TalonFX left = new WPI_TalonFX(0);
  private WPI_TalonFX right = new WPI_TalonFX(15);
  private Joystick leftController;
  private Joystick rightController;
  double maxOutput = 0.75;

  @Override
  public void robotInit() {
    left.configFactoryDefault();
    right.configFactoryDefault();
    left.setNeutralMode(NeutralMode.Brake);
    right.setNeutralMode(NeutralMode.Brake);
    left.configPeakOutputForward(1);
    right.configPeakOutputForward(1);
    left.configPeakOutputReverse(-1);
    right.configPeakOutputReverse(-1);
    left.setInverted(InvertType.InvertMotorOutput);
    right.setInverted(InvertType.InvertMotorOutput);
    m_myRobot = new DifferentialDrive(left, right);
    leftController = new Joystick(0);
    rightController = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    maxOutput = -leftController.getZ() / 2 + 0.5;
    SmartDashboard.putNumber("modifier", leftController.getZ());


    m_myRobot.arcadeDrive(-leftController.getY()*maxOutput, rightController.getX()*maxOutput);
  }
}
