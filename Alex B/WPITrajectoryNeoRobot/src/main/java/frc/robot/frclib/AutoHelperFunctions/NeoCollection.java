/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.frclib.AutoHelperFunctions;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Acts as an abstraction of the several components needed for NEO control
 */
public class NeoCollection {
    public int id;
    public CANSparkMax motor;
    public CANPIDController pidController;
    public CANEncoder encoder;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

    /**
     * Creates a NeoCollection object given the control parameters
     * @param id
     * @param kP
     * @param kI
     * @param kD
     * @param kIz
     * @param kFF
     * @param kMaxOutput
     * @param kMinOutput
     * @param maxRPM
     */

    public NeoCollection(int id, double kP, double kI, double kD, double kIz, double kFF, double kMaxOutput,
            double kMinOutput, double maxRPM) {
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

    public void init(){
        ///////////////////////////////////////////////////////////////
        // WARNING                                                   //
        ///////////////////////////////////////////////////////////////
        // DO NOT CHANGE THE MOTOR TYPE IN HERE                      //
        // IT WILL RESULT IN CATASTROPHIC EFFECTS                    //
        // TODO MAKE SURE THAT THESE VALUES ARE MotorType.kBrushless //
        ///////////////////////////////////////////////////////////////
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

        pidController.setP(this.kP);
        pidController.setI(this.kI);
        pidController.setD(this.kD);
        pidController.setIZone(this.kIz);
        pidController.setFF(this.kFF);
        pidController.setOutputRange(this.kMinOutput, this.kMaxOutput);
        
    }

    
}
