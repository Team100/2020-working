/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
/**
 * Add your docs here.
 */
public class ControlPanelSpinner extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
 
    /**
     * Change the I2C port below to match the connection of your color sensor
     */
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
   // private final WPI_TalonSRX m_motor = new WPI_TalonSRX(1);
  
    private int redCounter = 0;
    private int yellowCounter = 0;
    private int blueCounter = 0;
    private int greenCounter = 0;
    private boolean redController = true;
    private boolean blueController = true;
    private boolean yellowController = true;
    private boolean greenController = true;
  
  
    
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  
    private final ColorMatch m_colorMatcher = new ColorMatch();
   
    private final Color kBlueTarget = ColorMatch.makeColor(0.196, 0.491, 0.311);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.483, 0.371, 0.148);
    private final Color kYellowTarget = ColorMatch.makeColor(0.301, 0.550, 0.148);

    private final TalonSRX m_motor = new TalonSRX(5);
  @Override
  protected void initDefaultCommand() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);

  }
  public void spin(double speed) {
    m_motor.set(ControlMode.PercentOutput, speed);
  }
  public void periodic() {
    final Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    String colorString;
    
    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
   

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);

    String gameData;
  
   
    gameData = DriverStation.getInstance().getGameSpecificMessage();
   
    
    if(match.color == kRedTarget){
      if (redController){
        redCounter=0;
        redController=false;
      }
      redCounter=redCounter+1;
    }
     else if(match.color!=kRedTarget){
      redController=true;
    }
    if(match.color == kBlueTarget){
      if (blueController){
        blueCounter=0;
        blueController=false;
      }
      blueCounter =blueCounter+1;
    }
     else if(match.color!=kBlueTarget){
      blueController=true;
    }
    if(match.color == kGreenTarget){
      if (greenController){
        greenCounter=0;
        greenController=false;
      }
      greenCounter =greenCounter+1;
    }
     else if(match.color!=kGreenTarget){
      greenController=true;
    }
    if(match.color == kYellowTarget){
      if(yellowController){
        yellowCounter=0;
        yellowController=false;
      }
      yellowCounter =yellowCounter+1;
    }
     else if(match.color!=kYellowTarget){
      yellowController=true;
    }
     SmartDashboard.putNumber("Red Counter", redCounter);
     SmartDashboard.putNumber("Blue Counter", blueCounter);
     SmartDashboard.putNumber("Green Counter", greenCounter);
     SmartDashboard.putNumber("Yellow Counter", yellowCounter);
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
      case 'B':
        if(match.color == kRedTarget){
         
        }
        // Blue case code
        break;
      case 'G':
      if(match.color == kYellowTarget){
       
      }
        // Green case code
        break;
      case 'R':
        if(match.color == kBlueTarget){
          
        }
        // Red case code
        break;
      case 'Y':
      if(match.color == kGreenTarget){
       
      }
        // Yellow case code
        break;
      default:
        // This is corrupt data
        break;
      }
    } else {
      // Code for no data received yet
    }
    SmartDashboard.putString("Game Data", gameData);
  }
  

}
