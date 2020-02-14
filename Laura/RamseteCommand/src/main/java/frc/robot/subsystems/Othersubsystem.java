/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utility.FRCTalonSRX;

/**
 * import edu.wpi.first.wpilibj2.command.SubsystemBase;
 * 
 * /** Add your docs here.
 */
public class Othersubsystem extends SubsystemBase {

  private FRCTalonSRX m_motor;
  
	public Othersubsystem() {   
    m_motor = new FRCTalonSRX.FRCTalonSRXBuilder(1)
      .withSmartDashboardPath("OtherSubsystemMotor")
      .withSmartDashboardPutEnabled(true)
      .withSensorPhase(true)
      .build();  
    
    addChild("OtherMotor", m_motor.getMotor());
    addChild("OtherFRCTalonSRX", m_motor);
  }  
  
	@Override
	public void periodic() {
    m_motor.updateSmartDashboard();
    
		
	}
}
