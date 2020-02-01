
// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc100.Team100Robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc100.Team100Robot.commands.*;
import org.usfirst.frc100.Team100Robot.subsystems.*;
import com.kauailabs.navx.frc.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    public static Drivetrain drivetrain;
    public static Shifter shifter;
    public static Climber climber;
    //public static CargoHatchScore cargoHatchScore;
    public static double currentHeading;
    // public static AHRS ahrs;
    public static Global global;
    public static RobotAutoSwitch ras;
    public static ControlPanelSpinner controlPanelSpinner;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
       // ahrs = new AHRS(Constants.NAVX_COMM_PORT);

        drivetrain = new Drivetrain();
        shifter = new Shifter();
        climber = new Climber();
        //cargoHatchScore = new CargoHatchScore();
        global = new Global();
        ras = new RobotAutoSwitch();
        controlPanelSpinner = new ControlPanelSpinner();


        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());
        
        //SmartDashboard.putData("Auto mode", chooser);





        // Put Command Triggers
        //SmartDashboard.putData("CargoManipIntake",new CargoManipulatorIntake());
        //SmartDashboard.putData("CargoManipOuttake",new CargoManipulatorOuttake());
        //SmartDashboard.putData("DTShiftToHigh",new ShiftToHigh());
        //SmartDashboard.putData("DTShiftToLow",new ShiftToLow());
        //SmartDashboard.putData("BillRaise",new BillRaise());
        //SmartDashboard.putData("BillLower",new BillLower());
        //SmartDashboard.putData("PusherExtend",new ExtendPusher());
        //SmartDashboard.putData("PusherRetract",new RetractPusher());
        //SmartDashboard.putData("IntakeElement", new IntakeArmIntakeElement());
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        //Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().run();


        

        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
     }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().run();
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putData(new ShoulderHoming());
        //SmartDashboard.putData("Hatch Score",new HatchScore());
        //SmartDashboard.putData("Hatch Retract", new RetractHatchSystem());
        //SmartDashboard.putData("Bill Out", new HatchBillOut());
        //currentHeading = ahrs.getFusedHeading();

    //     SmartDashboard.putBoolean("IMU_Connected", ahrs.isConnected());
    //     SmartDashboard.putBoolean("IMU_IsCalibrating", ahrs.isCalibrating());
    //     SmartDashboard.putNumber("IMU_Yaw", ahrs.getYaw());
    //     SmartDashboard.putNumber("IMU_Pitch", ahrs.getPitch());
    //     SmartDashboard.putNumber("IMU_Roll", ahrs.getRoll());
    // }

    // public static double getCurrentHeading(){
    //     return currentHeading;
    // }
    }
}
