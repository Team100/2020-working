/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.AutoHelperFunctions.AutonConversionFactors;
import frc.robot.FRCLib.Motors.FRCTalonFX;

public class Drivetrain extends SubsystemBase {
    
  private FRCTalonFX leftMaster, leftFollower, rightMaster, rightFollower;

  public AHRS ahrs;

  public DifferentialDriveOdometry odometry;

  /**
   * Creates a new ExampleSubsystem.
   */
  public Drivetrain() {

    ahrs = new AHRS(Constants.DrivetrainConstants.NAVX_PORT);

    leftMaster = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.CAN_ID)
        .withKP(Constants.DrivetrainConstants.DrivetrainParameters.KP)
        .withKI(Constants.DrivetrainConstants.DrivetrainParameters.KI)
        .withKD(Constants.DrivetrainConstants.DrivetrainParameters.KD)
        .withKF(Constants.DrivetrainConstants.DrivetrainParameters.KF)
        .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.INVERTED)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.LeftMaster.NEUTRAL_MODE).build();

    leftFollower = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.CAN_ID)
        .withKP(Constants.DrivetrainConstants.DrivetrainParameters.KP)
        .withKI(Constants.DrivetrainConstants.DrivetrainParameters.KI)
        .withKD(Constants.DrivetrainConstants.DrivetrainParameters.KD)
        .withKF(Constants.DrivetrainConstants.DrivetrainParameters.KF)
        .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.INVERTED)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.LeftFollower.NEUTRAL_MODE).build();

    rightMaster = new FRCTalonFX.FRCTalonFXBuilder(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.CAN_ID)
        .withKP(Constants.DrivetrainConstants.DrivetrainParameters.KP)
        .withKI(Constants.DrivetrainConstants.DrivetrainParameters.KI)
        .withKD(Constants.DrivetrainConstants.DrivetrainParameters.KD)
        .withKF(Constants.DrivetrainConstants.DrivetrainParameters.KF)
        .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.INVERTED)
        .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.RightMaster.NEUTRAL_MODE).build();
        
    rightFollower = new FRCTalonFX.FRCTalonFXBuilder(
        Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.CAN_ID)
            .withKP(Constants.DrivetrainConstants.DrivetrainParameters.KP)
            .withKI(Constants.DrivetrainConstants.DrivetrainParameters.KI)
            .withKD(Constants.DrivetrainConstants.DrivetrainParameters.KD)
            .withKF(Constants.DrivetrainConstants.DrivetrainParameters.KF)
            .withInverted(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.INVERTED)
            .withPeakOutputForward(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.DrivetrainConstants.DrivetrainMotors.RightFollower.NEUTRAL_MODE).build();

    leftFollower.follow(leftMaster);
    rightFollower.follow(rightMaster);
  }

  public void set(double left, double right) {
    this.tankDrivePercentOutput(left, right);
  }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
        

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
        AutonConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(this.leftMaster.getSensorVelocity(),
            Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, false,
            Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV),
        AutonConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(this.rightMaster.getSensorVelocity(),
            Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, false,
            Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV));
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public void tankDrivePercentOutput(double leftPO, double rightPO) {
    this.leftMaster.drivePercentOutput(leftPO);
    this.rightMaster.drivePercentOutput(rightPO);
  }

  public void tankDriveVelocity(double leftVel, double rightVel) {
    System.out.println(leftVel + "," + rightVel);

    double leftLeaderNativeVelocity = AutonConversionFactors.convertWPILibTrajectoryUnitsToTalonSRXNativeUnits(leftVel,
        Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, false,
        Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV);
    double rightLeaderNativeVelocity = AutonConversionFactors.convertWPILibTrajectoryUnitsToTalonSRXNativeUnits(
        rightVel, Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, false,
        Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV);
    this.leftMaster.driveVelocity(leftLeaderNativeVelocity);
    this.rightMaster.driveVelocity(rightLeaderNativeVelocity);

    if(Constants.DrivetrainConstants.DEBUG){
      SmartDashboard.putNumber("LeftIntentedVelocity", leftLeaderNativeVelocity);
    SmartDashboard.putNumber("LeftIntendedVsActual", leftLeaderNativeVelocity - this.leftMaster.getSensorVelocity());
    }
    
  }

  public void resetEncoders() {
    leftMaster.setSensorPosition(0);
    rightMaster.setSensorPosition(0);
  }

  public double getAverageEncoderDistance() {
    return (leftMaster.getSelectedSensorPosition() + rightMaster.getSelectedSensorPosition()) / 2.0;
  }

  public void zeroHeading() {
    ahrs.zeroYaw();
  }

  public double getHeading() {
    // return Math.IEEEremainder(gyro.getAngle(), 360);
    return -1 * Math.IEEEremainder(ahrs.getFusedHeading(), 360);

  }

  public double getTurnRate() {
    return ahrs.getRate();
  }
}
