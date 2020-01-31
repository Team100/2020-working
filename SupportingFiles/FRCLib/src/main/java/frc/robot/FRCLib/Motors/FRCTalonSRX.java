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












    ///////////////////////////////////////////////////////////////////////////
    /**
     * A direct reference to the TalonSRX motor, designed for direct control
     */
    public TalonSRX motor;
    ///////////////////////////////////////////////////////////////////////////
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

    /**
     * Is a forward soft limit enabled
     */
    public boolean forwardSoftLimitEnabled;

    /**
     * The forward soft limit set
     */
    public int forwardSoftLimitThreshold;

    /**
     * Is a reverse soft limit enabled
     */
    public boolean reverseSoftLimitEnabled;

    /**
     * The reverse soft limit set
     */
    public int reverseSoftLimitThreshold;

    /**
     * Is auxiliary polarity enabled
     */
    public boolean auxPIDPolarity;

    /**
     * The motion cruise velocity set
     */
    public int motionCruiseVelocity;

    /**
     * The motion acceleration set
     */
    public int motionAcceleration;

    /**
     * The strength of the motion curve
     */
    public int motionCurveStrength;

    /**
     * The period set when using motion profiles
     */
    public int motionProfileTrajectoryPeriod;

    /**
     * Is continuous or discontinuous feedback enabled
     */
    public boolean feedbackNotContinuous;

    

    public static final class FRCTalonSRXBuilder {
        public int canID;
        public boolean inverted;
        public int feedbackPort = 0;
        public int timeout = 10;
        public boolean sensorPhase;
        public double kP;
        public double kI;
        public double kD;
        public double kF;
        public int allowableClosedLoopError;
        public StatusFrameEnhanced statusFrameType;
        public int statusFrame;
        public boolean currentLimitEnabled;
        public int currentLimit;
        public NeutralMode neutralMode;
        public boolean smartDashboardPutEnabled;
        public String smartDashboardPath;
        public double openLoopRampRate;
        public double closedLoopRampRate;
        public double nominalOutputForward;
        public double nominalOutputReverse;
        public double peakOutputForward;
        public double peakOutputReverse;
        public double neutralDeadband;
        public double voltageCompensationSaturation;
        public VelocityMeasPeriod velocityMeasurementPeriod;
        public int velocityMeasurementWindow;
        public boolean forwardSoftLimitEnabled;
        public int forwardSoftLimitThreshold;
        public boolean reverseSoftLimitEnabled;
        public int reverseSoftLimitThreshold;
        public boolean auxPIDPolarity;
        public int motionCruiseVelocity;
        public int motionAcceleration;
        public int motionCurveStrength;
        public int motionProfileTrajectoryPeriod;
        public boolean feedbackNotContinuous;

        private FRCTalonSRXBuilder() {
        }

        public static FRCTalonSRXBuilder aFRCTalonSRX() {
            return new FRCTalonSRXBuilder();
        }

        public FRCTalonSRXBuilder withCanID(int canID) {
            this.canID = canID;
            return this;
        }

        public FRCTalonSRXBuilder withInverted(boolean inverted) {
            this.inverted = inverted;
            return this;
        }

        public FRCTalonSRXBuilder withFeedbackPort(int feedbackPort) {
            this.feedbackPort = feedbackPort;
            return this;
        }

        public FRCTalonSRXBuilder withTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public FRCTalonSRXBuilder withSensorPhase(boolean sensorPhase) {
            this.sensorPhase = sensorPhase;
            return this;
        }

        public FRCTalonSRXBuilder withKP(double kP) {
            this.kP = kP;
            return this;
        }

        public FRCTalonSRXBuilder withKI(double kI) {
            this.kI = kI;
            return this;
        }

        public FRCTalonSRXBuilder withKD(double kD) {
            this.kD = kD;
            return this;
        }

        public FRCTalonSRXBuilder withKF(double kF) {
            this.kF = kF;
            return this;
        }

        public FRCTalonSRXBuilder withAllowableClosedLoopError(int allowableClosedLoopError) {
            this.allowableClosedLoopError = allowableClosedLoopError;
            return this;
        }

        public FRCTalonSRXBuilder withStatusFrameType(StatusFrameEnhanced statusFrameType) {
            this.statusFrameType = statusFrameType;
            return this;
        }

        public FRCTalonSRXBuilder withStatusFrame(int statusFrame) {
            this.statusFrame = statusFrame;
            return this;
        }

        public FRCTalonSRXBuilder withCurrentLimitEnabled(boolean currentLimitEnabled) {
            this.currentLimitEnabled = currentLimitEnabled;
            return this;
        }

        public FRCTalonSRXBuilder withCurrentLimit(int currentLimit) {
            this.currentLimit = currentLimit;
            return this;
        }

        public FRCTalonSRXBuilder withNeutralMode(NeutralMode neutralMode) {
            this.neutralMode = neutralMode;
            return this;
        }

        public FRCTalonSRXBuilder withSmartDashboardPutEnabled(boolean smartDashboardPutEnabled) {
            this.smartDashboardPutEnabled = smartDashboardPutEnabled;
            return this;
        }

        public FRCTalonSRXBuilder withSmartDashboardPath(String smartDashboardPath) {
            this.smartDashboardPath = smartDashboardPath;
            return this;
        }

        public FRCTalonSRXBuilder withOpenLoopRampRate(double openLoopRampRate) {
            this.openLoopRampRate = openLoopRampRate;
            return this;
        }

        public FRCTalonSRXBuilder withClosedLoopRampRate(double closedLoopRampRate) {
            this.closedLoopRampRate = closedLoopRampRate;
            return this;
        }

        public FRCTalonSRXBuilder withNominalOutputForward(double nominalOutputForward) {
            this.nominalOutputForward = nominalOutputForward;
            return this;
        }

        public FRCTalonSRXBuilder withNominalOutputReverse(double nominalOutputReverse) {
            this.nominalOutputReverse = nominalOutputReverse;
            return this;
        }

        public FRCTalonSRXBuilder withPeakOutputForward(double peakOutputForward) {
            this.peakOutputForward = peakOutputForward;
            return this;
        }

        public FRCTalonSRXBuilder withPeakOutputReverse(double peakOutputReverse) {
            this.peakOutputReverse = peakOutputReverse;
            return this;
        }

        public FRCTalonSRXBuilder withNeutralDeadband(double neutralDeadband) {
            this.neutralDeadband = neutralDeadband;
            return this;
        }

        public FRCTalonSRXBuilder withVoltageCompensationSaturation(double voltageCompensationSaturation) {
            this.voltageCompensationSaturation = voltageCompensationSaturation;
            return this;
        }

        public FRCTalonSRXBuilder withVelocityMeasurementPeriod(VelocityMeasPeriod velocityMeasurementPeriod) {
            this.velocityMeasurementPeriod = velocityMeasurementPeriod;
            return this;
        }

        public FRCTalonSRXBuilder withVelocityMeasurementWindow(int velocityMeasurementWindow) {
            this.velocityMeasurementWindow = velocityMeasurementWindow;
            return this;
        }

        public FRCTalonSRXBuilder withForwardSoftLimitEnabled(boolean forwardSoftLimitEnabled) {
            this.forwardSoftLimitEnabled = forwardSoftLimitEnabled;
            return this;
        }

        public FRCTalonSRXBuilder withForwardSoftLimitThreshold(int forwardSoftLimitThreshold) {
            this.forwardSoftLimitThreshold = forwardSoftLimitThreshold;
            return this;
        }

        public FRCTalonSRXBuilder withReverseSoftLimitEnabled(boolean reverseSoftLimitEnabled) {
            this.reverseSoftLimitEnabled = reverseSoftLimitEnabled;
            return this;
        }

        public FRCTalonSRXBuilder withReverseSoftLimitThreshold(int reverseSoftLimitThreshold) {
            this.reverseSoftLimitThreshold = reverseSoftLimitThreshold;
            return this;
        }

        public FRCTalonSRXBuilder withAuxPIDPolarity(boolean auxPIDPolarity) {
            this.auxPIDPolarity = auxPIDPolarity;
            return this;
        }

        public FRCTalonSRXBuilder withMotionCruiseVelocity(int motionCruiseVelocity) {
            this.motionCruiseVelocity = motionCruiseVelocity;
            return this;
        }

        public FRCTalonSRXBuilder withMotionAcceleration(int motionAcceleration) {
            this.motionAcceleration = motionAcceleration;
            return this;
        }

        public FRCTalonSRXBuilder withMotionCurveStrength(int motionCurveStrength) {
            this.motionCurveStrength = motionCurveStrength;
            return this;
        }

        public FRCTalonSRXBuilder withMotionProfileTrajectoryPeriod(int motionProfileTrajectoryPeriod) {
            this.motionProfileTrajectoryPeriod = motionProfileTrajectoryPeriod;
            return this;
        }

        public FRCTalonSRXBuilder withFeedbackNotContinuous(boolean feedbackNotContinuous) {
            this.feedbackNotContinuous = feedbackNotContinuous;
            return this;
        }

        public FRCTalonSRX build() {
            FRCTalonSRX fRCTalonSRX = new FRCTalonSRX();
            fRCTalonSRX.inverted = this.inverted;
            fRCTalonSRX.kF = this.kF;
            fRCTalonSRX.motionCurveStrength = this.motionCurveStrength;
            fRCTalonSRX.feedbackPort = this.feedbackPort;
            fRCTalonSRX.peakOutputReverse = this.peakOutputReverse;
            fRCTalonSRX.neutralMode = this.neutralMode;
            fRCTalonSRX.closedLoopRampRate = this.closedLoopRampRate;
            fRCTalonSRX.velocityMeasurementWindow = this.velocityMeasurementWindow;
            fRCTalonSRX.voltageCompensationSaturation = this.voltageCompensationSaturation;
            fRCTalonSRX.motionProfileTrajectoryPeriod = this.motionProfileTrajectoryPeriod;
            fRCTalonSRX.smartDashboardPutEnabled = this.smartDashboardPutEnabled;
            fRCTalonSRX.forwardSoftLimitThreshold = this.forwardSoftLimitThreshold;
            fRCTalonSRX.kD = this.kD;
            fRCTalonSRX.motionAcceleration = this.motionAcceleration;
            fRCTalonSRX.auxPIDPolarity = this.auxPIDPolarity;
            fRCTalonSRX.currentLimit = this.currentLimit;
            fRCTalonSRX.canID = this.canID;
            fRCTalonSRX.forwardSoftLimitEnabled = this.forwardSoftLimitEnabled;
            fRCTalonSRX.smartDashboardPath = this.smartDashboardPath;
            fRCTalonSRX.motionCruiseVelocity = this.motionCruiseVelocity;
            fRCTalonSRX.neutralDeadband = this.neutralDeadband;
            fRCTalonSRX.kI = this.kI;
            fRCTalonSRX.currentLimitEnabled = this.currentLimitEnabled;
            fRCTalonSRX.nominalOutputReverse = this.nominalOutputReverse;
            fRCTalonSRX.reverseSoftLimitEnabled = this.reverseSoftLimitEnabled;
            fRCTalonSRX.statusFrame = this.statusFrame;
            fRCTalonSRX.sensorPhase = this.sensorPhase;
            fRCTalonSRX.nominalOutputForward = this.nominalOutputForward;
            fRCTalonSRX.peakOutputForward = this.peakOutputForward;
            fRCTalonSRX.openLoopRampRate = this.openLoopRampRate;
            fRCTalonSRX.reverseSoftLimitThreshold = this.reverseSoftLimitThreshold;
            fRCTalonSRX.feedbackNotContinuous = this.feedbackNotContinuous;
            fRCTalonSRX.kP = this.kP;
            fRCTalonSRX.statusFrameType = this.statusFrameType;
            fRCTalonSRX.allowableClosedLoopError = this.allowableClosedLoopError;
            fRCTalonSRX.velocityMeasurementPeriod = this.velocityMeasurementPeriod;
            fRCTalonSRX.timeout = this.timeout;
            return fRCTalonSRX;
        }
    }
}
