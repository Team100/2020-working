/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;

public class Turret extends SubsystemBase {
    public FRCTalonSRX talon;
    private double target = 0;

    /**
     * Creates a new Turret.
     */
    public Turret() {
        //configure motor
        talon = new FRCTalonSRX.FRCTalonSRXBuilder().withCanID(Constants.Turret.CAN_ID)
                .withKP(Constants.Turret.kP)
                .withKI(Constants.Turret.kI)
                .withKD(Constants.Turret.kD)
                .withKF(Constants.Turret.kF)
                .withClosedLoopRampRate(Constants.Turret.RAMP_SECONDS_TO_FULL)
                .withPeakOutputForward(Constants.Turret.PEAK_OUTPUT)
                .withPeakOutputReverse(-Constants.Turret.PEAK_OUTPUT)
                .withSensorPhase(true)
                .withNeutralMode(NeutralMode.Brake)
                .withAllowableClosedLoopError(Constants.Turret.ALLOWABLE_ERROR)
                .withNominalOutputForward(Constants.Turret.NOMINAL_VOLTAGE_FORWARD/talon.motor.getBusVoltage())
                .withNominalOutputReverse(Constants.Turret.NOMINAL_VOLTAGE_REVERSE/talon.motor.getBusVoltage())
                .build();

        talon.motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        talon.motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        talon.motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if((target > Constants.Turret.LOWER_LIMIT) && (target < Constants.Turret.UPPER_LIMIT))
        talon.drivePosition(target);

        SmartDashboard.putNumber("Target", target);
        SmartDashboard.putNumber("Selected", talon.getSelectedSensorPosition());
        SmartDashboard.putNumber("Error", target - talon.getSelectedSensorPosition());
        SmartDashboard.putNumber("Quad", talon.motor.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("Voltage", talon.motor.getMotorOutputVoltage());

        
    }

    public void setTarget(double t, boolean raw) {
        if(raw) {
            target = t;
        } else {
            target = (t*Constants.Turret.TICKS_PER_DEG) + Constants.Turret.TICK_OFFSET + talon.getSelectedSensorPosition();
        }
    }
}
