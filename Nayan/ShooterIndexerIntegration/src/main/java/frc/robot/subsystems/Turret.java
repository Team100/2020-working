/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  public TalonFX talon = new TalonFX(Constants.Turret.CAN_ID);
  private double target = 0;

  /**
   * Creates a new Turret.
   */
  public Turret() {
    //configure motor
    talon.configFactoryDefault();
    talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    talon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    talon.config_kP(0, Constants.Turret.kP);
    talon.config_kI(0, Constants.Turret.kI);
    talon.config_kD(0, Constants.Turret.kD);
    talon.config_kF(0, Constants.Turret.kF);
    talon.configClosedloopRamp(Constants.Turret.RAMP_SECONDS_TO_FULL);
    talon.configPeakOutputForward(Constants.Turret.PEAK_OUTPUT);
    talon.configPeakOutputReverse(-Constants.Turret.PEAK_OUTPUT);
    talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    talon.setSensorPhase(true);
    talon.setNeutralMode(NeutralMode.Brake);
    talon.configAllowableClosedloopError(0, Constants.Turret.ALLOWABLE_ERROR);
    talon.configNominalOutputForward(Constants.Turret.NOMINAL_VOLTAGE_FORWARD/talon.getBusVoltage());
    talon.configNominalOutputReverse(Constants.Turret.NOMINAL_VOLTAGE_REVERSE/talon.getBusVoltage());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if((target > Constants.Turret.LOWER_LIMIT) && (target < Constants.Turret.UPPER_LIMIT))
    talon.set(ControlMode.Position, target);

    SmartDashboard.putNumber("Target", target);
    SmartDashboard.putNumber("Selected", talon.getSelectedSensorPosition());
    SmartDashboard.putNumber("Error", target - talon.getSelectedSensorPosition());
    //SmartDashboard.putNumber("Quad", talon.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("Voltage", talon.getMotorOutputVoltage());

    
  }

  public void setTarget(double t, boolean raw) {
    if(raw) {
      target = t;
    } else {
      target = (t*Constants.Turret.TICKS_PER_DEG) + Constants.Turret.TICK_OFFSET + talon.getSelectedSensorPosition();
    }
  }
}
