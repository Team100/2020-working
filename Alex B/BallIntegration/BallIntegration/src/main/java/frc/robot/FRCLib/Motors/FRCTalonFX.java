/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Motors;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An abstraction for the Talon SRX for debugging information
 */
public class FRCTalonFX {

    public void reset(){
        this.motor.configFactoryDefault();
    }
    public void driveVelocity(double velocity){
        this.motor.set(ControlMode.Velocity, velocity);
    }
    public void drivePercentOutput(double percentOutput){
        this.motor.set(ControlMode.PercentOutput, percentOutput);
        System.out.println(percentOutput);
        
    }
    public void driveMotionMagic(double setpoint){
        this.motor.set(ControlMode.MotionProfile, setpoint);
    }
    public void drivePosition(double setpoint){
        this.motor.set(ControlMode.Position, setpoint);
    }
    public void driveCurrent(double current){
        this.motor.set(ControlMode.Current, current);
    }

    public int getSensorVelocity(){
        return this.motor.getSelectedSensorVelocity();
    }
    public int getSelectedSensorPosition(){
        return this.motor.getSelectedSensorPosition();
    }

    public void follow(FRCTalonFX master){
        this.motor.follow(master.motor);
    }

    public void updateSmartDashboard(){
        if(this.isSmartDashboardPutEnabled()){

            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/percentOutput",this.motor.getMotorOutputPercent());

            SmartDashboard.putNumber(this.getSmartDashboardPath() + "/allowableClosedLoopError", this.getAllowableClosedLoopError());
            SmartDashboard.putBoolean(this.getSmartDashboardPath()+"/auxPIDPolarity", this.isAuxPIDPolarity());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/canID",this.getCanID());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/closedLoopRampRate", this.getClosedLoopRampRate());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/currentLimit", this.getCurrentLimit());
            SmartDashboard.putBoolean(this.getSmartDashboardPath()+"/currentLimitEnabled",this.isCurrentLimitEnabled());
            SmartDashboard.putBoolean(this.getSmartDashboardPath()+"/feedbackNotContinuous",this.isFeedbackNotContinuous());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/feedbackPort",this.getFeedbackPort());
            SmartDashboard.putBoolean(this.getSmartDashboardPath()+"/forwardSoftLimitEnabled",this.isForwardSoftLimitEnabled());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/forwardSoftLimitThreshold",this.getForwardSoftLimitThreshold());
            SmartDashboard.putBoolean(this.getSmartDashboardPath()+"/inverted",this.isInverted());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/kP",this.getkP());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/kI", this.getkI());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/kD", this.getkD());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/kF", this.getkF());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/motionAcceleration",this.getMotionAcceleration());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/motionCruiseVelocity",this.getMotionCruiseVelocity());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/motionCurveStrength",this.getMotionCurveStrength());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/motionProfileTrajectoryPeriod", this.motionProfileTrajectoryPeriod);
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/neutralDeadband",this.getNeutralDeadband());
            SmartDashboard.putBoolean(this.getSmartDashboardPath()+"/neutralDeadband",(this.getNeutralMode() == NeutralMode.Coast)?false:true);
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/nominalOutputForward",this.getNominalOutputForward());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/nominalOutputReverse",this.getNominalOutputReverse());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/openLoopRampRate",this.getOpenLoopRampRate());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/peakOutputForward",this.getPeakOutputForward());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/peakOutputReverse",this.getPeakOutputReverse());
            SmartDashboard.putBoolean(this.getSmartDashboardPath()+"/reverseSoftLimitEnabled",this.isReverseSoftLimitEnabled());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/reverseSoftLimitThreshold",this.getReverseSoftLimitThreshold());
            SmartDashboard.putBoolean(this.getSmartDashboardPath()+"/sensorPhase",this.isSensorPhase());
            SmartDashboard.putString(this.getSmartDashboardPath()+"/SmartDashboardPath",this.getSmartDashboardPath());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/statusFrame",this.getStatusFrame());
            SmartDashboard.putString(this.getSmartDashboardPath()+"/statusFrameType",this.getStatusFrameType().toString());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/timeout",this.getTimeout());
            SmartDashboard.putString(this.getSmartDashboardPath()+"/velocityMeasurementPeriod",this.getVelocityMeasurementPeriod().toString());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/velocityMeasurementWindow",this.getVelocityMeasurementWindow());
            SmartDashboard.putNumber(this.getSmartDashboardPath()+"/voltageCompensationSaturation",this.getVoltageCompensationSaturation());

        }

    }

    ///////////////////////////////////////////////////////////////////////////
    /**
     * A direct reference to the TalonSRX motor, designed for direct control
     */
    public TalonFX motor;
    ///////////////////////////////////////////////////////////////////////////


    /**
     * The Can ID of the selected motor
     */
    private int canID;

    /**
     * The inversion of the motor
     *
     * true inverts the motor
     */
    private boolean inverted;

    /**
     * The feedback port of the motor
     * Default is 0
     */
    private int feedbackPort = 0;

    /**
     * The timeout of the motor in ms
     *
     * Default is 10
     */
    private int timeout = 10;

    /**
     * The sensor phase of the motor
     *
     * true inverts the encoder signal
     */
    private boolean sensorPhase;

    /**
     * The kP value of the motor's PID controller
     */
    private double kP;

    /**
     * The kI value of the motor's PID controller
     */
    private double kI;

    /**
     * The kD value of the motor's PID controller
     */
    private double kD;

    /**
     * The kF value of the motor's PID controller
     */
    private double kF;

    /**
     * The acceptable closed loop error in ticks
     */
    private int allowableClosedLoopError;

    /**
     * The type of status frame
     */
    private StatusFrameEnhanced statusFrameType;

    /**
     * The status frame of the motor
     */
    private int statusFrame;

    /**
     * Is a current limit enabled
     *
     * a currentLimit must be set if this is true
     */
    private boolean currentLimitEnabled;

    /**
     * The current limit set (amps)
     *
     * currentLimitEnabled must be set for this to activate
     */
    private int currentLimit;

    /**
     * The neutral mode of the motor controller
     */
    private NeutralMode neutralMode;

    /**
     * Should a smartDashboardPut be enabled
     *
     * true will put to SmartDashboard
     */
    private boolean smartDashboardPutEnabled;

    /**
     * The path that the motor controller should report to
     */
    private String smartDashboardPath;

    /**
     * The ramp rate when controlled in open loop
     */
    private double openLoopRampRate;

    /**
     * The ramp rate when controlled in closed loop
     */
    private double closedLoopRampRate;

    /**
     * The forward nominal output
     */
    private double nominalOutputForward;

    /**
     * The reverse nominal output
     */
    private double nominalOutputReverse;

    /**
     * The forward peak output
     */
    private double peakOutputForward;

    /**
     * The reverse peak output
     */
    private double peakOutputReverse;

    /**
     * The neutral deadband
     */
    private double neutralDeadband;

    /**
     * The saturation for voltage compensation
     */
    private double voltageCompensationSaturation;

    /**
     * The measurement period for velocity control
     */
    private VelocityMeasPeriod velocityMeasurementPeriod;

    /**
     * The measurement window for the velocity control
     */
    private int velocityMeasurementWindow;

    /**
     * Is a forward soft limit enabled
     */
    private boolean forwardSoftLimitEnabled;

    /**
     * The forward soft limit set
     */
    private int forwardSoftLimitThreshold;

    /**
     * Is a reverse soft limit enabled
     */
    private boolean reverseSoftLimitEnabled;

    /**
     * The reverse soft limit set
     */
    private int reverseSoftLimitThreshold;

    /**
     * Is auxiliary polarity enabled
     */
    private boolean auxPIDPolarity;

    /**
     * The motion cruise velocity set
     */
    private int motionCruiseVelocity;

    /**
     * The motion acceleration set
     */
    private int motionAcceleration;

    /**
     * The strength of the motion curve
     */
    private int motionCurveStrength;

    /**
     * The period set when using motion profiles
     */
    private int motionProfileTrajectoryPeriod;

    /**
     * Is continuous or discontinuous feedback enabled
     */
    private boolean feedbackNotContinuous;

    public void updatePIDController(){
        motor.config_kP(0,this.getkP());
        motor.config_kI(0, this.getkI());
        motor.config_kD(0, this.getkD());
        motor.config_kF(1, this.getkF());



    }
    public FRCTalonFX configure(){
        motor = new TalonFX(this.getCanID());
        System.out.println(this.motor.configFactoryDefault());
        System.out.println("#################RESET");
        if(this.isInverted()){motor.setInverted(this.isInverted());System.out.println("Configuring Inverted");}
        if(this.isCurrentLimitEnabled()){
          

        }
        if(this.isFeedbackNotContinuous()){motor.configFeedbackNotContinuous(this.isFeedbackNotContinuous(), this.getTimeout());System.out.println("Configuring Feedback Continuity");}

        if(this.isForwardSoftLimitEnabled()){
        motor.configForwardSoftLimitEnable(this.isForwardSoftLimitEnabled());
        motor.configForwardSoftLimitThreshold(this.getForwardSoftLimitThreshold());
        System.out.println("Configuring forward soft limit");
        }
        if(this.getMotionAcceleration() != 0){
        motor.configMotionAcceleration(this.getMotionAcceleration());
        motor.configMotionCruiseVelocity(this.getMotionCruiseVelocity());
        System.out.println("Configuring acceleration");
        }
        if(this.getNeutralMode() != null){
            motor.setNeutralMode(this.getNeutralMode());
            System.out.println("Setting Neutral Mode");
        }
        if(this.getNominalOutputForward() != 0 || this.getNominalOutputReverse() != 0){
            motor.configNominalOutputForward(this.getNominalOutputForward());
        motor.configNominalOutputReverse(this.getNominalOutputReverse());
            System.out.println("Setting Nominal Output");
        }

        if(this.getOpenLoopRampRate() != 0){
            motor.configOpenloopRamp(this.getOpenLoopRampRate());
            System.out.println("Setting Open Loop Ramp Rate");

        }

        if(this.getPeakOutputForward() != 0 || this.getPeakOutputReverse() != 0){
            motor.configPeakOutputForward(this.getPeakOutputForward());
        motor.configPeakOutputForward(this.getPeakOutputReverse());
        System.out.println("Setting Peak Output");
        }
        
        if(this.isReverseSoftLimitEnabled()){
            motor.configReverseSoftLimitEnable(this.isReverseSoftLimitEnabled());
        motor.configReverseSoftLimitThreshold(this.getReverseSoftLimitThreshold());
        System.out.println("setting reverse soft limit enabled");
        }
        if(this.isSensorPhase()){
            motor.setSensorPhase(this.isSensorPhase());
            System.out.println("setting sensor phase");

        }
        if(this.getStatusFrame() != 0){
            motor.setStatusFramePeriod(this.getStatusFrameType(), this.getStatusFrame());
            System.out.println("Setting Frame Period");

        }
        if(this.getVelocityMeasurementPeriod() != null || this.getVelocityMeasurementWindow() != 0){
            motor.configVelocityMeasurementPeriod(this.getVelocityMeasurementPeriod());
            motor.configVelocityMeasurementWindow(this.getVelocityMeasurementWindow());
            System.out.println("Setting Velocity Measurement Period");
        }
        if(this.getVoltageCompensationSaturation() != 0){
            motor.configVoltageCompSaturation(this.getVoltageCompensationSaturation());
            System.out.println("Setting Saturation");

        }
        if(this.getkP() != 0 || this.getkI() != 0 || this.getkD() != 0 || this.getkF() != 0){
            updatePIDController();
            System.out.println("Setting PID Controller");
        }
        return this;
    }

    public TalonFX getMotor() {
        return motor;
    }

    public void setMotor(TalonFX motor) {
        this.motor = motor;
    }

    public int getCanID() {
        return canID;
    }

    public void setCanID(int canID) {
        this.canID = canID;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public int getFeedbackPort() {
        return feedbackPort;
    }

    public void setFeedbackPort(int feedbackPort) {
        this.feedbackPort = feedbackPort;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isSensorPhase() {
        return sensorPhase;
    }

    public void setSensorPhase(boolean sensorPhase) {
        this.sensorPhase = sensorPhase;
    }

    public double getkP() {
        return kP;
    }

    public void setkP(double kP) {
        this.kP = kP;
        this.updatePIDController();
    }

    public double getkI() {
        return kI;
    }

    public void setkI(double kI) {
        this.kI = kI;
        this.updatePIDController();
    }

    public double getkD() {
        return kD;
    }

    public void setkD(double kD) {
        this.kD = kD;
        this.updatePIDController();
    }

    public double getkF() {
        return kF;
    }

    public void setkF(double kF) {
        this.kF = kF;
        this.updatePIDController();
    }

    public int getAllowableClosedLoopError() {
        return allowableClosedLoopError;
    }

    public void setAllowableClosedLoopError(int allowableClosedLoopError) {
        this.allowableClosedLoopError = allowableClosedLoopError;
    }

    public StatusFrameEnhanced getStatusFrameType() {
        return statusFrameType;
    }

    public void setStatusFrameType(StatusFrameEnhanced statusFrameType) {
        this.statusFrameType = statusFrameType;
    }

    public int getStatusFrame() {
        return statusFrame;
    }

    public void setStatusFrame(int statusFrame) {
        this.statusFrame = statusFrame;
    }

    public boolean isCurrentLimitEnabled() {
        return currentLimitEnabled;
    }

    public void setCurrentLimitEnabled(boolean currentLimitEnabled) {
        this.currentLimitEnabled = currentLimitEnabled;
    }

    public int getCurrentLimit() {
        return currentLimit;
    }

    public void setCurrentLimit(int currentLimit) {
        this.currentLimit = currentLimit;
    }

    public NeutralMode getNeutralMode() {
        return neutralMode;
    }

    public void setNeutralMode(NeutralMode neutralMode) {
        this.neutralMode = neutralMode;
    }

    public boolean isSmartDashboardPutEnabled() {
        return smartDashboardPutEnabled;
    }

    public void setSmartDashboardPutEnabled(boolean smartDashboardPutEnabled) {
        this.smartDashboardPutEnabled = smartDashboardPutEnabled;
    }

    public String getSmartDashboardPath() {
        return smartDashboardPath;
    }

    public void setSmartDashboardPath(String smartDashboardPath) {
        this.smartDashboardPath = smartDashboardPath;
    }

    public double getOpenLoopRampRate() {
        return openLoopRampRate;
    }

    public void setOpenLoopRampRate(double openLoopRampRate) {
        this.openLoopRampRate = openLoopRampRate;
    }

    public double getClosedLoopRampRate() {
        return closedLoopRampRate;
    }

    public void setClosedLoopRampRate(double closedLoopRampRate) {
        this.closedLoopRampRate = closedLoopRampRate;
    }

    public double getNominalOutputForward() {
        return nominalOutputForward;
    }

    public void setNominalOutputForward(double nominalOutputForward) {
        this.nominalOutputForward = nominalOutputForward;
    }

    public double getNominalOutputReverse() {
        return nominalOutputReverse;
    }

    public void setNominalOutputReverse(double nominalOutputReverse) {
        this.nominalOutputReverse = nominalOutputReverse;
    }

    public double getPeakOutputForward() {
        return peakOutputForward;
    }

    public void setPeakOutputForward(double peakOutputForward) {
        this.peakOutputForward = peakOutputForward;
    }

    public double getPeakOutputReverse() {
        return peakOutputReverse;
    }

    public void setPeakOutputReverse(double peakOutputReverse) {
        this.peakOutputReverse = peakOutputReverse;
    }

    public double getNeutralDeadband() {
        return neutralDeadband;
    }

    public void setNeutralDeadband(double neutralDeadband) {
        this.neutralDeadband = neutralDeadband;
    }

    public double getVoltageCompensationSaturation() {
        return voltageCompensationSaturation;
    }

    public void setVoltageCompensationSaturation(double voltageCompensationSaturation) {
        this.voltageCompensationSaturation = voltageCompensationSaturation;
    }

    public VelocityMeasPeriod getVelocityMeasurementPeriod() {
        return velocityMeasurementPeriod;
    }

    public void setVelocityMeasurementPeriod(VelocityMeasPeriod velocityMeasurementPeriod) {
        this.velocityMeasurementPeriod = velocityMeasurementPeriod;
    }

    public int getVelocityMeasurementWindow() {
        return velocityMeasurementWindow;
    }

    public void setVelocityMeasurementWindow(int velocityMeasurementWindow) {
        this.velocityMeasurementWindow = velocityMeasurementWindow;
    }

    public boolean isForwardSoftLimitEnabled() {
        return forwardSoftLimitEnabled;
    }

    public void setForwardSoftLimitEnabled(boolean forwardSoftLimitEnabled) {
        this.forwardSoftLimitEnabled = forwardSoftLimitEnabled;
    }

    public int getForwardSoftLimitThreshold() {
        return forwardSoftLimitThreshold;
    }

    public void setForwardSoftLimitThreshold(int forwardSoftLimitThreshold) {
        this.forwardSoftLimitThreshold = forwardSoftLimitThreshold;
    }

    public boolean isReverseSoftLimitEnabled() {
        return reverseSoftLimitEnabled;
    }

    public void setReverseSoftLimitEnabled(boolean reverseSoftLimitEnabled) {
        this.reverseSoftLimitEnabled = reverseSoftLimitEnabled;
    }

    public int getReverseSoftLimitThreshold() {
        return reverseSoftLimitThreshold;
    }

    public void setReverseSoftLimitThreshold(int reverseSoftLimitThreshold) {
        this.reverseSoftLimitThreshold = reverseSoftLimitThreshold;
    }

    public boolean isAuxPIDPolarity() {
        return auxPIDPolarity;
    }

    public void setAuxPIDPolarity(boolean auxPIDPolarity) {
        this.auxPIDPolarity = auxPIDPolarity;
    }

    public int getMotionCruiseVelocity() {
        return motionCruiseVelocity;
    }

    public void setMotionCruiseVelocity(int motionCruiseVelocity) {
        this.motionCruiseVelocity = motionCruiseVelocity;
    }

    public int getMotionAcceleration() {
        return motionAcceleration;
    }

    public void setMotionAcceleration(int motionAcceleration) {
        this.motionAcceleration = motionAcceleration;
    }

    public int getMotionCurveStrength() {
        return motionCurveStrength;
    }

    public void setMotionCurveStrength(int motionCurveStrength) {
        this.motionCurveStrength = motionCurveStrength;
    }

    public int getMotionProfileTrajectoryPeriod() {
        return motionProfileTrajectoryPeriod;
    }

    public void setMotionProfileTrajectoryPeriod(int motionProfileTrajectoryPeriod) {
        this.motionProfileTrajectoryPeriod = motionProfileTrajectoryPeriod;
    }

    public boolean isFeedbackNotContinuous() {
        return feedbackNotContinuous;
    }

    public void setFeedbackNotContinuous(boolean feedbackNotContinuous) {
        this.feedbackNotContinuous = feedbackNotContinuous;
    }


    public static final class FRCTalonFXBuilder {
        private int canID;
        private boolean inverted;
        private int feedbackPort = 0;
        private int timeout = 10;
        private boolean sensorPhase;
        private double kP;
        private double kI;
        private double kD;
        private double kF;
        private int allowableClosedLoopError;
        private StatusFrameEnhanced statusFrameType;
        private int statusFrame;
        private boolean currentLimitEnabled;
        private int currentLimit;
        private NeutralMode neutralMode;
        private boolean smartDashboardPutEnabled;
        private String smartDashboardPath;
        private double openLoopRampRate;
        private double closedLoopRampRate;
        private double nominalOutputForward;
        private double nominalOutputReverse;
        private double peakOutputForward;
        private double peakOutputReverse;
        private double neutralDeadband;
        private double voltageCompensationSaturation;
        private VelocityMeasPeriod velocityMeasurementPeriod;
        private int velocityMeasurementWindow;
        private boolean forwardSoftLimitEnabled;
        private int forwardSoftLimitThreshold;
        private boolean reverseSoftLimitEnabled;
        private int reverseSoftLimitThreshold;
        private boolean auxPIDPolarity;
        private int motionCruiseVelocity;
        private int motionAcceleration;
        private int motionCurveStrength;
        private int motionProfileTrajectoryPeriod;
        private boolean feedbackNotContinuous;

        public FRCTalonFXBuilder() {
        }

        public static FRCTalonFXBuilder aFRCTalonFX() {
            return new FRCTalonFXBuilder();
        }

        public FRCTalonFXBuilder withCanID(int canID) {
            this.canID = canID;
            return this;
        }

        public FRCTalonFXBuilder withInverted(boolean inverted) {
            this.inverted = inverted;
            return this;
        }

        public FRCTalonFXBuilder withFeedbackPort(int feedbackPort) {
            this.feedbackPort = feedbackPort;
            return this;
        }

        public FRCTalonFXBuilder withTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public FRCTalonFXBuilder withSensorPhase(boolean sensorPhase) {
            this.sensorPhase = sensorPhase;
            return this;
        }

        public FRCTalonFXBuilder withKP(double kP) {
            this.kP = kP;
            return this;
        }

        public FRCTalonFXBuilder withKI(double kI) {
            this.kI = kI;
            return this;
        }

        public FRCTalonFXBuilder withKD(double kD) {
            this.kD = kD;
            return this;
        }

        public FRCTalonFXBuilder withKF(double kF) {
            this.kF = kF;
            return this;
        }

        public FRCTalonFXBuilder withAllowableClosedLoopError(int allowableClosedLoopError) {
            this.allowableClosedLoopError = allowableClosedLoopError;
            return this;
        }

        public FRCTalonFXBuilder withStatusFrameType(StatusFrameEnhanced statusFrameType) {
            this.statusFrameType = statusFrameType;
            return this;
        }

        public FRCTalonFXBuilder withStatusFrame(int statusFrame) {
            this.statusFrame = statusFrame;
            return this;
        }

        public FRCTalonFXBuilder withCurrentLimitEnabled(boolean currentLimitEnabled) {
            this.currentLimitEnabled = currentLimitEnabled;
            return this;
        }

        public FRCTalonFXBuilder withCurrentLimit(int currentLimit) {
            this.currentLimit = currentLimit;
            return this;
        }

        public FRCTalonFXBuilder withNeutralMode(NeutralMode neutralMode) {
            this.neutralMode = neutralMode;
            return this;
        }

        public FRCTalonFXBuilder withSmartDashboardPutEnabled(boolean smartDashboardPutEnabled) {
            this.smartDashboardPutEnabled = smartDashboardPutEnabled;
            return this;
        }

        public FRCTalonFXBuilder withSmartDashboardPath(String smartDashboardPath) {
            this.smartDashboardPath = smartDashboardPath;
            return this;
        }

        public FRCTalonFXBuilder withOpenLoopRampRate(double openLoopRampRate) {
            this.openLoopRampRate = openLoopRampRate;
            return this;
        }

        public FRCTalonFXBuilder withClosedLoopRampRate(double closedLoopRampRate) {
            this.closedLoopRampRate = closedLoopRampRate;
            return this;
        }

        public FRCTalonFXBuilder withNominalOutputForward(double nominalOutputForward) {
            this.nominalOutputForward = nominalOutputForward;
            return this;
        }

        public FRCTalonFXBuilder withNominalOutputReverse(double nominalOutputReverse) {
            this.nominalOutputReverse = nominalOutputReverse;
            return this;
        }

        public FRCTalonFXBuilder withPeakOutputForward(double peakOutputForward) {
            this.peakOutputForward = peakOutputForward;
            return this;
        }

        public FRCTalonFXBuilder withPeakOutputReverse(double peakOutputReverse) {
            this.peakOutputReverse = peakOutputReverse;
            return this;
        }

        public FRCTalonFXBuilder withNeutralDeadband(double neutralDeadband) {
            this.neutralDeadband = neutralDeadband;
            return this;
        }

        public FRCTalonFXBuilder withVoltageCompensationSaturation(double voltageCompensationSaturation) {
            this.voltageCompensationSaturation = voltageCompensationSaturation;
            return this;
        }

        public FRCTalonFXBuilder withVelocityMeasurementPeriod(VelocityMeasPeriod velocityMeasurementPeriod) {
            this.velocityMeasurementPeriod = velocityMeasurementPeriod;
            return this;
        }

        public FRCTalonFXBuilder withVelocityMeasurementWindow(int velocityMeasurementWindow) {
            this.velocityMeasurementWindow = velocityMeasurementWindow;
            return this;
        }

        public FRCTalonFXBuilder withForwardSoftLimitEnabled(boolean forwardSoftLimitEnabled) {
            this.forwardSoftLimitEnabled = forwardSoftLimitEnabled;
            return this;
        }

        public FRCTalonFXBuilder withForwardSoftLimitThreshold(int forwardSoftLimitThreshold) {
            this.forwardSoftLimitThreshold = forwardSoftLimitThreshold;
            return this;
        }

        public FRCTalonFXBuilder withReverseSoftLimitEnabled(boolean reverseSoftLimitEnabled) {
            this.reverseSoftLimitEnabled = reverseSoftLimitEnabled;
            return this;
        }

        public FRCTalonFXBuilder withReverseSoftLimitThreshold(int reverseSoftLimitThreshold) {
            this.reverseSoftLimitThreshold = reverseSoftLimitThreshold;
            return this;
        }

        public FRCTalonFXBuilder withAuxPIDPolarity(boolean auxPIDPolarity) {
            this.auxPIDPolarity = auxPIDPolarity;
            return this;
        }

        public FRCTalonFXBuilder withMotionCruiseVelocity(int motionCruiseVelocity) {
            this.motionCruiseVelocity = motionCruiseVelocity;
            return this;
        }

        public FRCTalonFXBuilder withMotionAcceleration(int motionAcceleration) {
            this.motionAcceleration = motionAcceleration;
            return this;
        }

        public FRCTalonFXBuilder withMotionCurveStrength(int motionCurveStrength) {
            this.motionCurveStrength = motionCurveStrength;
            return this;
        }

        public FRCTalonFXBuilder withMotionProfileTrajectoryPeriod(int motionProfileTrajectoryPeriod) {
            this.motionProfileTrajectoryPeriod = motionProfileTrajectoryPeriod;
            return this;
        }

        public FRCTalonFXBuilder withFeedbackNotContinuous(boolean feedbackNotContinuous) {
            this.feedbackNotContinuous = feedbackNotContinuous;
            return this;
        }

        public FRCTalonFX build() {
            FRCTalonFX fRCTalonFX = new FRCTalonFX();
            fRCTalonFX.setCanID(canID);
            fRCTalonFX.setInverted(inverted);
            fRCTalonFX.setFeedbackPort(feedbackPort);
            fRCTalonFX.setTimeout(timeout);
            fRCTalonFX.setSensorPhase(sensorPhase);
            fRCTalonFX.setAllowableClosedLoopError(allowableClosedLoopError);
            fRCTalonFX.setStatusFrameType(statusFrameType);
            fRCTalonFX.setStatusFrame(statusFrame);
            fRCTalonFX.setCurrentLimitEnabled(currentLimitEnabled);
            fRCTalonFX.setCurrentLimit(currentLimit);
            fRCTalonFX.setNeutralMode(neutralMode);
            fRCTalonFX.setSmartDashboardPutEnabled(smartDashboardPutEnabled);
            fRCTalonFX.setSmartDashboardPath(smartDashboardPath);
            fRCTalonFX.setOpenLoopRampRate(openLoopRampRate);
            fRCTalonFX.setClosedLoopRampRate(closedLoopRampRate);
            fRCTalonFX.setNominalOutputForward(nominalOutputForward);
            fRCTalonFX.setNominalOutputReverse(nominalOutputReverse);
            fRCTalonFX.setPeakOutputForward(peakOutputForward);
            fRCTalonFX.setPeakOutputReverse(peakOutputReverse);
            fRCTalonFX.setNeutralDeadband(neutralDeadband);
            fRCTalonFX.setVoltageCompensationSaturation(voltageCompensationSaturation);
            fRCTalonFX.setVelocityMeasurementPeriod(velocityMeasurementPeriod);
            fRCTalonFX.setVelocityMeasurementWindow(velocityMeasurementWindow);
            fRCTalonFX.setForwardSoftLimitEnabled(forwardSoftLimitEnabled);
            fRCTalonFX.setForwardSoftLimitThreshold(forwardSoftLimitThreshold);
            fRCTalonFX.setReverseSoftLimitEnabled(reverseSoftLimitEnabled);
            fRCTalonFX.setReverseSoftLimitThreshold(reverseSoftLimitThreshold);
            fRCTalonFX.setAuxPIDPolarity(auxPIDPolarity);
            fRCTalonFX.setMotionCruiseVelocity(motionCruiseVelocity);
            fRCTalonFX.setMotionAcceleration(motionAcceleration);
            fRCTalonFX.setMotionCurveStrength(motionCurveStrength);
            fRCTalonFX.setMotionProfileTrajectoryPeriod(motionProfileTrajectoryPeriod);
            fRCTalonFX.setFeedbackNotContinuous(feedbackNotContinuous);
            fRCTalonFX.kF = this.kF;
            fRCTalonFX.kD = this.kD;
            fRCTalonFX.kI = this.kI;
            fRCTalonFX.kP = this.kP;
            return fRCTalonFX.configure();
        }
    }
}
