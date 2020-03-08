/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * This class runs one light
 */
public class Light {
    /**
     * lightPad vars
     */
    public int colorNum;
    public int blinkSpeed;
    public int count;
    public Colors currentColor;
    public Speeds currentSpeed;
    public boolean toggle;

    /**
     * Color enum
     */
    public static enum Colors{
        OFF, RED, GREEN, AMBER
    }

    /**
     * Speed enum
     */
    public static enum Speeds{
        STEADY, SLOW, FAST
    }

    /**
     * Creates new Light
     */
    public Light(){
        colorNum = 0x00;
        blinkSpeed = 0;
        count = 0;
        currentColor = Colors.OFF;
        currentSpeed = Speeds.STEADY;
        toggle = true;
    }

    /**
     * changes the properties of light instance
     * @param newColor: Which color will be displayed
     * @param newSpeed: How fast the blink will be
     */
    public void changeProperties(Colors newColor, Speeds newSpeed){
        count = 0;
        this.currentColor = newColor;
        this.currentSpeed = newSpeed;
        switch(this.currentColor){
            case OFF:
                colorNum = 0x00;
                break;
            case RED:
                colorNum = 0x01;
                break;
            case GREEN:
                colorNum = 0x02;
                break;
            case AMBER:
                colorNum = 0x03;
                break;
        }
        switch(this.currentSpeed){
            case STEADY:
                blinkSpeed = 0;
                toggle = true;
                break;
            case SLOW:
                blinkSpeed = 50;
                break;
            case FAST:
                blinkSpeed = 20;
                break;
        }
    }

}
