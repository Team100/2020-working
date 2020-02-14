/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utility.FRCTalonFX;

/**
 * import edu.wpi.first.wpilibj2.command.SubsystemBase;
 * 
 * /** Add your docs here.
 */
public class Testsubsystem extends SubsystemBase {

  private FRCTalonFX m_motor;
  private DigitalInput m_input = new DigitalInput(1);
  private Encoder m_rio_encoder = new Encoder(2,3);
  private PIDController m_rio_PID = new PIDController(0, 0, 0);
  
	public Testsubsystem() {
    m_motor = new FRCTalonFX.FRCTalonFXBuilder(4)
       .withSmartDashboardPath("TestSubsystemMotor")
       .withSmartDashboardPutEnabled(true)
      .build();  
    
    addChild("TestMotor", m_motor.getMotor());
    addChild("TestFRCTalonFX", m_motor);
    addChild("TestDigitalInput", m_input);
    addChild("Test RIO Encoder", m_rio_encoder);
    addChild("Test RIO PID Controller", m_rio_PID);
  }  
  
	@Override
	public void periodic() {
    m_motor.updateSmartDashboard();
    
		
	}
}
