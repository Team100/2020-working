/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  public TalonSRX leftTalon;
  public TalonSRX rightTalon;
  public Joystick joystick;
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    leftTalon = new TalonSRX(0);
    rightTalon = new TalonSRX(15);
    
    leftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    rightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

    leftTalon.setInverted(true);
    rightTalon.setInverted(false);

    leftTalon.setSensorPhase(false);
    rightTalon.setSensorPhase(false);

    joystick = new Joystick(0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    this.leftTalon.set(ControlMode.PercentOutput, this.joystick.getRawAxis(1));
    this.rightTalon.set(ControlMode.PercentOutput, this.joystick.getRawAxis(1));
    SmartDashboard.putNumber("Left Sensor Velocity", this.leftTalon.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Right Sensor Velocity", this.rightTalon.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Joystick Value", this.joystick.getRawAxis(1));

   
  }
}
