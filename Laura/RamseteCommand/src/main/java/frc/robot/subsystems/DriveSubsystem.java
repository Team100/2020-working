/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Robot;

public class DriveSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private final WPI_TalonFX m_leftMaster = new WPI_TalonFX(DriveConstants.kLeftMotor1Port);
  private final WPI_TalonFX m_rightMaster = new WPI_TalonFX(DriveConstants.kRightMotor1Port);
  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMaster, m_rightMaster);

  // The gyro sensor
  private final Gyro m_gyro = new ADXRS450_Gyro();

  // Odometry class for tracking robot pose
  private final DifferentialDriveOdometry m_odometry;

  private double leftSimVelocity = 0;
  private double rightSimVelocity = 0;
  private double leftSimPosition = 0;
  private double rightSimPosition = 0;
  private double simHeading = 0;
  private double simRotationalVelocity = 0;

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    m_drive.setSafetyEnabled(false);
    resetEncoders();
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    // TODO implement proper scaling for the encoder readings!!!
    double leftPosition = 0;
    double rightPosition = 0;

    if (Robot.isReal()) {
      leftPosition = m_leftMaster.getSelectedSensorPosition();
      rightPosition = m_rightMaster.getSelectedSensorPosition();
    } else {
      leftSimPosition = leftSimPosition + leftSimVelocity * .02;
      rightSimPosition = rightSimPosition + rightSimVelocity * 0.02;
      leftPosition = leftSimPosition;
      rightPosition = rightSimPosition;
      ChassisSpeeds chassis = DriveConstants.kDriveKinematics
          .toChassisSpeeds(new DifferentialDriveWheelSpeeds(leftSimVelocity, rightSimVelocity));
      simRotationalVelocity = chassis.omegaRadiansPerSecond;
      simHeading = simHeading + new Rotation2d(simRotationalVelocity * 0.02).getDegrees();
      simHeading = Math.IEEEremainder(simHeading, 360);
    }
    m_odometry.update(Rotation2d.fromDegrees(getHeading()), leftPosition, rightPosition);
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    Pose2d myPose = m_odometry.getPoseMeters();
    SmartDashboard.putNumber("Pose_X m", myPose.getTranslation().getX());
    SmartDashboard.putNumber("Pose_Y m", myPose.getTranslation().getY());
    SmartDashboard.putNumber("Pose_Angle deg", myPose.getRotation().getDegrees());

    // System.out.println("POSE " + myPose.toString());
    return myPose;
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    // TODO Get proper scaling for velocities !!!
    double leftVelocity = 0;
    double rightVelocity = 0;
    if (Robot.isReal()) {
      leftVelocity = m_leftMaster.getSelectedSensorVelocity();
      rightVelocity = m_rightMaster.getSelectedSensorVelocity();
    } else {
      leftVelocity = leftSimVelocity;
      rightVelocity = rightSimVelocity;
    }
    return new DifferentialDriveWheelSpeeds(leftVelocity, rightVelocity);
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    resetEncoders();
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Controls the left and right sides of the drive directly with velocity
   * commands.
   *
   * @param leftVelocity  the commanded left velocity
   * @param rightVelocity the commanded right velocity
   */
  public void tankDriveVelocity(double leftVelocity, double rightVelocity) {

    SmartDashboard.putNumber("Drive_LeftVelocity m/s", leftVelocity);
    SmartDashboard.putNumber("Drive_RightVelocity", rightVelocity);
    leftSimVelocity = leftVelocity;
    rightSimVelocity = rightVelocity;
    m_leftMaster.set(ControlMode.Velocity, leftVelocity); // TODO Scaling
    m_rightMaster.set(ControlMode.Velocity, -rightVelocity); // TODO Scaling
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    leftSimPosition = 0;
    rightSimPosition = 0;
    leftSimVelocity = 0;
    rightSimVelocity = 0;
    m_leftMaster.setSelectedSensorPosition(0);
    m_rightMaster.setSelectedSensorPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    // TODO Scaling
    double leftPosition = 0;
    double rightPosition = 0;
    if (Robot.isReal()) {
      leftPosition = m_leftMaster.getSelectedSensorPosition();
      rightPosition = m_rightMaster.getSelectedSensorPosition();
    } else {
      leftPosition = leftSimPosition;
      rightPosition = rightSimPosition;
    }
    return (leftPosition + rightPosition) / 2.0;
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more
   * slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from 180 to 180
   */
  public double getHeading() {
    if (Robot.isReal()) {
      return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
    } else {
      return Math.IEEEremainder(simHeading, 360);
    }
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return m_gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }
}
