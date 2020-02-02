package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends SubsystemBase {
    private DifferentialDrive differentialDrive;
    private WPI_TalonFX left = new WPI_TalonFX(0);
    private WPI_TalonFX right = new WPI_TalonFX(15);
    private Joystick controller;

    public Drivetrain() {
      left.configFactoryDefault();
      right.configFactoryDefault();
      left.setNeutralMode(NeutralMode.Coast);
      right.setNeutralMode(NeutralMode.Coast);
      left.configPeakOutputForward(0.5);
      right.configPeakOutputForward(0.5);
      left.configPeakOutputReverse(-0.5);
      right.configPeakOutputReverse(-0.5);
      differentialDrive = new DifferentialDrive(left, right);
      controller = new Joystick(0);
    }

// @Override
    public void teleopPeriodic() {
      differentialDrive.arcadeDrive(controller.getY()*0.5, -controller.getZ()*0.5);
    }
}