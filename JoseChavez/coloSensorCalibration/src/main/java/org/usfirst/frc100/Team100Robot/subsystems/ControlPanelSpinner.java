/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import org.usfirst.frc100.Team100Robot.Constants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

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
  private int[] colorSequence = { 1, 2, 3, 4 };
  private int currentColor;
  private int nextColor;
  private int revolutionsCounter = 0;
  private boolean stop = false;
  

  // = Preferences.getInstance().getDouble(".....",
  // Constants.RGB_RED_VALUE_FOR_BLUE);)

  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(
      Preferences.getInstance().getDouble("RedTile_BlueComponent", Constants.RGB_RED_VALUE_FOR_BLUE),
      Preferences.getInstance().getDouble("GreenTile_BlueComponent", Constants.RGB_GREEN_VALUE_FOR_BLUE),
      Preferences.getInstance().getDouble("BlueTile_BlueComponent", Constants.RGB_BLUE_VALUE_FOR_BLUE));
  private final Color kGreenTarget = ColorMatch.makeColor(
      Preferences.getInstance().getDouble("RedTile_GreenComponent", Constants.RGB_RED_VALUE_FOR_GREEN),
      Preferences.getInstance().getDouble("GreenTile_GreenComponent", Constants.RGB_GREEN_VALUE_FOR_GREEN),
      Preferences.getInstance().getDouble("BlueTile_GreenComponent", Constants.RGB_BLUE_VALUE_FOR_GREEN));
  private final Color kRedTarget = ColorMatch.makeColor(
      Preferences.getInstance().getDouble("RedTile_RedComponent", Constants.RGB_RED_VALUE_FOR_RED),
      Preferences.getInstance().getDouble("GreenTile_RedComponent", Constants.RGB_GREEN_VALUE_FOR_RED),
      Preferences.getInstance().getDouble("BlueTile_RedComponent", Constants.RGB_BLUE_VALUE_FOR_RED));
  private final Color kYellowTarget = ColorMatch.makeColor(
      Preferences.getInstance().getDouble("RedTile_YellowComponent", Constants.RGB_RED_VALUE_FOR_YELLOW),
      Preferences.getInstance().getDouble("GreenTile_YellowComponent", Constants.RGB_GREEN_VALUE_FOR_YELLOW),
      Preferences.getInstance().getDouble("BlueTile_YellowComponent", Constants.RGB_BLUE_VALUE_FOR_YELLOW));

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
   

    configuratingColors = SmartDashboard.getNumber("configurating Colors", 0);
    SmartDashboard.putNumber("configurating Colors", configuratingColors);

    //revolutionsCounter=0;
  }

  public void spin(double speed) {
    m_motor.set(ControlMode.PercentOutput, speed);

  }

  

  /*
   * public void threeTImes(){ while (revolutionsCounter>=24){ spin(1); }
   */
  // }
  public void calibrate() {
    configuratingColors = SmartDashboard.getNumber("configurating Colors", 0);
    Color detectedColor1 = m_colorSensor.getColor();
    double red1 = detectedColor1.red;
    double blue1 = detectedColor1.blue;
    double green1 = detectedColor1.green;

    if (configuratingColors == 1) {
      Preferences.getInstance().putDouble("RedTile_RedComponent", red1);
      Preferences.getInstance().putDouble("GreenTile_RedComponent", green1);
      Preferences.getInstance().putDouble("BlueTile_RedComponent", blue1);

    }
    if (configuratingColors == 2) {
      Preferences.getInstance().putDouble("RedTile_BlueComponent", red1);
      Preferences.getInstance().putDouble("GreenTile_BlueComponent", green1);
      Preferences.getInstance().putDouble("BlueTile_BlueComponent", blue1);

    }
    if (configuratingColors == 3) {

      Preferences.getInstance().putDouble("RedTile_GreenComponent", red1);
      Preferences.getInstance().putDouble("GreenTile_GreenComponent", green1);
      Preferences.getInstance().putDouble("BlueTile_GreenComponent", blue1);

    }
    if (configuratingColors == 4) {
      Preferences.getInstance().putDouble("RedTile_YellowComponent", red1);
      Preferences.getInstance().putDouble("GreenTile_YellowComponent", green1);
      Preferences.getInstance().putDouble("BlueTile_YellowComponent", blue1);

    }
  }

  public int getCurrentColor() {
    final Color detectedColor = m_colorSensor.getColor();
    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      return 1;
    } else if (match.color == kRedTarget) {
      return 3;
    } else if (match.color == kGreenTarget) {
      return 2;
    } else if (match.color == kYellowTarget) {
      return 4;
    } else{
      return 0;
    }
  }

  public int getRevolutionsCounter() {
    return revolutionsCounter;
  }

  public void resetTo0(){
    revolutionsCounter = 0;
  }

  public boolean reachedColor(){
    return stop;
  }

  public void resetToFalse(){
    stop = false;
  }
  public void periodic() {
    final Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    String colorString;

    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

  if (match.confidence>.91){
    if (match.color == kBlueTarget) {
        currentColor = 1;
        
        if (nextColor == currentColor) {
          revolutionsCounter = revolutionsCounter + 1;
          nextColor = 2;
        } else {
          nextColor = 2;
      }
        colorString = "Blue";
      } else if (match.color == kRedTarget) {
        currentColor = 3;
        //if (nextColor == currentColor) {
          //revolutionsCounter = revolutionsCounter + 1;
          //nextColor = 4;
        //} else {
          nextColor = 4;
        //}
        colorString = "Red";
      } else if (match.color == kGreenTarget) {
        colorString = "Green";
        currentColor = 2;
        //if (nextColor == currentColor) {
          //revolutionsCounter = revolutionsCounter + 1;
        // nextColor = 3;
      // } else {
          nextColor = 3;
      // }
      } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
        currentColor = 4;
        //if (nextColor == currentColor) {
          //revolutionsCounter = revolutionsCounter + 1;
        // nextColor = 1;
        //} else {
          nextColor = 1;
        //}
      } else {
        colorString = "Unknown";
      }}
      else{
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
    SmartDashboard.putNumber("Counter of Changes", revolutionsCounter);
    SmartDashboard.putData("Conrol Panel Spinner", this);

    String gameData;

    gameData = DriverStation.getInstance().getGameSpecificMessage();

    if (match.color == kRedTarget) {
      if (redController) {
        redCounter = 0;
        redController = false;
      }
      redCounter = redCounter + 1;
    } else if (match.color != kRedTarget) {
      redController = true;
    }
    if (match.color == kBlueTarget) {
      if (blueController) {
        blueCounter = 0;
        blueController = false;
      }
      blueCounter = blueCounter + 1;
    } else if (match.color != kBlueTarget) {
      blueController = true;
    }
    if (match.color == kGreenTarget) {
      if (greenController) {
        greenCounter = 0;
        greenController = false;
      }
      greenCounter = greenCounter + 1;
    } else if (match.color != kGreenTarget) {
      greenController = true;
    }
    if (match.color == kYellowTarget) {
      if (yellowController) {
        yellowCounter = 0;
        yellowController = false;
      }
      yellowCounter = yellowCounter + 1;
    } else if (match.color != kYellowTarget) {
      yellowController = true;
    }
    SmartDashboard.putNumber("Red Counter", redCounter);
    SmartDashboard.putNumber("Blue Counter", blueCounter);
    SmartDashboard.putNumber("Green Counter", greenCounter);
    SmartDashboard.putNumber("Yellow Counter", yellowCounter);
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
      case 'B':
      if (match.confidence>.95){
        if (match.color == kRedTarget) {
          stop = true;
        }}
        // Blue case code
        break;
      case 'G':
      if (match.confidence>.95){
        if (match.color == kYellowTarget) {
          stop = true;
        }}
        // Green case code
        break;
      case 'R':
      if (match.confidence>.95){
        if (match.color == kBlueTarget) {
          stop = true;
        }}
        // Red case code
        break;
      case 'Y':
      if (match.confidence>.95){
        if (match.color == kGreenTarget) {
          stop = true;
        }}
        // Yellow case code
        break;
      default:
        // This is corrupt data
        break;
      }
    } else {
      stop = false;
      // Code for no data received yet
    }
    SmartDashboard.putString("Game Data", gameData);
  }

  public void teleopInit() {
    /*
     * preferences = Preferences.getInstance(); configuratingColors =
     * preferences.getDouble("configurating Color", 0);
     */
    //revolutionsCounter=0;
    configuratingColors = SmartDashboard.getNumber("configurating Colors", 0);
  }

  public ControlPanelSpinner() {
    m_motor.setNeutralMode(NeutralMode.Brake);
    m_motor.configContinuousCurrentLimit(25, 10);
  }
}
