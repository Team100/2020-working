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

public class Stage extends SubsystemBase {
    private FRCTalonSRX motor;
    private double mod;

    /**
     * Creates a new Stage.
     */
    public Stage(int CANID, double modifier) {
        mod = modifier;
        motor = new FRCTalonSRX.FRCTalonSRXBuilder(CANID).build();
        if (CANID == Constants.StageOne.MOTOR_CAN_ID) motor.setSmartDashboardPath("/FRClib/Indexer/StageOne");
        else if (CANID == Constants.StageTwo.MOTOR_CAN_ID) motor.setSmartDashboardPath("/FRClib/Indexer/StageTwo");
        else motor.setSmartDashboardPath("/FRClib/Error");
        motor.setSmartDashboardPutEnabled(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        motor.updateSmartDashboard();
    }

    public void forward() {
        motor.drivePercentOutput(0.5*mod);
    }

    public void reverse() {
        motor.drivePercentOutput(-0.5*mod);
    }

    public void stop() {
        motor.drivePercentOutput(0);
    }
}
