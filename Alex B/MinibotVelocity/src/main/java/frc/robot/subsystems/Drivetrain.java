/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.RightLeader;
import frc.robot.frclib.AutoHelperFunctions.AutonConversionFactors;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;


public class Drivetrain extends SubsystemBase {
  public TalonSRX leftLeader;
  //public VictorSPX leftFollower;
  public TalonSRX rightLeader;
  //public VictorSPX rightFollower;
  public ADXRS450_Gyro gyro;
  public AHRS ahrs;

  public DifferentialDriveOdometry odometry;
  public DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(Constants.DTConstants.KTRACK_WIDTH);



  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
      SmartDashboard.putNumber("Velocity", 0);
      SmartDashboard.putNumber("kP", 0);
      SmartDashboard.putNumber("kI", 0);
      SmartDashboard.putNumber("kD", 0);
      SmartDashboard.putNumber("kF", 0);




    leftLeader = new TalonSRX(Constants.LeftLeader.CAN_ID);
    //leftFollower = new VictorSPX(Constants.//LeftFollower.CAN_ID);

    rightLeader = new TalonSRX(Constants.RightLeader.CAN_ID);
    //rightFollower = new VictorSPX(Constants.//RightFollower.CAN_ID);


    ///////////////////////////////////////////////////////////////
    
    // Reset all of the motor controllers
    // This is so that we can avoid having left over settings changes messing stuff open

    leftLeader.configFactoryDefault();
    //leftFollower.configFactoryDefault();

    rightLeader.configFactoryDefault();
    //rightFollower.configFactoryDefault();

    ///////////////////////////////////////////////////////////////

    // Have the followers follow the masters

    //leftFollower.follow(leftLeader);
    //rightFollower.follow(rightLeader);

    ///////////////////////////////////////////////////////////////

    // Configure inverts

    leftLeader.setInverted(Constants.LeftLeader.INVERTED);
    //leftFollower.setInverted(Constants.//LeftFollower.INVERTED);

    rightLeader.setInverted(Constants.RightLeader.INVERTED);
    //rightFollower.setInverted(Constants.//RightFollower.INVERTED);
    
    ///////////////////////////////////////////////////////////////

    // Configure PID

    leftLeader.config_kP(0, Constants.LeftLeader.KP);
    leftLeader.config_kI(0, Constants.LeftLeader.KI);
    leftLeader.config_kD(0, Constants.LeftLeader.KD);
    leftLeader.config_kF(0, Constants.LeftLeader.KF);

    rightLeader.config_kP(0, Constants.RightLeader.KP);
    rightLeader.config_kI(0, Constants.RightLeader.KI);
    rightLeader.config_kD(0, Constants.RightLeader.KD);
    rightLeader.config_kF(0, Constants.RightLeader.KF);

    ///////////////////////////////////////////////////////////////

    // Configure Encoder
    leftLeader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.LeftLeader.Feedback.PORT, Constants.LeftLeader.TIMEOUT);
    rightLeader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.RightLeader.Feedback.PORT, Constants.RightLeader.TIMEOUT);

    ///////////////////////////////////////////////////////////////

    // Configure Sensor Phase

    leftLeader.setSensorPhase(Constants.LeftLeader.Feedback.SENSOR_PHASE);
    rightLeader.setSensorPhase(Constants.RightLeader.Feedback.SENSOR_PHASE);

    ///////////////////////////////////////////////////////////////

    // Reset the Encoders

    leftLeader.setSelectedSensorPosition(0);
    rightLeader.setSelectedSensorPosition(0);

    ///////////////////////////////////////////////////////////////

    // Closed Loop Error
    leftLeader.configAllowableClosedloopError(0, Constants.LeftLeader.CLOSED_LOOP_ERROR, Constants.LeftLeader.TIMEOUT);
    rightLeader.configAllowableClosedloopError(0, Constants.RightLeader.CLOSED_LOOP_ERROR, Constants.RightLeader.TIMEOUT);

    ///////////////////////////////////////////////////////////////

    // Set Status Frame Period
    leftLeader.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, Constants.LeftLeader.STATUS_FRAME, Constants.LeftLeader.TIMEOUT);
    rightLeader.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, Constants.RightLeader.STATUS_FRAME, Constants.RightLeader.TIMEOUT);

    ///////////////////////////////////////////////////////////////

    // Current Limit

    if(Constants.LeftLeader.Power.CURRENT_LIMIT){
      leftLeader.configPeakCurrentLimit(Constants.LeftLeader.Power.MAX_AMP);
    }

    if(Constants.RightLeader.Power.CURRENT_LIMIT){
      rightLeader.configPeakCurrentLimit(Constants.RightLeader.Power.MAX_AMP);
    }

    leftLeader.setNeutralMode(NeutralMode.Brake);
    rightLeader.setNeutralMode(NeutralMode.Brake);


    ahrs = new AHRS(SPI.Port.kMXP);
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(ahrs.getCompassHeading()));

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(SmartDashboard.getNumber("Velocity", 0) == 3000 && Math.abs(leftLeader.getSelectedSensorVelocity()-3000)<20){
        SmartDashboard.putNumber("Velocity", 4000);
    }
    else if(SmartDashboard.getNumber("Velocity", 0) == 4000 && Math.abs(leftLeader.getSelectedSensorVelocity()-4000)<20){
        SmartDashboard.putNumber("Velocity", 2000);
    }
    else if(SmartDashboard.getNumber("Velocity", 0) == 2000 && Math.abs(leftLeader.getSelectedSensorVelocity()-2000)<20){
        SmartDashboard.putNumber("Velocity", 0);
    }
    leftLeader.set(ControlMode.Velocity, SmartDashboard.getNumber("Velocity", 0));
    rightLeader.set(ControlMode.Velocity, SmartDashboard.getNumber("Velocity", 0));
    SmartDashboard.putNumber("Left Sensor Velocity",this.leftLeader.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Right Sensor Velocity", this.rightLeader.getSelectedSensorVelocity());

    leftLeader.config_kP(0, SmartDashboard.getNumber("kP", 0));
    leftLeader.config_kI(0, SmartDashboard.getNumber("kI", 0));
    leftLeader.config_kD(0, SmartDashboard.getNumber("kD", 0));
    leftLeader.config_kF(0, SmartDashboard.getNumber("kF", 0));

    rightLeader.config_kP(0, SmartDashboard.getNumber("kP", 0));
    rightLeader.config_kI(0, SmartDashboard.getNumber("kI", 0));
    rightLeader.config_kD(0, SmartDashboard.getNumber("kD", 0));
    rightLeader.config_kF(0, SmartDashboard.getNumber("kF", 0));

    SmartDashboard.putNumber("Left Voltage Output", leftLeader.getMotorOutputVoltage());







    
  }

  public Pose2d getPose(){
    return odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(AutonConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(this.leftLeader.getSelectedSensorVelocity(), Constants.DTConstants.WHEEL_DIAMETER, false, Constants.DTConstants.TICKS_PER_REV), AutonConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(this.rightLeader.getSelectedSensorVelocity(), Constants.DTConstants.WHEEL_DIAMETER, false, Constants.DTConstants.TICKS_PER_REV));
  }

  public void resetOdometry(Pose2d pose){
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(ahrs.getCompassHeading()));
  }

  public void tankDriveVolts(double leftVolts, double rightVolts){
    System.out.println(leftVolts + ","+ rightVolts);  
    leftLeader.set(ControlMode.Current, leftVolts);
    rightLeader.set(ControlMode.Current, -rightVolts);
  }

  public void tankDriveVelocity(double leftVel, double rightVel){
    System.out.println(leftVel + ","+ rightVel);  

    double leftLeaderNativeVelocity = AutonConversionFactors.convertWPILibTrajectoryUnitsToTalonSRXNativeUnits(leftVel, Constants.DTConstants.WHEEL_DIAMETER, false, Constants.DTConstants.TICKS_PER_REV);
    double rightLeaderNativeVelocity = AutonConversionFactors.convertWPILibTrajectoryUnitsToTalonSRXNativeUnits(rightVel, Constants.DTConstants.WHEEL_DIAMETER, false, Constants.DTConstants.TICKS_PER_REV);

    this.leftLeader.set(ControlMode.Velocity, leftLeaderNativeVelocity);
    this.rightLeader.set(ControlMode.Velocity, rightLeaderNativeVelocity);

    SmartDashboard.putNumber("LeftIntentedVelocity", leftLeaderNativeVelocity);
    SmartDashboard.putNumber("LeftIntendedVsActual", leftLeaderNativeVelocity-this.leftLeader.getSelectedSensorVelocity());
  }

  public void resetEncoders(){
    leftLeader.setSelectedSensorPosition(0);
    rightLeader.setSelectedSensorPosition(0);
  }

  public double getAverageEncoderDistance(){
    return (leftLeader.getSelectedSensorPosition() + rightLeader.getSelectedSensorPosition())/2.0;
  }
  public void zeroHeading() {
    ahrs.reset();
  }

  public double getHeading(){
    return Math.IEEEremainder(ahrs.getCompassHeading(), 360);

  }
  public double getTurnRate(){
    return ahrs.getRate();
  }

  
}
