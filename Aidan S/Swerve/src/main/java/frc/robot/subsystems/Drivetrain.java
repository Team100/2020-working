/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.Drive;
import frc.robot.libswerve.drive.CanTalonSwerveEnclosure;
import frc.robot.libswerve.drive.SwerveDrive;

/**
 * Drivetrain
 */
public class Drivetrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static final double GEAR_RATIO = (1988d/1.2);
    private static final double frameLength = 19;
    private static final double frameWidth = 27.5;
    public SwerveDrive swerveDrive;

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
        WPI_TalonSRX fletcherDrive = new WPI_TalonSRX(Constants.FL_DRIVE_CANID);
        WPI_TalonSRX fletcherTurn = new WPI_TalonSRX(Constants.FL_TURN_CANID);
        WPI_TalonSRX frederickDrive = new WPI_TalonSRX(Constants.FR_DRIVE_CANID);
        WPI_TalonSRX frederickTurn = new WPI_TalonSRX(Constants.FR_TURN_CANID);
        WPI_TalonSRX blakeDrive = new WPI_TalonSRX(Constants.BL_DRIVE_CANID);
        WPI_TalonSRX blakeTurn = new WPI_TalonSRX(Constants.BL_TURN_CANID);
        WPI_TalonSRX brianDrive = new WPI_TalonSRX(Constants.BR_DRIVE_CANID);
        WPI_TalonSRX brianTurn = new WPI_TalonSRX(Constants.BR_TURN_CANID);

        CanTalonSwerveEnclosure se1 = new CanTalonSwerveEnclosure("enc 1", frederickDrive, frederickTurn, GEAR_RATIO);
        CanTalonSwerveEnclosure se2 = new CanTalonSwerveEnclosure("enc 2", fletcherDrive, fletcherTurn, GEAR_RATIO);
        CanTalonSwerveEnclosure se3 = new CanTalonSwerveEnclosure("enc 3", blakeDrive, blakeTurn, GEAR_RATIO);
        CanTalonSwerveEnclosure se4 = new CanTalonSwerveEnclosure("enc 4", brianDrive, brianTurn, GEAR_RATIO);

        swerveDrive = new SwerveDrive(se1, se2, se3, se4, frameWidth, frameLength);
    }
}
