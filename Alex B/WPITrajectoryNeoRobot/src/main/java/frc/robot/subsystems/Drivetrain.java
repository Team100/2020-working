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


  public CANSparkMax leftMaster;
  public CANSparkMax rightMaster;
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {

    ///////////////////////////////////////////////////////////////
    // WARNING                                                   //
    ///////////////////////////////////////////////////////////////
    // DO NOT CHANGE THE MOTOR TYPE IN HERE                      //
    // IT WILL RESULT IN CATASTROPHIC EFFECTS                    //
    // TODO MAKE SURE THAT THESE VALUES ARE MotorType.kBrushless //
    ///////////////////////////////////////////////////////////////

    leftMaster = new CANSparkMax(Constants.LeftLeader.CAN_ID, MotorType.kBrushless);
    rightMaster = new CANSparkMax(Constants.RightLeader.CAN_ID, MotorType.kBrushless);

    ///////////////////////////////////////////////////////////////
    // INFO                                                      //
    ///////////////////////////////////////////////////////////////
    // This code will configure a Spark MAX Motor Controller.    //
    // It uses Constants.java as the base for the configuration. //
    // Please make any changes in Constants.java                 //
    ///////////////////////////////////////////////////////////////



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
