/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Map;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utility.T100ColorSensor;

public class ColorSubsystem extends SubsystemBase {
  private final T100ColorSensor m_colorSensor = new T100ColorSensor();
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private String colorString = "Unknown";
  private boolean isRed = false;
  private boolean isYellow = false;
  private boolean isGreen = false;
  private boolean isBlue = false;
  /*
   * Creates a new ColorSubsystem.
   */

  public ColorSubsystem() {
    m_colorSensor.addColorMatch(kBlueTarget);
    m_colorSensor.addColorMatch(kGreenTarget);
    m_colorSensor.addColorMatch(kRedTarget);
    m_colorSensor.addColorMatch(kYellowTarget);
    addChild("Color Sensor", m_colorSensor);
    ShuffleboardTab colorfulTab = Shuffleboard.getTab("Colorful");
    ShuffleboardLayout colorfulLayout = colorfulTab.getLayout("Sensor", BuiltInLayouts.kList);
    colorfulLayout.add("Color Sensor", m_colorSensor);
    ShuffleboardLayout colorBooleansLayout = colorfulTab.getLayout("Current Color", BuiltInLayouts.kGrid)
      .withSize(4,1)
      .withPosition(0,0)
      .withProperties(Map.of("Number of columns", 4, "Number of rows", 1, "Label position", "HIDDEN"));
    colorBooleansLayout.addBoolean("IsRed", () -> isRed).withWidget(BuiltInWidgets.kBooleanBox)
        .withPosition(0,0)
        .withSize(1,1)
        .withProperties(Map.of("Color when true", "Red", "Color when false", "White"));
    colorBooleansLayout.addBoolean("IsYellow", () -> isYellow).withWidget(BuiltInWidgets.kBooleanBox)
        .withPosition(1,0)
        .withSize(1,1)
        .withProperties(Map.of("Color when true", "Yellow", "Color when false", "White"));
    colorBooleansLayout.addBoolean("IsGreen", () -> isGreen).withWidget(BuiltInWidgets.kBooleanBox)
        .withPosition(2,0)
        .withSize(1,1)
        .withProperties(Map.of("Color when true", "Green", "Color when false", "White"));
    colorBooleansLayout.addBoolean("IsBlue", () -> isBlue).withWidget(BuiltInWidgets.kBooleanBox)
        .withPosition(3,0)
        .withSize(1,1)
        .withProperties(Map.of("Color when true", "Blue", "Color when false", "White"));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ColorMatchResult match = m_colorSensor.updateCurColor();
    isRed = false;
    isBlue = false;
    isYellow = false;
    isGreen = false;
    if (match.color == kBlueTarget) {
      colorString = "Blue";
      isBlue = true;
    } else if (match.color == kRedTarget) {
      colorString = "Red";
      isRed = true;
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
      isGreen = true;
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
      isYellow = true;
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putBoolean("Color Sensor Has Reset", m_colorSensor.hasReset());
    SmartDashboard.putBoolean("IsRed", isRed);
    SmartDashboard.putBoolean("IsYellow", isYellow);
    SmartDashboard.putBoolean("IsGreen", isGreen);
    SmartDashboard.putBoolean("IsBlue", isBlue);
  }

}
