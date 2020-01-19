/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.frclib.AutoHelperFunctions.NeoCollection;


public class Drivetrain extends SubsystemBase {

////////////////////////////////////////////////////////////
// Drivetrain.java                                        //
////////////////////////////////////////////////////////////
//                                                        //
// This subsystem is designed around the 2020 NEO Minibot //
// and is designed to run the SPARK MAX autonomous        //
// based on the blog post at                              //
// blog.alexbeaver.com/wpilib-trajectory-guide/           //
// as well as the WPILib Trajectory Talon SRX Robot       //
//                                                        //
////////////////////////////////////////////////////////////


  /**
   * A collection of motor controller, encoder, and PID for the left master motor
   */
  public NeoCollection leftMaster;

  /**
   * A collection of motor controller, encoder, and PID for the right master motor
   */
  public NeoCollection rightMaster;
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    leftMaster = new NeoCollection(Constants.LeftLeader.CAN_ID, Constants.LeftLeader.KP, Constants.LeftLeader.KI, Constants.LeftLeader.KD, Constants.LeftLeader.KIZ, Constants.LeftLeader.KF, -1, 1, 100);
    rightMaster = new NeoCollection(Constants.RightLeader.CAN_ID, Constants.RightLeader.KP, Constants.RightLeader.KI, Constants.RightLeader.KD, Constants.RightLeader.KIZ, Constants.RightLeader.KF, -1, 1, 100);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
