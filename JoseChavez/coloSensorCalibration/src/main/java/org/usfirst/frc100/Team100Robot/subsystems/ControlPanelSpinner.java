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
import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.OI;

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
   
    private final Color kBlueTarget = ColorMatch.makeColor(Constants.RGB_RED_VALUE_FOR_BLUE, Constants.RGB_GREEN_VALUE_FOR_BLUE, Constants.RGB_BLUE_VALUE_FOR_BLUE);
    private final Color kGreenTarget = ColorMatch.makeColor(Constants.RGB_RED_VALUE_FOR_GREEN, Constants.RGB_GREEN_VALUE_FOR_GREEN, Constants.RGB_BLUE_VALUE_FOR_GREEN);
    private final Color kRedTarget = ColorMatch.makeColor(Constants.RGB_RED_VALUE_FOR_RED, Constants.RGB_GREEN_VALUE_FOR_RED, Constants.RGB_BLUE_VALUE_FOR_RED);
    private final Color kYellowTarget = ColorMatch.makeColor(Constants.RGB_RED_VALUE_FOR_YELLOW, Constants.RGB_GREEN_VALUE_FOR_YELLOW, Constants.RGB_BLUE_VALUE_FOR_YELLOW);

    private final TalonSRX m_motor = new TalonSRX(5);
   
    private final Color detectedColor = m_colorSensor.getColor();
    private final double red = detectedColor.red;
    private final double blue = detectedColor.blue;
    private final double green = detectedColor.green;
    private double configuratingColors;
  @Override
  protected void initDefaultCommand() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);

    configuratingColors= SmartDashboard.getNumber("configurating Colors", 0);
    SmartDashboard.putNumber("configurating Colors", configuratingColors);

  }
  public void spin(double speed) {
    m_motor.set(ControlMode.PercentOutput, speed);

  }
  public void calibrate(){
     Color detectedColor1 = m_colorSensor.getColor();
     double red1 = detectedColor1.red;
     double blue1 = detectedColor1.blue;
     double green1 = detectedColor1.green;

      if (configuratingColors==1){
        SmartDashboard.putNumber("RGB red value for red", red1);
        SmartDashboard.putNumber("RGB green value for red", green1);
        SmartDashboard.putNumber("RGB blue value for red", blue1);

      }
      if (configuratingColors==2){
        SmartDashboard.putNumber("RGB red value for blue", red1);
        SmartDashboard.putNumber("RGB green value for blue", green1);
        SmartDashboard.putNumber("RGB blue value for blue", blue1);
      }
      if (configuratingColors==3){
        SmartDashboard.putNumber("RGB red value for green", red1);
        SmartDashboard.putNumber("RGB green value for green", green1);
        SmartDashboard.putNumber("RGB blue value for green", blue1);
      }
      if (configuratingColors==4){
        SmartDashboard.putNumber("RGB red value for yellow", red1);
        SmartDashboard.putNumber("RGB green value for yellow", green1);
        SmartDashboard.putNumber("RGB blue value for yellow", blue1);
      }
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
  
  
  public void teleopInit(){
   /*  preferences = Preferences.getInstance();
    configuratingColors = preferences.getDouble("configurating Color", 0); */

    configuratingColors= SmartDashboard.getNumber("configurating Colors", 0);
  }
}
