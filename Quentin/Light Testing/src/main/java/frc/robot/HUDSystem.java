/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Light.Colors;
import frc.robot.Light.Speeds;

/**
 * This runs the lights of the HUD
 * NOTE: Binary will go (TOPLEFT ---> TOPRIGHT ---> BOTTOMLEFT ---> BOTTOMRIGHT)
 */
public class HUDSystem {
    /**
     * NOTE: index 0: Top left light
     * NOTE: index 1: Top right light
     * NOTE: index 2: Bottom left light
     * NOTE: index 3: Bottom right light
     */
    private Light[] HUDSystem = new Light[4];
    private Joystick lightpad;

    /**
     * position enum
     */
    public static enum Positions{
        TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT
    }

    /**
     * Creates new HUDSystem
     */
    public HUDSystem(Joystick lightpad){
        for(int i = 0; i < HUDSystem.length; i++){
            HUDSystem[i] = new Light();
        }
        this.lightpad = lightpad;
    }

    /**
     * Interface for how programer will change light indicator
     * @param newPos: Which light will be switched
     * @param newColor: What the new color will be
     * @param newSpeed: How fast the new color will blink
     */
    public void changeLight(Positions newPos, Colors newColor, Speeds newSpeed){
        switch(newPos){
            case TOPLEFT:
                this.HUDSystem[0].changeProperties(newColor, newSpeed);
                break;
            case TOPRIGHT:
                this.HUDSystem[1].changeProperties(newColor, newSpeed);
                break;
            case BOTTOMLEFT:
                this.HUDSystem[2].changeProperties(newColor, newSpeed);
                break;
            case BOTTOMRIGHT:
                this.HUDSystem[3].changeProperties(newColor, newSpeed);
                break;
        }
    }

    /**
     * Runs all lights in array
     * Runs every teleop periodic
     */
    public void periodicRun(){
        int output = 0x00;
        for(int i = 0; i < HUDSystem.length; i++){
            Light currentLight = HUDSystem[i];
            currentLight.count++;
            if(currentLight.count >= currentLight.blinkSpeed && currentLight.currentSpeed != Speeds.STEADY){
                currentLight.toggle = !currentLight.toggle;
                currentLight.count = 0;
                
            }
            if(currentLight.toggle){
                output += currentLight.colorNum;
                if(i != 3) output = output << 2;
                
            }else{
                output += 0x00;
                if(i != 3) output = output << 2;
            }
            
            

        }
        lightpad.setOutputs(output);

    }
}
