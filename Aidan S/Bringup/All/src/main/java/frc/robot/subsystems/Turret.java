/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;

public class Turret extends SubsystemBase {
    private FRCTalonSRX motor;
    private double setpoint;

    /**
     * Creates a new Stage.
     */
    public Turret() {
        motor = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Turret.MOTOR_CAN_ID)
                .build();
        motor.motor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        motor.setSmartDashboardPath("/FRClib/Turret");
        motor.setSmartDashboardPutEnabled(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        motor.updateSmartDashboard();

        motor.drivePercentOutput(-(setpoint / 20));
    }

    public void forward() {
        setpoint++;
    }

    public void reverse() {
        setpoint--;
    }

    public void zero() {
        setpoint = 0;
    }
}
