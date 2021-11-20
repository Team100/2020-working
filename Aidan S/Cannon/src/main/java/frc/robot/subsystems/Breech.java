/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Breech extends SubsystemBase {
    Solenoid breechSolenoid;
    Solenoid ejectSolenoid;
    DigitalInput cartridgeLimitSwitch;
    int number;

    /**
     */
    public Breech() {
        breechSolenoid = new Solenoid(Constants.Breech.BARREL_SOLENOID_CAN_ID);
        ejectSolenoid = new Solenoid(Constants.Breech.EJECT_SOLENOID_CAN_ID);
        cartridgeLimitSwitch = new DigitalInput(Constants.Breech.CARTRIDGE_LIMIT_SWITCH_DIO_ID);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    public void open() {
        breechSolenoid.set(true);
    }

    public void close() {
        breechSolenoid.set(false);
    }

    public void eject() {
        Timer myTimer = new Timer();
        ejectSolenoid.set(true);
        myTimer.start();

        while (myTimer.get() < 2.0);

        ejectSolenoid.set(false);
        myTimer.stop();
    }

    // public void closeEjectSolenoid() {
    //     ejectSolenoid.set(true);
    // }

    // public void openEjectSolenoid() {
    //     ejectSolenoid.set(false);
    // }

    public boolean isOpen() {
        return breechSolenoid.get();
    }

    public boolean isLoaded() {
        return cartridgeLimitSwitch.get();
    }

}
