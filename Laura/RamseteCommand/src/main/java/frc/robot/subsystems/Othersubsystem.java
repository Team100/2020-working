/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utility.FRCTalonSRX;

/**
 * import edu.wpi.first.wpilibj2.command.SubsystemBase;
 * 
 * /** Add your docs here.
 */
public class Othersubsystem extends SubsystemBase {

  private FRCTalonSRX m_motor;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private Color m_detectedColor = ColorMatch.makeColor(0,0,0);
  private ColorMatchResult m_curColorMatch;
  private DigitalInput m_rightSight = new DigitalInput(0);
  private AnalogInput m_forceSensor = new AnalogInput(0);
  
	public Othersubsystem() {   
    m_motor = new FRCTalonSRX.FRCTalonSRXBuilder(1)
      .withSmartDashboardPath("OtherSubsystemMotor")
      .withSmartDashboardPutEnabled(true)
      .withSensorPhase(true)
      .withFeedbackNotContinuous(true)
      .build();  
    
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);  
    addChild("OtherMotor", m_motor.getMotor());
    addChild("OtherFRCTalonSRX", m_motor);
    
    addChild("Scheduler", this);
    addChild("Right Sight", m_rightSight);
    addChild("Force Sensor", m_forceSensor);
    
  }  
  
	@Override
	public void periodic() {
    m_motor.updateSmartDashboard();
    Color detectedColor = m_colorSensor.getColor();
    ColorSensorV3.RawColor raw  = m_colorSensor.getRawColor();
    m_detectedColor = detectedColor;
    /**
     * Run the color match algorithm on our detected color
     */
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    m_curColorMatch = match;
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

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putBoolean("Color Sensor Has Reset", m_colorSensor.hasReset());
    SmartDashboard.putNumber("RawRed", raw.red);
    SmartDashboard.putNumber("RawGreen", raw.green);
    SmartDashboard.putNumber("RawBlue", raw.blue);
    
  }
  
  public Color getCurrentColor() {
    return m_detectedColor;
  }

  public Color getCurrentColorMatch() {
    if (m_curColorMatch == null) {
      return m_detectedColor;
    } else {
      return m_curColorMatch.color;
    } 
  }
}
