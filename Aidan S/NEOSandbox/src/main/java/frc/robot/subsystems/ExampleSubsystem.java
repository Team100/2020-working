// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  private CANSparkMax sparkMax;
  private SparkMaxPIDController smPID;

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    sparkMax = new CANSparkMax(1, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getPercentOutput() {
    return sparkMax.get();
  }

  public double getVelocity() {
    return sparkMax.getEncoder().getVelocity();
  }

  public double getPosition() {
    return sparkMax.getEncoder().getPosition();
  }

  public void setPercentOutput(double percent) {
    sparkMax.set(percent);
  }

  public void setVelocitySetpoint(double velocity) {
    smPID.setReference(velocity, ControlType.kVelocity);
  }

  public void setPositionSetpoint(double position) {
    smPID.setReference(position, ControlType.kPosition);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
