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
import frc.robot.supporting.CustomPathGenerator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.supporting.PathDebugging;
import jaci.pathfinder.*;

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

  /**
   * The command to run for the autonomous
   */
  Command m_autonomousCommand;

  /**
   * The auton selector that will be sent to SmartDashboard
   */
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public void generateTrajectories(){
    System.out.println("Generating Trajectories");
    Waypoint[] straightPath = {new Waypoint(0,0,0), new Waypoint(2,0,0)};
    System.out.println(straightPath.toString());
    Trajectory straight = CustomPathGenerator.generate(straightPath);
    PathDebugging.printTrajectory(straight);
    PathDebugging.printTrajectory(CustomPathGenerator.getLeftTrajectory(straight));
    PathDebugging.printTrajectory(CustomPathGenerator.getRightTrajectory(straight));
    System.out.println("DONE");
  }
  
  /**
   * Run on the robot power on
   * 
   * Do any initial setup steps
   */
  @Override
  public void robotInit() {
    System.out.println("RobotInit");
    m_oi = new OI();
    m_chooser.addOption("Straight", null);
    SmartDashboard.putData("Auto mode", m_chooser);
    generateTrajectories();
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
      //m_autonomousCommand.start();
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
