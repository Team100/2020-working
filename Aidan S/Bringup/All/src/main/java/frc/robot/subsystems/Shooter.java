/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;

public class Shooter extends SubsystemBase {
    private FRCTalonSRX master;
    private FRCTalonSRX follower;
    private double setpoint = 0;

    /**
     * Creates a new Stage.
     */
    public Shooter() {
        master = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Shooter.MASTER_CAN_ID).build();
        master.setSmartDashboardPath("/FRClib/Shooter/Master");
        master.setSmartDashboardPutEnabled(true);

        follower = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Shooter.FOLLOWER_CAN_ID)
                .withMaster(master)
                .withInverted(InvertType.OpposeMaster)
                .build();
        follower.setSmartDashboardPath("/FRClib/Shooter/Follower");
        follower.setSmartDashboardPutEnabled(true);

        master.motor.setSafetyEnabled(false);
        follower.motor.setSafetyEnabled(false);
        // follower.motor.setInverted(InvertType.OpposeMaster);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        master.updateSmartDashboard();
        follower.updateSmartDashboard();

        master.drivePercentOutput(setpoint);
        // follower.drivePercentOutput(setpoint);
    }

    public void increase() {
        if (setpoint < 1) setpoint += 0.1;
    }

    public void decrease() {
        if (setpoint > 0) setpoint -= 0.1;
    }

    public void stop() {
        setpoint = 0;
        master.drivePercentOutput(0);
    }
}
