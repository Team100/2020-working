/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private TalonFX master;
    private TalonFX follower;
    private double shooterSetpoint = 0;
    private PowerDistributionPanel pdp;

    /**
     * Creates a new Shooter.
     */
    public Shooter() {
        master = new TalonFX(Constants.Shooter.FALCON_1_CANID);
        follower = new TalonFX(Constants.Shooter.FALCON_2_CANID);
        TalonFX[] t = {master, follower};
        pdp = new PowerDistributionPanel();

        for (TalonFX mc: t) {
            mc.configFactoryDefault();
            mc.setNeutralMode(NeutralMode.Coast);
            mc.configPeakOutputForward(Constants.Shooter.PEAK_OUTPUT);
            mc.configPeakOutputReverse(-Constants.Shooter.PEAK_OUTPUT);
        }

        follower.follow(master);
        follower.setInverted(InvertType.OpposeMaster);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        master.set(ControlMode.PercentOutput, -shooterSetpoint);
        SmartDashboard.putNumber("SD Percent Output", shooterSetpoint);
        SmartDashboard.putNumber("SD Sensor Velocity", master.getSensorCollection().getIntegratedSensorVelocity());
        SmartDashboard.putNumber("Current 14", pdp.getCurrent(14));
        SmartDashboard.putNumber("Current 15", pdp.getCurrent(15));
        SmartDashboard.putNumber("Output Velocity", (
                master.getSensorCollection().getIntegratedSensorVelocity() /  //Encoder ticks per 100ms
                Constants.Shooter.ENCODER_CPR *                         // Revolutions per 100ms
                10 *                                                    // Revolutions per second
                2 * Math.PI *                                           // Radians per second
                Constants.Shooter.WHEEL_RADIUS) /                       // Units per sec (Units = units of wheel diameter)
                2);                                                     /* Wheel is essentially 'rolling without slipping'
                                                                        ** on the hood. This means the overall velocity of
                                                                        ** the ball is equal to half the tangential speed
                                                                        ** of the wheel. */
                                                                        /* Final number from all this ^^ is the velocity 
                                                                        ** of the ball in units per second. */
                SmartDashboard.putNumber("Shooter RPM", (
                    master.getSensorCollection().getIntegratedSensorVelocity() /  //Encoder ticks per 100ms
                    Constants.Shooter.ENCODER_CPR *                         // Revolutions per 100ms
                    10 * 60));                                              // Revolutions per minute
                    
            }

    public void set(double s) {
        shooterSetpoint = s;
    }
}
