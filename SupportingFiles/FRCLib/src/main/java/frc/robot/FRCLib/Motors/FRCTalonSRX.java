/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Motors;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * An abstraction for the Talon SRX for debugging information
 */
public class FRCTalonSRX {

    /**
     * A direct reference to the TalonSRX motor, designed for direct control
     */
    public TalonSRX motor;

    /**
     * The Can ID of the selected motor
     */
    public int canID;

    /**
     * The inversion of the motor
     *
     * true inverts the motor
     */
    public boolean inverted;

    /**
     * The feedback port of the motor
     * Default is 0
     */
    public int feedbackPort = 0;

    /**
     * The timeout of the motor in ms
     *
     * Default is 10
     */
    public int timeout = 10;

    /**
     * The sensor phase of the motor
     *
     * true inverts the encoder signal
     */
    public boolean sensorPhase;

    /**
     * The kP value of the motor's PID controller
     */
    public double kP;

    /**
     * The kI value of the motor's PID controller
     */
    public double kI;

    /**
     * The kD value of the motor's PID controller
     */
    public double kD;

    /**
     * The kF value of the motor's PID controller
     */
    public double kF;

    /**
     * The acceptable closed loop error in ticks
     */
    public int allowableClosedLoopError;

    /**
     * The type of status frame
     */
    public StatusFrameEnhanced statusFrameType;

    /**
     * The status frame of the motor
     */
    public int statusFrame;

    /**
     * Is a current limit enabled
     *
     * a currentLimit must be set if this is true
     */
    public boolean currentLimitEnabled;

    /**
     * The current limit set
     *
     * currentLimitEnabled must be set for this to activate
     */
    public int currentLimit;

    /**
     * The neutral mode of the motor controller
     */
    public NeutralMode neutralMode;

    /**
     * Should a smartDashboardPut be enabled
     *
     * true will put to SmartDashboard
     */
    public boolean smartDashboardPutEnabled;

    /**
     * The path that the motor controller should report to
     */
    public String smartDashboardPath;

    /**
     * The ramp rate when controlled in open loop
     */
    public double openLoopRampRate;

    /**
     * The ramp rate when controlled in closed loop
     */
    public double closedLoopRampRate;

    /**
     * The forward nominal output
     */
    public double nominalOutputForward;

    /**
     * The reverse nominal output
     */
    public double nominalOutputReverse;

    /**
     * The forward peak output
     */
    public double peakOutputForward;

    /**
     * The reverse peak output
     */
    public double peakOutputReverse;

    /**
     * The neutral deadband
     */
    public double neutralDeadband;

    /**
     * The saturation for voltage compensation
     */
    public double voltageCompensationSaturation;

    /**
     * The measurement period for velocity control
     */
    public VelocityMeasPeriod velocityMeasurementPeriod;
    
    /**
     * The measurement window for the velocity control
     */
    public int velocityMeasurementWindow;

}
