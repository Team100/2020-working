/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  Servo doughnuts;

  /**
   * Creates a new ExampleSubsystem
   */
  public ExampleSubsystem() {
doughnuts = new Servo(1);

SmartDashboard.putNumber("Steak", 0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    doughnuts.setAngle((int)SmartDashboard.getNumber("Steak", -60));
    SmartDashboard.putNumber("servo output", doughnuts.getAngle());
  }
}
