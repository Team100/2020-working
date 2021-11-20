/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PressureBox extends SubsystemBase {
    AnalogPotentiometer pressureTransducer;
    Solenoid firePiston;
    Solenoid fillValve;
    Compressor fillCompressor;
    /**
     * Creates a new PressureBox.
     */
    public PressureBox() {
        pressureTransducer = new AnalogPotentiometer(Constants.PressureBox.TRANSDUCER_ANALOG_ID, Constants.PressureBox.TRANSDUCER_SCALE, Constants.PressureBox.TRANSDUCER_OFFSET);
        firePiston = new Solenoid(Constants.PressureBox.PISTON_SOLENOID_CAN_ID);
        fillValve = new Solenoid(Constants.PressureBox.FILL_VALVE_SOLENOID_CAN_ID);
        fillCompressor = new Compressor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        SmartDashboard.putNumber("Pressure", checkPressure());
    }

    public double checkPressure() {
        return pressureTransducer.get();
    }

    public void startFilling() {
        fillCompressor.start();
        fillValve.set(true);
    }

    public void stopFilling() {
        fillValve.set(false);
        fillCompressor.stop();
    }

    public void openPiston() {
        if (!firePiston.get()) {
            firePiston.set(true);
        }
    }

    public void closePiston () {
        if (firePiston.get()) {
            firePiston.set(false);
        }
    }
}
