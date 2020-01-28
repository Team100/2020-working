/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                             //
// Why does this exist?                                                                                                        //
//                                                                                                                             //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                             //
// The Spark MAX Motor Controller separates the Motor, Encoder, and PID into three separate objects.                           //
// In order to make this code look more similar to our Talon SRX-based code, this class wraps the three objects together.      //
// The other benefit to this is that you can have a more hierarchal code layout, because one object contains the sub elements. //
//                                                                                                                             //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


package frc.robot.frclib.AutoHelperFunctions;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/**
 * Acts as an unifier of the several components needed for NEO control
 */
public class NeoCollection {
    /**
     * The CAN ID of the motor
     */
    public int id;

    /**
     * The NEO Motor
     */
    public CANSparkMax motor;

    /**
     * The PID Controller for the Neo
     */
    public CANPIDController pidController;

    /**
     * The Encoder on the NEO
     */
    public CANEncoder encoder;

    /**
     * Constants for Control
     */
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

    public int inverted = 1;

    /**
     * Creates a NeoCollection object given the control parameters
     * @param id            The CANBus ID of the Motor
     * @param kP            Proportional Constant
     * @param kI            Integral Constant
     * @param kD            Derivative Constant
     * @param kIz           Integral Zone Constant
     * @param kFF           Feedforward Constant
     * @param kMaxOutput    Maximum Output
     * @param kMinOutput    Minimum Output
     * @param maxRPM        The Max velocity in RPM
     */

    public NeoCollection(int id, double kP, double kI, double kD, double kIz, double kFF, double kMinOutput,
            double kMaxOutput, double maxRPM) {
        this.id = id;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kIz = kIz;
        this.kFF = kFF; 
        this.kMaxOutput = kMaxOutput;
        this.kMinOutput = kMinOutput;
        this.maxRPM = maxRPM;


        this.init();
    }

    /**
     * Handles the initialization of a Neo Collection based upon the instance variables
     */
    public void init(){
        //////////////////////////////////////////////////////////
        // WARNING                                              //
        //////////////////////////////////////////////////////////
        // DO NOT CHANGE THE MOTOR TYPE IN HERE                 //
        // IT WILL RESULT IN CATASTROPHIC EFFECTS               //
        // MAKE SURE THAT THESE VALUES ARE MotorType.kBrushless //
        //////////////////////////////////////////////////////////
        motor = new CANSparkMax(this.id, MotorType.kBrushless);

        ///////////////////////////////////////////////////////////////
        // INFO                                                      //
        ///////////////////////////////////////////////////////////////
        // This code will configure a Spark MAX Motor Controller.    //
        // It uses Constants.java as the base for the configuration. //
        // Please make any changes in Constants.java                 //
        ///////////////////////////////////////////////////////////////

        motor.restoreFactoryDefaults();
        pidController = motor.getPIDController();
        encoder = motor.getEncoder();

        motor.setIdleMode(IdleMode.kBrake);
        this.configPIDController();

    }

    /**
     * Updates the PID Controller based off of the instance variables
     */
    public void configPIDController(){
        pidController.setP(this.kP);
        pidController.setI(this.kI);
        pidController.setD(this.kD);
        pidController.setIZone(this.kIz);
        pidController.setFF(this.kFF);
        pidController.setOutputRange(this.kMinOutput, this.kMaxOutput);
        System.out.println("Min Output"+this.kMinOutput);
        

        SmartDashboard.putNumber("PID Controller kP", pidController.getP());
        SmartDashboard.putNumber("PID Controller kI", pidController.getI());
        SmartDashboard.putNumber("PID Controller kD", pidController.getD());
        SmartDashboard.putNumber("PID Controller kF", pidController.getFF());
        
    }


    /**
     * Updates the PID of the NeoCollection
     * @param kP            Proportional Constant
     * @param kI            Integral Constant
     * @param kD            Derivative Constant
     * @param kIz           Integral Zone Constant
     * @param kFF           Feedforward Constant
     * @param kMinOutput    Minimum Output
     * @param kMaxOutput    Maximum Output
     * @param maxRPM        The Max velocity in RPM
     */
    public void configPIDController(double kP, double kI, double kD, double kIz, double kFF, double kMinOutput, double kMaxOutput, double maxRPM){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kIz = kIz;
        this.kFF = kFF;
        this.kMaxOutput = kMaxOutput;
        this.kMinOutput = kMinOutput;
        this.maxRPM = maxRPM;
        this.configPIDController();
    }

    /**
     * Set the speed of a NEO motor
     * @param speed speed of robot (-1 to 1)
     */
    public void setSpeed(double speed){
        System.out.println("SETTING SPEED TO: "+speed);
        this.motor.set(speed);
    }

    /**
     * Set the velocity to a NEO Motor
     * @param velocity the velocity to set (in RPM)
     */
    public void setVelocity(double velocity){
        this.pidController.setReference(velocity*this.inverted, ControlType.kVelocity);
    }

    /**
     * Get the current velocity
     * @return sensor velocity in RPM
     */
    public double getSensorVelocity(){
        return this.encoder.getVelocity()*this.inverted;
    }

    /**
     * Get the current position
     * @return encoder position in ticks
     */
    public int getSensorPosition(){
        return (int)(this.encoder.getPosition()*Constants.DTConstants.TICKS_PER_REV*this.inverted);
    }

    
}
