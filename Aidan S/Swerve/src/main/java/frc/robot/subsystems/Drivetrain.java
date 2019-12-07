/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.Drive;
import frc.robot.libswerve.drive.CanTalonSwerveEnclosure;
import frc.robot.libswerve.drive.SwerveDrive;
import frc.robot.libswerve.math.CentricMode;

/**
 * Drivetrain
 */
public class Drivetrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static final double frameLength = 18.15;
    private static final double frameWidth = 17.75;

    public SwerveDrive swerveDrive;

    public WPI_TalonSRX fletcherTurn;
    public WPI_TalonSRX frederickTurn;
    public WPI_TalonSRX blakeTurn;
    public WPI_TalonSRX brianTurn;

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());

        fletcherTurn = new WPI_TalonSRX(Constants.FL_TURN_CANID);
        frederickTurn = new WPI_TalonSRX(Constants.FR_TURN_CANID);
        blakeTurn = new WPI_TalonSRX(Constants.BL_TURN_CANID);
        brianTurn = new WPI_TalonSRX(Constants.BR_TURN_CANID);

        updateMotorControllers();

        WPI_TalonSRX fletcherDrive = new WPI_TalonSRX(Constants.FL_DRIVE_CANID);
        WPI_TalonSRX frederickDrive = new WPI_TalonSRX(Constants.FR_DRIVE_CANID);
        WPI_TalonSRX blakeDrive = new WPI_TalonSRX(Constants.BL_DRIVE_CANID);
        WPI_TalonSRX brianDrive = new WPI_TalonSRX(Constants.BR_DRIVE_CANID);

        CanTalonSwerveEnclosure se1 = new CanTalonSwerveEnclosure("enc 1", frederickDrive, frederickTurn, Constants.FR_GEAR_RATIO, Constants.DRIVE_MODIFIER);
        CanTalonSwerveEnclosure se2 = new CanTalonSwerveEnclosure("enc 2", fletcherDrive, fletcherTurn, Constants.FL_GEAR_RATIO, -Constants.DRIVE_MODIFIER);
        CanTalonSwerveEnclosure se3 = new CanTalonSwerveEnclosure("enc 3", blakeDrive, blakeTurn, Constants.BL_GEAR_RATIO, -Constants.DRIVE_MODIFIER);
        CanTalonSwerveEnclosure se4 = new CanTalonSwerveEnclosure("enc 4", brianDrive, brianTurn, Constants.BR_GEAR_RATIO, Constants.DRIVE_MODIFIER);

        swerveDrive = new SwerveDrive(se1, se2, se3, se4, frameWidth, frameLength);
        swerveDrive.setCentricMode(CentricMode.FIELD);
    }

    private void updateMotorControllers() {
        WPI_TalonSRX[] motors = new WPI_TalonSRX[4];
        motors[0] = fletcherTurn;
        motors[1] = frederickTurn;
        motors[2] = blakeTurn;
        motors[3] = brianTurn;

        for(WPI_TalonSRX mc: motors) {
            mc.configFactoryDefault();

            mc.config_kP(0, 20);
            mc.config_kI(0, 0.01);
            mc.config_kD(0, 0);
            mc.config_kF(0, 0);
            mc.setSensorPhase(true);
            mc.setNeutralMode(NeutralMode.Brake);
            mc.configAllowableClosedloopError(0, 4);
        }
    }
}
