/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {


  public TalonSRX shooterMaster;

  public TalonSRX shooterFollower;

  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem() {
    shooterMaster = new TalonSRX(0);
    shooterFollower = new TalonSRX(1);
    shooterFollower.follow(shooterMaster);
    if(SmartDashboard.getNumber("percentOuput", -32767) != -32767){
      publishSettingsToSmartDashboard();
    }
  }

  public void publishSettingsToSmartDashboard(){
    SmartDashboard.putNumber("percentOutput", 0);
    SmartDashboard.putBoolean("Flush", false);


  }

  public void syncVelocityWithSmartDashboard(){
    SmartDashboard.putNumber("Sensor Velocity", shooterMaster.getSelectedSensorVelocity());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if(SmartDashboard.getBoolean("Flush", false)){
      double speed = SmartDashboard.getNumber("percentOutput", 0);
      shooterMaster.set(ControlMode.PercentOutput, speed);
      SmartDashboard.putBoolean("Flush", false);
    }

  }
}
