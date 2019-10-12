/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autoselector.UpdatePath;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.Path;

public class Robot extends TimedRobot {
 

  /*
   * Define all of your subsystems here.
   * 
   * This will allow us to refer to our subsystems later
   */
  public static Drivetrain drivetrain = new Drivetrain();

  /**
   * A reference to our joysticks, which allow us to process button presses
   */
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  
  @Override
  public void robotInit() {
    drivetrain.currentPath = Path.DRIVE_FORWARD;
    m_oi = new OI();
    m_chooser.addOption("My Auto", new UpdatePath(Path.DRIVE_FORWARD));
    SmartDashboard.putData("Auto mode", m_chooser);
  }

 
  @Override
  public void robotPeriodic() {
  }

 
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

   

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }


  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void testPeriodic() {
  }
}
