/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private DifferentialDrive differentialDrive;
  private WPI_TalonFX left;
  private WPI_TalonFX right;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    left = new WPI_TalonFX(Constants.Drivetrain.CAN_ID_LEFT);
    right = new WPI_TalonFX(Constants.Drivetrain.CAN_ID_RIGHT);

    WPI_TalonFX[] talons = {left, right};
    for (WPI_TalonFX mc : talons) {
      mc.configFactoryDefault();
      mc.setNeutralMode(NeutralMode.Coast);
      mc.configPeakOutputForward(Constants.Drivetrain.PEAK_OUTPUT);
      mc.configPeakOutputReverse(-Constants.Drivetrain.PEAK_OUTPUT);
    }
    differentialDrive = new DifferentialDrive(left, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double f, double t) {
    differentialDrive.arcadeDrive(f, t);
  }
}
