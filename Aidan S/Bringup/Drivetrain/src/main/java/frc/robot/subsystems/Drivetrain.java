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

public class Drivetrain extends SubsystemBase {
    private FRCTalonSRX leftMaster;
    private FRCTalonSRX leftFollower;
    private FRCTalonSRX rightMaster;
    private FRCTalonSRX rightFollower;

    /**
     * Creates a new Stage.
     */
    public Drivetrain() {
        leftMaster = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Drivetrain.LEFT_MASTER_CAN_ID).build();
        leftMaster.setSmartDashboardPath("/FRClib/Error");
        leftMaster.setSmartDashboardPutEnabled(true);

        leftFollower = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Drivetrain.LEFT_FOLLOWER_CAN_ID).withMaster(leftMaster).build();
        leftFollower.setSmartDashboardPath("/FRClib/Error");
        leftFollower.setSmartDashboardPutEnabled(true);

        rightMaster = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Drivetrain.RIGHT_MASTER_CAN_ID).build();
        rightMaster.setSmartDashboardPath("/FRClib/Error");
        rightMaster.setSmartDashboardPutEnabled(true);

        rightFollower = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.Drivetrain.RIGHT_FOLLOWER_CAN_ID).withMaster(rightMaster).build();
        rightFollower.setSmartDashboardPath("/FRClib/Error");
        rightFollower.setSmartDashboardPutEnabled(true);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        leftMaster.updateSmartDashboard();
        leftFollower.updateSmartDashboard();
        rightMaster.updateSmartDashboard();
        rightFollower.updateSmartDashboard();
    }

    public void drive(double l, double r) {
        leftMaster.drivePercentOutput(l);
        rightMaster.drivePercentOutput(-r);
    }
}
