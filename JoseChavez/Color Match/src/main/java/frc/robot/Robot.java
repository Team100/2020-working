/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This is a simple example to show how the REV Color Sensor V3 can be used to
 * detect pre-configured colors.
 */
public class Robot extends TimedRobot {
  /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
 // private final WPI_TalonSRX m_motor = new WPI_TalonSRX(1);
  private boolean runMotor = true;
  private int redCounter = 0;
  private int yellowCounter = 0;
  private int blueCounter = 0;
  private int greenCounter = 0;
  private boolean redController = true;
  private boolean blueController = true;
  private boolean yellowController = true;
  private boolean greenController = true;


  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a parameter.
   * The device will be automatically initialized with default parameters.
   */
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  /**
   * A Rev Color Match object is used to register and detect known colors. This
   * can be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */
  private final ColorMatch m_colorMatcher = new ColorMatch();
 /**
   * Note: Any example colors should be calibrated as the user needs, these are
   * here as a basic example.
   */
  private final Color kBlueTarget = ColorMatch.makeColor(0.196, 0.491, 0.311);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.483, 0.371, 0.148);
  private final Color kYellowTarget = ColorMatch.makeColor(0.301, 0.550, 0.148);

  @Override
  public void robotInit() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  public void robotPeriodic() {
    /**
     * The method GetColor() returns a normalized color value from the sensor and
     * can be useful if outputting the color to an RGB LED or similar. To read the
     * raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in well
     * lit conditions (the built in LED is a big help here!). The farther an object
     * is the more light from the surroundings will bleed into the measurements and
     * make it difficult to accurately determine its color.
     */
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
   
    runMotor=true;
    
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
          runMotor=false;
        }
        // Blue case code
        break;
      case 'G':
      if(match.color == kYellowTarget){
        runMotor=false;
      }
        // Green case code
        break;
      case 'R':
        if(match.color == kBlueTarget){
          runMotor=false;
        }
        // Red case code
        break;
      case 'Y':
      if(match.color == kGreenTarget){
        runMotor=false;
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

  @Override
  public void teleopPeriodic() {

    if(runMotor){
    //  m_motor.set(1);
    }
    else{
    //  m_motor.set(0);
    }
    
  }

}
