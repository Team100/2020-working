/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utility.FRCTalonFX;

/**
 * import edu.wpi.first.wpilibj2.command.SubsystemBase;
 * 
 * /** Add your docs here.
 */
public class Testsubsystem extends SubsystemBase {

  private FRCTalonFX m_motor;
	public Testsubsystem() {
    
    m_motor = FRCTalonFX.FRCTalonFXBuilder.aFRCTalonFX()
      .withCanID(4)
      .withSmartDashboardPath("TestSubsystemMotor")
      .withSmartDashboardPutEnabled(true)
      .build();   
  }  
  
	@Override
	public void periodic() {
    m_motor.updateSmartDashboard();
    
		
	}
}
