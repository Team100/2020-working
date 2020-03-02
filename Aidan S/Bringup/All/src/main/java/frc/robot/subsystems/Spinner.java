/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;

public class Spinner extends SubsystemBase {
    private FRCTalonSRX motor;

    /**
     * Creates a new Stage.
     */
    public Spinner() {
        motor = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Spinner.MOTOR_CAN_ID).build();
        motor.setSmartDashboardPath("/FRClib/Spinner");
        motor.setSmartDashboardPutEnabled(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        motor.updateSmartDashboard();
    }

    public void forward() {
        motor.drivePercentOutput(1.0);
    }

    public void reverse() {
        motor.drivePercentOutput(-1.0);
    }

    public void stop() {
        motor.drivePercentOutput(0);
    }
}
