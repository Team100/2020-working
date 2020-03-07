/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class runs one light
 */
public class Light {
    /**
     * lightPad vars
     */
    private Joystick lightPad;
    private int counter =  0;

    /**
     * States vars
     */
    public static enum lightStates{
        OFF, REDSTEADY, GREENSTEADY, AMBERSTEADY, REDSLOWBLINK, GREENSLOWBLINK, AMBERSLOWBLINK,
            REDFASTBLINK, GREENFASTBLINK, AMBERFASTBLINK
    }
    private lightStates currentState = lightStates.OFF;

    /**
     * Creates new Light
     */
    public Light(Joystick lightPad){
        this.lightPad = lightPad;
    }

    /**
     * Getter and Setter for currentState
     */
    public lightStates getCurrentState(){
        return currentState;
    }

    public void setNewState(lightStates newState){
        currentState = newState;
        counter = 0;
    }

    /**
     * Blinks light according to what state the light is in
     * Runs every teleop periodic
     */
    public void peridicRun(){
        switch(currentState){
            case OFF:
                lightPad.setOutputs(0);
                break;
            case REDSTEADY:
                break;
            case GREENSTEADY:
                break;
            case AMBERSTEADY:
                break;
            case REDSLOWBLINK:
                break;
            case GREENSLOWBLINK:
                break;
            case AMBERSLOWBLINK:
                break;
            case REDFASTBLINK:
                break;
            case GREENFASTBLINK:
                break;
            case AMBERFASTBLINK:
                break;
        }
    }
}
