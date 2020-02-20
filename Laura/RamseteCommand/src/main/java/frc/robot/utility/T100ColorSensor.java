/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utility;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.util.Color;

public class T100ColorSensor implements Sendable {
    private I2C.Port i2cPort;
    private ColorSensorV3 m_colorSensor;
    private ColorMatch m_colorMatcher = new ColorMatch();
    private Color m_detectedColor = ColorMatch.makeColor(0, 0, 0);
    private ColorMatchResult m_curColorMatch;
    ColorSensorV3.RawColor m_detectedRawColor;

    public T100ColorSensor() {
        this(I2C.Port.kOnboard);
    }

    private T100ColorSensor(I2C.Port i2cPort) {
  
        this.i2cPort = i2cPort;
        m_colorSensor = new ColorSensorV3(this.i2cPort);
        updateCurColor();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Red", () -> m_detectedColor.red,null);
        builder.addDoubleProperty("Blue", () -> m_detectedColor.blue, null);
        builder.addDoubleProperty("Green", () -> m_detectedColor.green, null);
        builder.addDoubleProperty("Raw Red", () -> m_detectedRawColor.red, null);
        builder.addDoubleProperty("Raw Blue", () -> m_detectedRawColor.blue, null);
        builder.addDoubleProperty("Raw Green", () -> m_detectedRawColor.green, null);
    }

    public ColorSensorV3 getColorSensor() {
        return m_colorSensor;
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

    public void resetColorMatch() {
        m_colorMatcher = null;
        m_colorMatcher = new ColorMatch();
    }

    public void addColorMatch(Color matchColor) {
        m_colorMatcher.addColorMatch(matchColor);
    }

    public ColorMatchResult updateCurColor() {
        m_detectedColor = m_colorSensor.getColor();
        m_detectedRawColor = m_colorSensor.getRawColor();
        m_curColorMatch = m_colorMatcher.matchClosestColor(m_detectedColor);
        return m_curColorMatch;
    }

    public boolean hasReset() {
        return m_colorSensor.hasReset();
    }

}
