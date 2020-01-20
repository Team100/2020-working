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



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(SmartDashboard.getBoolean("UPDATE", false)){
      System.out.println("UPDATING");
      double kP     = SmartDashboard.getNumber("kP"   , 0);
      double kI     = SmartDashboard.getNumber("kI"   , 0);
      double kD     = SmartDashboard.getNumber("kD"   , 0);
      double kIz    = SmartDashboard.getNumber("kIz"  , 0);
      double kF     = SmartDashboard.getNumber("kF"   , 0);
      double kMIN   = SmartDashboard.getNumber("min"  , 0);
      double kMAX   = SmartDashboard.getNumber("max"  , 0);
      double kRPM_M = SmartDashboard.getNumber("RPM_M", 0); //MAX RPM

      leftMaster.configPIDController(kP, kI, kD, kIz, kF, kMIN, kMAX, kRPM_M);
      rightMaster.configPIDController(kP, kI, kD, kIz, kF, kMIN, kMAX, kRPM_M);
      double vel = SmartDashboard.getNumber("vel", 0);

      leftMaster.pidController.setReference(vel, ControlType.kVelocity);
      //leftMaster.motor.set(vel);

      SmartDashboard.putBoolean("UPDATE", false);
    }
    

    SmartDashboard.putNumber("Current Velocity", leftMaster.encoder.getVelocity());
    

  }


  public Pose2d getPose(){
    return odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(AutonConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(this.leftLeader.getSelectedSensorVelocity(), Constants.DTConstants.WHEEL_DIAMETER, false, Constants.DTConstants.TICKS_PER_REV), AutonConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajecoryUnits(this.rightLeader.getSelectedSensorVelocity(), Constants.DTConstants.WHEEL_DIAMETER, false, Constants.DTConstants.TICKS_PER_REV));
  }


  public void resetOdometry(Pose2d pose){
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

 

  public void tankDriveVelocity(double leftVel, double rightVel){
    System.out.println(leftVel + ","+ rightVel);  

    double leftLeaderNativeVelocity = AutonConversionFactors.convertMpSToRPM(leftVel, Constants.DTConstants.WHEEL_DIAMETER, Constants.DTConstants.TICKS_PER_REV);
    double rightLeaderNativeVelocity = AutonConversionFactors.convertMpSToRPM(rightVel, Constants.DTConstants.WHEEL_DIAMETER, Constants.DTConstants.TICKS_PER_REV);


    this.leftMaster.setVelocity(leftLeaderNativeVelocity);
    this.rightMaster.setVelocity(rightLeaderNativeVelocity);

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
