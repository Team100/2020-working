/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.AutoHelperFunctions;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class CustomRamseteController extends RamseteController {

  private Pose2d m_poseError;
  private Pose2d m_poseTolerance;
  private final double m_b;
  private final double m_zeta;

  public CustomRamseteController(double b, double zeta) {
    super(b, zeta);
    m_b = b;
    m_zeta = zeta;
  }

  public CustomRamseteController() {
    super(2.0, 0.7);
    this.m_b = 2.0;
    this.m_zeta = 0.7;
  }

  private static double sinc(double x) {
    if (Math.abs(x) < 1e-9) {
      return 1.0 - 1.0 / 6.0 * x * x;
    } else {
      return Math.sin(x) / x;
    }
  }

  @Override
  public ChassisSpeeds calculate(Pose2d currentPose, Pose2d poseRef, double linearVelocityRefMeters,
      double angularVelocityRefRadiansPerSecond) {
    this.m_poseError = poseRef.relativeTo(currentPose);

    // Aliases for equation readability
    final double eX = m_poseError.getTranslation().getX();
    final double eY = m_poseError.getTranslation().getY();
    final double eTheta = m_poseError.getRotation().getRadians();
    final double vRef = linearVelocityRefMeters;
    final double omegaRef = angularVelocityRefRadiansPerSecond;

    double k = 2.0 * m_zeta * Math.sqrt(Math.pow(omegaRef, 2) + m_b * Math.pow(vRef, 2));
    if (Constants.DrivetrainConstants.DEBUG) {
      SmartDashboard.putNumber("Current X", currentPose.getTranslation().getX());
      SmartDashboard.putNumber("Reference X", poseRef.getTranslation().getX());

      SmartDashboard.putNumber("eX", eX);
      SmartDashboard.putNumber("eY", eY);
      SmartDashboard.putNumber("eTheta", eTheta);
      SmartDashboard.putNumber("vRef", vRef);
      SmartDashboard.putNumber("omegaRef", omegaRef);

      SmartDashboard.putNumber("k", k);

      SmartDashboard.putNumber("vX [m/s]", vRef * m_poseError.getRotation().getCos() + k * eX);
      SmartDashboard.putNumber("vY [m/s]", 0.0);
      SmartDashboard.putNumber("vOmega [rad/s]", omegaRef + k * eTheta + m_b * vRef * sinc(eTheta) * eY);

      SmartDashboard.putNumber("vX [t/100ms]",
          AutonConversionFactors.convertWPILibTrajectoryUnitsToTalonSRXNativeUnits(
              vRef * m_poseError.getRotation().getCos() + k * eX,
              Constants.DrivetrainConstants.DrivetrainParameters.WHEEL_DIAMETER, false,
              Constants.DrivetrainConstants.DrivetrainParameters.TICKS_PER_REV));

    }

    return new ChassisSpeeds(vRef * m_poseError.getRotation().getCos() + k * eX, 0.0,
        omegaRef + k * eTheta + m_b * vRef * sinc(eTheta) * eY);

  }

  public ChassisSpeeds calculate(Pose2d currentPose, Trajectory.State desiredState) {
    return calculate(currentPose, desiredState.poseMeters, desiredState.velocityMetersPerSecond,
        desiredState.velocityMetersPerSecond * desiredState.curvatureRadPerMeter);
  }

}