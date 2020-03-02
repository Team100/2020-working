/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Gearbox extends SubsystemBase {
  private final TalonFX f1;
  private final TalonFX f2;
  private double setpoint = 0;


  /**
   * Creates a new ExampleSubsystem.
   */
  public Gearbox() {
    f1 = new TalonFX(Constants.Gearbox.FALCON_1_CANID);
    f2 = new TalonFX(Constants.Gearbox.FALCON_2_CANID);
    f2.follow(f1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    f1.set(ControlMode.PercentOutput, setpoint);
  }

  public void set(double s) {
    setpoint = s;
  }
}
