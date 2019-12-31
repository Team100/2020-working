/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.CustomClasses.StoredTrajectory;
import frc.robot.commands.navigation.DTStop;
import frc.robot.commands.navigation.pathnav.ProcessDriving;
import frc.robot.commands.navigation.pathnav.TalonFollowTrajectory;
import frc.robot.supporting.CustomPathGenerator;
import frc.robot.supporting.MotionCalc;
import jaci.pathfinder.Waypoint;

/**
 * The drivetrain object used for autonomous
 */
public class Drivetrain extends Subsystem {
 
  public TalonSRX leftLeader;
  //public VictorSPX leftFollower;
  public TalonSRX rightLeader;
  //public VictorSPX rightFollower;
  public ADXRS450_Gyro gyro;
  
  
  public int iterator;

  public StoredTrajectory straightpath;
  public Drivetrain(){
    iterator = 0;

    ///////////////////////////////////////////////////////////////
    
    // Instantiate the motor controllers


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


    gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    gyro.reset();



    Waypoint[] straight ={
            new Waypoint(0,0,0),
            new Waypoint (1,0,0),
            new Waypoint (3,0,0)
    };
    straightpath = new StoredTrajectory("Straight",CustomPathGenerator.generate(straight));



  }

  public int getLeftDrivetrainTicks(){
    return leftLeader.getSelectedSensorPosition();
  }
  public int getRightDrivetrainTicks() {
    return rightLeader.getSelectedSensorPosition();
  }

  public void driveInVelocityMode(double l, double r){
    System.out.println("Driving in Velocity Mode");

    //this.leftLeader.set(ControlMode.Velocity, l);
    //this.rightLeader.set(ControlMode.Velocity, r);


     //this.leftLeader.set(ControlMode.PercentOutput, l);
     //this.rightLeader.set(ControlMode.PercentOutput, r);

      this.leftLeader.set(ControlMode.PercentOutput, l);
      this.rightLeader.set(ControlMode.PercentOutput, r);
  }

  public void positionPDauxF(double leftPos, double leftFF, double rightPos, double rightFF){
    leftLeader.set(ControlMode.Position, MotionCalc.distanceToRotations(leftPos, Constants.DTConstants.WHEEL_DIAMETER)*Constants.DTConstants.TICKS_PER_REV, DemandType.ArbitraryFeedForward, leftFF);
    rightLeader.set(ControlMode.Position, MotionCalc.distanceToRotations(rightPos, Constants.DTConstants.WHEEL_DIAMETER)*Constants.DTConstants.TICKS_PER_REV, DemandType.ArbitraryFeedForward, -rightFF);

  }

  public void halt(){
    this.leftLeader.set(ControlMode.PercentOutput, 0);
    this.rightLeader.set(ControlMode.PercentOutput, 0);
  }

  public void runStraightPath(){
    new TalonFollowTrajectory(straightpath.trajectory).start();
  }
  public double getGyroValue(){
    return this.gyro.getAngle();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DTStop());

    
  }

  
}
