/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This runs the lights of the HUD
 */
public class HUDSystem {
    /**
     * NOTE: index 0: Top left light
     * NOTE: index 1: Top right light
     * NOTE: index 2: Bottom right light
     * NOTE: index 3: Bottom left light
     */
    private Light[] HUDSystem = new Light[4];

    /**
     * Creates new HUDSystem
     */
    public HUDSystem(Joystick Light0, Joystick Light1, Joystick Light2, Joystick Light3){
        this.HUDSystem[0] = new Light(Light0);
        this.HUDSystem[1] = new Light(Light1);
        this.HUDSystem[2] = new Light(Light2);
        this.HUDSystem[3] = new Light(Light3);
    }

    /**
     * Runs all lights in array
     * Runs every teleop periodic
     */
    public void periodicRun(){

    }
}
