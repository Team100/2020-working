/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Safety extends SubsystemBase {
    Ultrasonic ultrasonic;
    /**
     * Creates a new Safety.
     */
    public Safety() {
        ultrasonic = new Ultrasonic(Constants.Safety.ULTRASONIC_DIO_ID_1, Constants.Safety.ULTRASONIC_DIO_ID_2);
    }

    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }

    public boolean objectInRange() {
        return ultrasonic.getRangeInches() <= Constants.Safety.ULTRASONIC_RANGE;
    }

}

