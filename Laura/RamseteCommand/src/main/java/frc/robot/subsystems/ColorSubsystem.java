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

  public enum CP_Color {
    RED(0.561, 0.232, 0.114, "Red"), 
    YELLOW(0.361, 0.524, 0.113, "Yellow"), 
    BLUE(0.143, 0.427, 0.429, "Blue"),
    GREEN(0.197, 0.561, 0.240, "Green"),
    UNKNOWN(0,0,0,"Unknown");

    private double red_component;
    private double green_component;
    private double blue_component;
    private Color color;
    private String name;

    CP_Color(double red_component, double green_component, double blue_component, String name) {
      this.name = name;
      update_components (red_component, green_component, blue_component);
     }

    void update_components(double R, double G, double B) {
      this.red_component = R;
      this.green_component = G;
      this.blue_component = B;
      this.color = ColorMatch.makeColor(this.red_component, this.green_component, this.blue_component);
    }

    Color getColor() {
      return color;
    }

    String getName() {
      return this.name;
    }

  }

  private final T100ColorSensor m_colorSensor = new T100ColorSensor();
  private String colorString = "Unknown";
  private boolean isRed = false;
  private boolean isYellow = false;
  private boolean isGreen = false;
  private boolean isBlue = false;
  /*
   * Creates a new ColorSubsystem.
   */

  public ColorSubsystem() {
    m_colorSensor.addColorMatch(CP_Color.BLUE.getColor());
    m_colorSensor.addColorMatch(CP_Color.GREEN.getColor());
    m_colorSensor.addColorMatch(CP_Color.RED.getColor());
    m_colorSensor.addColorMatch(CP_Color.YELLOW.getColor());
    addChild("Color Sensor", m_colorSensor);
    ShuffleboardTab colorfulTab = Shuffleboard.getTab("Colorful");
    ShuffleboardLayout colorfulLayout = colorfulTab.getLayout("Sensor", BuiltInLayouts.kList);
    colorfulLayout.add("Color Sensor", m_colorSensor);
    ShuffleboardLayout colorBooleansLayout = colorfulTab.getLayout("Current Color", BuiltInLayouts.kGrid).withSize(4, 1)
        .withPosition(0, 0)
        .withProperties(Map.of("Number of columns", 4, "Number of rows", 1, "Label position", "HIDDEN"));
    colorBooleansLayout.addBoolean("IsRed", () -> isRed).withWidget(BuiltInWidgets.kBooleanBox).withPosition(0, 0)
        .withSize(1, 1).withProperties(Map.of("Color when true", "Red", "Color when false", "White"));
    colorBooleansLayout.addBoolean("IsYellow", () -> isYellow).withWidget(BuiltInWidgets.kBooleanBox).withPosition(1, 0)
        .withSize(1, 1).withProperties(Map.of("Color when true", "Yellow", "Color when false", "White"));
    colorBooleansLayout.addBoolean("IsGreen", () -> isGreen).withWidget(BuiltInWidgets.kBooleanBox).withPosition(2, 0)
        .withSize(1, 1).withProperties(Map.of("Color when true", "Green", "Color when false", "White"));
    colorBooleansLayout.addBoolean("IsBlue", () -> isBlue).withWidget(BuiltInWidgets.kBooleanBox).withPosition(3, 0)
        .withSize(1, 1).withProperties(Map.of("Color when true", "Blue", "Color when false", "White"));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ColorMatchResult match = m_colorSensor.updateCurColor();
    isRed = false;
    isBlue = false;
    isYellow = false;
    isGreen = false;
    CP_Color current;
    if (match.color == CP_Color.BLUE.getColor()) {
      current = CP_Color.BLUE;
      isBlue = true;
    } else if (match.color == CP_Color.RED.getColor()) {
      current = CP_Color.RED;
      isRed = true;
    } else if (match.color == CP_Color.GREEN.getColor()) {
      current = CP_Color.GREEN;
      isGreen = true;
    } else if (match.color == CP_Color.YELLOW.getColor()) {
      current = CP_Color.YELLOW;
      isYellow = true;
    } else {
      current = CP_Color.UNKNOWN;
    }

    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", current.getName());
    SmartDashboard.putBoolean("Color Sensor Has Reset", m_colorSensor.hasReset());
    SmartDashboard.putBoolean("IsRed", isRed);
    SmartDashboard.putBoolean("IsYellow", isYellow);
    SmartDashboard.putBoolean("IsGreen", isGreen);
    SmartDashboard.putBoolean("IsBlue", isBlue);
  }

}
