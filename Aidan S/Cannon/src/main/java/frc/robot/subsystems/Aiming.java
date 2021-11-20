/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Aiming extends SubsystemBase {
  DigitalInput topLimit;
  DigitalInput bottomLimit;
  TalonFX angleMotor;
  /**
   * Creates a new Aiming.
   */
  public Aiming() {
    topLimit = new DigitalInput(Constants.Aiming.TOP_LIMIT_DIO_ID);
    bottomLimit = new DigitalInput(Constants.Aiming.BOTTOM_LIMIT_DIO_ID);
    angleMotor = new TalonFX(Constants.Aiming.MOTOR_CAN_ID);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void percentOutPut(double percent) {
    angleMotor.set(ControlMode.PercentOutput, percent);
  }
}
