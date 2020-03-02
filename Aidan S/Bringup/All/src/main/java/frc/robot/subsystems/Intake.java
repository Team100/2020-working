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

public class Intake extends SubsystemBase {
    private FRCTalonSRX pivot;
    private double pivotSP;
    private FRCTalonSRX spin;

    /**
     * Creates a new Stage.
     */
    public Intake() {
        pivot = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Intake.PIVOT_MOTOR_CAN_ID)
                .build();
        pivot.setSmartDashboardPath("/FRClib/Intake/Pivot");
        pivot.setSmartDashboardPutEnabled(true);
        pivot.motor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        
        spin = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Intake.SPIN_MOTOR_CAN_ID).build();
        spin.setSmartDashboardPath("/FRClib/Intake/Spin");
        spin.setSmartDashboardPutEnabled(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        pivot.updateSmartDashboard();

        pivot.drivePercentOutput(pivotSP / 100);
    }

    public void up() {
        pivot.drivePercentOutput(-0.1);
    }

    public void down() {
        pivot.drivePercentOutput(0.1);
    }

    public void zero() {
        pivotSP = 0;
    }

    public void spin() {
        spin.drivePercentOutput(-0.5);
    }

    public void stop() {
        spin.drivePercentOutput(0);
    }
}
