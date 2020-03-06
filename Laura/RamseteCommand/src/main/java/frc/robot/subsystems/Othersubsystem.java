/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utility.FRCTalonSRX;

/**
 * import edu.wpi.first.wpilibj2.command.SubsystemBase;
 * 
 * /** Add your docs here.
 */
public class Othersubsystem extends SubsystemBase {

  private FRCTalonSRX m_motor;
  private DigitalInput m_rightSight = new DigitalInput(0);
  private AnalogInput m_forceSensor = new AnalogInput(0);
  private Servo m_servo = new Servo(0);
  
	public Othersubsystem() {   
    m_motor = new FRCTalonSRX.FRCTalonSRXBuilder(1)
      .withSmartDashboardPath("OtherSubsystemMotor")
      .withSmartDashboardPutEnabled(true)
      .withSensorPhase(true)
      .withFeedbackNotContinuous(true)
      .build();  
    

    addChild("OtherMotor", m_motor.getMotor());
    addChild("OtherFRCTalonSRX", m_motor);
    
    addChild("Scheduler", this);
    addChild("Right Sight", m_rightSight);
    addChild("Force Sensor", m_forceSensor);
    addChild("Servo", m_servo);
    
  }  
  
	@Override
	public void periodic() {
    m_motor.updateSmartDashboard();
    
    
  }
  
 
}
