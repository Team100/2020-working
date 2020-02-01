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

public class Cameratiltsubsystem extends SubsystemBase {
  private int setpoint;
  /**
   * Creates a new ExampleSubsystem.
   */
  private final Servo m_tiltmotor;

  public Cameratiltsubsystem() {
    m_tiltmotor = new Servo(2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_tiltmotor.setAngle(setpoint);
    SmartDashboard.putNumber("Servo Angle", setpoint);
  }

  public void setsetpoint(int s) {
    setpoint = s;
  }
}
