/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.frclib.AutoHelperFunctions.AutonConversionFactors;
import frc.robot.frclib.AutoHelperFunctions.NeoCollection;


public class Drivetrain extends SubsystemBase {

////////////////////////////////////////////////////////////
// Drivetrain.java                                        //
////////////////////////////////////////////////////////////
//                                                        //
// This subsystem is designed around the 2020 NEO Minibot //
// and is designed to run the SPARK MAX autonomous        //
// based on the blog post at                              //
// blog.alexbeaver.com/wpilib-trajectory-guide/           //
// as well as the WPILib Trajectory Talon SRX Robot       //
//                                                        //
////////////////////////////////////////////////////////////


  /**
   * A collection of motor controller, encoder, and PID for the left master motor
   */
  public NeoCollection leftMaster;

  /**
   * A collection of motor controller, encoder, and PID for the right master motor
   */
  public NeoCollection rightMaster;

  /**
   * The Minibot Gyro
   */
  public ADXRS450_Gyro gyro;

  /**
   * The current odometry of the robot
   */
  public DifferentialDriveOdometry odometry;

  /**
   * Information regarding the feedback from the Drivetrain
   */
  public DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(Constants.RobotCharacteristics.WHEELBASE_WIDTH);
  
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    leftMaster = new NeoCollection(Constants.LeftLeader.CAN_ID, Constants.LeftLeader.KP, Constants.LeftLeader.KI, Constants.LeftLeader.KD, Constants.LeftLeader.KIZ, Constants.LeftLeader.KF, -1, 1, 5777);
    rightMaster = new NeoCollection(Constants.RightLeader.CAN_ID, Constants.RightLeader.KP, Constants.RightLeader.KI, Constants.RightLeader.KD, Constants.RightLeader.KIZ, Constants.RightLeader.KF, -1, 1, 5777);
    gyro = new ADXRS450_Gyro();
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(this.getHeading()));
    this.tankDriveSpeed(0, 0);
    resetEncoders();

    leftMaster.inverted = -1;
    rightMaster.inverted = 1;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(SmartDashboard.getBoolean("UPDATE", false)){
      System.out.println("UPDATING");
      /*double kP     = SmartDashboard.getNumber("kP"   , 0);
      double kI     = SmartDashboard.getNumber("kI"   , 0);
      double kD     = SmartDashboard.getNumber("kD"   , 0);
      double kIz    = SmartDashboard.getNumber("kIz"  , 0);
      double kF     = SmartDashboard.getNumber("kF"   , 0);
      double kMIN   = SmartDashboard.getNumber("min"  , 0);
      double kMAX   = SmartDashboard.getNumber("max"  , 0);
      double kRPM_M = SmartDashboard.getNumber("RPM_M", 0); //MAX RPM*/

      double kP = Constants.DTConstants.KP;
      double kI = Constants.DTConstants.KI;
      double kD = Constants.DTConstants.KD;
      double kIz = Constants.DTConstants.KIZ;
      double kF = Constants.DTConstants.KF;
      double kMIN = -1; //TODO Migrate to Constants.java
      double kMAX = 1;
      double kRPM_M = 3000;




      leftMaster.configPIDController(kP, kI, kD, kIz, kF, kMIN, kMAX, kRPM_M);
      rightMaster.configPIDController(kP, kI, kD, kIz, kF, kMIN, kMAX, kRPM_M);
      double vel = SmartDashboard.getNumber("vel", 0);

      leftMaster.pidController.setReference(vel, ControlType.kVelocity);
      
      //leftMaster.motor.set(vel);

      SmartDashboard.putBoolean("UPDATE", false);

    }

    double leftLeaderDistance = AutonConversionFactors.convertTicksToMeters(leftMaster.getSensorPosition(), Constants.DTConstants.WHEEL_DIAMETER, Constants.DTConstants.TICKS_PER_REV);
    double rightLeaderDistance = AutonConversionFactors.convertTicksToMeters(rightMaster.getSensorPosition(), Constants.DTConstants.WHEEL_DIAMETER, Constants.DTConstants.TICKS_PER_REV);
    odometry.update(Rotation2d.fromDegrees(getHeading()),leftLeaderDistance, rightLeaderDistance);

    tankDriveVelocity(10, 10);
    

    SmartDashboard.putNumber("Current Velocity", leftMaster.encoder.getVelocity());
    

  }


  public Pose2d getPose(){
    return odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(AutonConversionFactors.convertRPMToMpS(this.leftMaster.getSensorVelocity(), Constants.DTConstants.WHEEL_DIAMETER, Constants.DTConstants.TICKS_PER_REV), AutonConversionFactors.convertRPMToMpS(this.rightMaster.getSensorVelocity(), Constants.DTConstants.WHEEL_DIAMETER, Constants.DTConstants.TICKS_PER_REV));
  }


  public void resetOdometry(Pose2d pose){
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public void tankDriveSpeed(double leftSpeed, double rightSpeed){
    System.out.println("Left Speed" +leftSpeed);
    System.out.println("Right Speed"+rightSpeed);
    this.leftMaster.setSpeed(leftSpeed);
    this.rightMaster.setSpeed(rightSpeed);
  }

  public void tankDriveVelocity(double leftVel, double rightVel){

    double leftLeaderNativeVelocity = AutonConversionFactors.convertMpSToRPM(leftVel, Constants.DTConstants.WHEEL_DIAMETER, Constants.DTConstants.GEARING_RATIO);
    double rightLeaderNativeVelocity = AutonConversionFactors.convertMpSToRPM(rightVel, Constants.DTConstants.WHEEL_DIAMETER, Constants.DTConstants.GEARING_RATIO);
    System.out.println(leftLeaderNativeVelocity + ","+ rightLeaderNativeVelocity);  

    this.leftMaster.setVelocity(leftLeaderNativeVelocity);
    this.rightMaster.setVelocity(rightLeaderNativeVelocity);

    SmartDashboard.putNumber("LeftIntentedVelocity", leftLeaderNativeVelocity);
    SmartDashboard.putNumber("LeftSensorVelocity", this.leftMaster.getSensorVelocity());
    SmartDashboard.putNumber("RighttSensorVelocity", this.rightMaster.getSensorVelocity());

    SmartDashboard.putNumber("LeftIntendedVsActual", leftLeaderNativeVelocity-this.leftMaster.getSensorVelocity());
  }

  public void resetEncoders(){
    leftMaster.encoder.setPosition(0);
    rightMaster.encoder.setPosition(0);
  }

 
  public double getAverageEncoderDistance(){
    return (leftMaster.getSensorPosition() + leftMaster.getSensorPosition())/2.0;
  }
  public void zeroHeading() {
    gyro.reset();
  }

  public double getHeading(){
    //return Math.IEEEremainder(gyro.getAngle(), 360);
    return -1 * Math.IEEEremainder(gyro.getAngle(),360);

  }
  public double getTurnRate(){
    return gyro.getRate();
  }
}
