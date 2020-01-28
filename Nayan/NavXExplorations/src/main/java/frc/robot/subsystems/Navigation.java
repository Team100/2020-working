/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Navigation extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private final AHRS m_ahrs = new AHRS(SerialPort.Port.kUSB);

  public Navigation() {
    zeroYaw();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    /* Display 6-axis Processed Angle Data */
    SmartDashboard.putBoolean("IMU_Connected", m_ahrs.isConnected());
    SmartDashboard.putBoolean("IMU_IsCalibrating", m_ahrs.isCalibrating());
    SmartDashboard.putNumber("IMU_Yaw", m_ahrs.getYaw());
    SmartDashboard.putNumber("IMU_Pitch", m_ahrs.getPitch());
    SmartDashboard.putNumber("IMU_Roll", m_ahrs.getRoll());

    /* Display tilt-corrected, Magnetometer-based heading (requires */
    /* magnetometer calibration to be useful) */

    SmartDashboard.putNumber("IMU_CompassHeading", m_ahrs.getCompassHeading());

    /* Display 9-axis Heading (requires magnetometer calibration to be useful) */
    SmartDashboard.putNumber("IMU_FusedHeading", m_ahrs.getFusedHeading());

    /* These functions are compatible w/the WPI Gyro Class, providing a simple */
    /* path for upgrading from the Kit-of-Parts gyro to the navx-MXP */

    SmartDashboard.putNumber("IMU_TotalYaw", m_ahrs.getAngle());
    SmartDashboard.putNumber("IMU_YawRateDPS", m_ahrs.getRate());

    /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */

    SmartDashboard.putNumber("IMU_Accel_X", m_ahrs.getWorldLinearAccelX());
    SmartDashboard.putNumber("IMU_Accel_Y", m_ahrs.getWorldLinearAccelY());
    SmartDashboard.putBoolean("IMU_IsMoving", m_ahrs.isMoving());
    SmartDashboard.putBoolean("IMU_IsRotating", m_ahrs.isRotating());

    /* Display estimates of velocity/displacement. Note that these values are */
    /* not expected to be accurate enough for estimating robot position on a */
    /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
    /* of these errors due to single (velocity) integration and especially */
    /* double (displacement) integration. */

    SmartDashboard.putNumber("Velocity_X", m_ahrs.getVelocityX());
    SmartDashboard.putNumber("Velocity_Y", m_ahrs.getVelocityY());
    SmartDashboard.putNumber("Displacement_X", m_ahrs.getDisplacementX());
    SmartDashboard.putNumber("Displacement_Y", m_ahrs.getDisplacementY());

    /* Display Raw Gyro/Accelerometer/Magnetometer Values */
    /* NOTE: These values are not normally necessary, but are made available */
    /* for advanced users. Before using this data, please consider whether */
    /* the processed data (see above) will suit your needs. */

    SmartDashboard.putNumber("RawGyro_X", m_ahrs.getRawGyroX());
    SmartDashboard.putNumber("RawGyro_Y", m_ahrs.getRawGyroY());
    SmartDashboard.putNumber("RawGyro_Z", m_ahrs.getRawGyroZ());
    SmartDashboard.putNumber("RawAccel_X", m_ahrs.getRawAccelX());
    SmartDashboard.putNumber("RawAccel_Y", m_ahrs.getRawAccelY());
    SmartDashboard.putNumber("RawAccel_Z", m_ahrs.getRawAccelZ());
    SmartDashboard.putNumber("RawMag_X", m_ahrs.getRawMagX());
    SmartDashboard.putNumber("RawMag_Y", m_ahrs.getRawMagY());
    SmartDashboard.putNumber("RawMag_Z", m_ahrs.getRawMagZ());
    SmartDashboard.putNumber("IMU_Temp_C", m_ahrs.getTempC());

    /* Omnimount Yaw Axis Information */
    /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount */
    AHRS.BoardYawAxis yaw_axis = m_ahrs.getBoardYawAxis();
    SmartDashboard.putString("YawAxisDirection", yaw_axis.up ? "Up" : "Down");
    SmartDashboard.putNumber("YawAxis", yaw_axis.board_axis.getValue());

    /* Sensor Board Information */
    SmartDashboard.putString("FirmwareVersion", m_ahrs.getFirmwareVersion());

    /* Quaternion Data */
    /* Quaternions are fascinating, and are the most compact representation of */
    /* orientation data. All of the Yaw, Pitch and Roll Values can be derived */
    /* from the Quaternions. If interested in motion processing, knowledge of */
    /* Quaternions is highly recommended. */
    SmartDashboard.putNumber("QuaternionW", m_ahrs.getQuaternionW());

    SmartDashboard.putNumber("QuaternionX", m_ahrs.getQuaternionX());
    SmartDashboard.putNumber("QuaternionY", m_ahrs.getQuaternionY());
    SmartDashboard.putNumber("QuaternionZ", m_ahrs.getQuaternionZ());

    /* Connectivity Debugging Support */
    SmartDashboard.putNumber("IMU_Byte_Count", m_ahrs.getByteCount());
    SmartDashboard.putNumber("IMU_Update_Count", m_ahrs.getUpdateCount());



cornerUp();
  }

  public void zeroYaw() {
    m_ahrs.zeroYaw();
  }

  public void cornerUp() {

    if(m_ahrs.getPitch()>3&&m_ahrs.getRoll()>3){//back left
      SmartDashboard.putString("CornerUp", "back left");
    }
    if(m_ahrs.getPitch()<-3&&m_ahrs.getRoll()>3){//front left
      SmartDashboard.putString("CornerUp", "front left");
    }
    if(m_ahrs.getPitch()>3&&m_ahrs.getRoll()<-3){//back right
      SmartDashboard.putString("CornerUp", "back right");
    }
    if(m_ahrs.getPitch()<-3&&m_ahrs.getRoll()<-3){//front right
      SmartDashboard.putString("CornerUp", "front right");
    } 
  }
}
