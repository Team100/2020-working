/**
 * Phoenix Software License Agreement
 *
 * Copyright (C) Cross The Road Electronics.  All rights
 * reserved.
 * 
 * Cross The Road Electronics (CTRE) licenses to you the right to 
 * use, publish, and distribute copies of CRF (Cross The Road) firmware files (*.crf) and 
 * Phoenix Software API Libraries ONLY when in use with CTR Electronics hardware products
 * as well as the FRC roboRIO when in use in FRC Competition.
 * 
 * THE SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS" WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT
 * LIMITATION, ANY WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT SHALL
 * CROSS THE ROAD ELECTRONICS BE LIABLE FOR ANY INCIDENTAL, SPECIAL, 
 * INDIRECT OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF
 * PROCUREMENT OF SUBSTITUTE GOODS, TECHNOLOGY OR SERVICES, ANY CLAIMS
 * BY THIRD PARTIES (INCLUDING BUT NOT LIMITED TO ANY DEFENSE
 * THEREOF), ANY CLAIMS FOR INDEMNITY OR CONTRIBUTION, OR OTHER
 * SIMILAR COSTS, WHETHER ASSERTED ON THE BASIS OF CONTRACT, TORT
 * (INCLUDING NEGLIGENCE), BREACH OF WARRANTY, OR OTHERWISE
 */

/**
 * Enable robot and slowly drive forward.
 * [1] If DS reports errors, adjust CAN IDs and firmware update.
 * [2] If motors are spinning incorrectly, first check gamepad (hold down btn1)
 * [3] If motors are still spinning incorrectly, correct motor inverts.
 * [4] Now that motors are driving correctly, check sensor phase.  If sensor is out of phase, adjust sensor phase.
 * [4] Is only necessary if you have sensors.
 */
package frc.robot;

import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import java.io.IOException;
import java.util.*;
import java.lang.Thread;
//import Server.java;

public class Robot extends TimedRobot {
    /*
     * --- [1] Update CAN Device IDs and use WPI_VictorSPX where necessary.
     *     These CAN Bus Assignments should work with 2019 robot drivetrain.
     */
    WPI_TalonSRX _rghtFront = new WPI_TalonSRX(15);
    WPI_VictorSPX _rghtFollower = new WPI_VictorSPX(13);
    WPI_TalonSRX _leftFront = new WPI_TalonSRX(0);
    WPI_VictorSPX _leftFollower = new WPI_VictorSPX(3);

    DifferentialDrive _diffDrive = new DifferentialDrive(_leftFront, _rghtFront);

    Joystick _joystick = new Joystick(0);

    NetworkTableInstance inst;
    NetworkTable Vision;
    NetworkTableEntry ballFound, ballAngle;
    boolean ballDetected;
    double ballDirection;

    Faults _faults_L = new Faults();
    Faults _faults_R = new Faults();
    double turnx, kP_turn;
    boolean lineDetValid;
    double lineOffset;

    @Override
    public void teleopInit() {
        kP_turn = SmartDashboard.getNumber("kP_turn", 0.01);   
    }

    @Override
    public void teleopPeriodic() {

        String work = "";

        /* get gamepad stick values */
        double forw = -1 * _joystick.getRawAxis(1); /* positive is forward */
        double turn = +1 * _joystick.getRawAxis(2); /* positive is right */
        boolean btn1 = _joystick.getRawButton(1); /* is button is down, print joystick values */
        boolean btn2 = _joystick.getRawButton(2); /* is button2 is pressed, do Line Tracking */
        boolean btn3 = _joystick.getRawButton(3); /* is button3 is pressed, do Ball Tracking */

        lineDetValid = SmartDashboard.getBoolean("LineDetValid", false);
        lineOffset = SmartDashboard.getNumber("LineOffset", 0.0);

        ballDetected = ballFound.getBoolean(false);
        ballDirection = ballAngle.getDouble(0.0);

        if (btn3 && ballDetected) {  // line following if button 3 is pressed
            turnx = kP_turn * ballDirection;
            _diffDrive.arcadeDrive(0.67, turnx);
            System.out.println("Got to Ball Tracking: " + kP_turn + " " + ballDirection);

        } else if (btn2 && lineDetValid) {  // line following if button 2 is pressed
            turnx = kP_turn * lineOffset;
            _diffDrive.arcadeDrive(0.4, turnx);
            System.out.println("Got to Line Following: " + kP_turn + " " + lineOffset);
            SmartDashboard.putNumber("LF Current", _leftFront.getStatorCurrent());
            SmartDashboard.putNumber("RF Current", _rghtFront.getStatorCurrent());

        } else {   // Normal joystick driving

        /* deadband gamepad 10% */
        if (Math.abs(forw) < 0.10) {
            forw = 0;
        }
        if (Math.abs(turn) < 0.10) {
            turn = 0;
        }

        /* drive robot */
        _diffDrive.arcadeDrive(0.5*forw, 0.5*turn);
        SmartDashboard.putNumber("LF Current", _leftFront.getStatorCurrent());
        SmartDashboard.putNumber("RF Current", _rghtFront.getStatorCurrent());

        /*
         * [2] Make sure Gamepad Forward is positive for FORWARD, and GZ is positive for
         * RIGHT
         */
        work += " GF:" + forw + " GT:" + turn;

        /* get sensor values */
        // double leftPos = _leftFront.GetSelectedSensorPosition(0);
        // double rghtPos = _rghtFront.GetSelectedSensorPosition(0);
        double leftVelUnitsPer100ms = _leftFront.getSelectedSensorVelocity(0);
        double rghtVelUnitsPer100ms = _rghtFront.getSelectedSensorVelocity(0);

        work += " L:" + leftVelUnitsPer100ms + " R:" + rghtVelUnitsPer100ms;

        /*
         * drive motor at least 25%, Talons will auto-detect if sensor is out of phase
         */
        _leftFront.getFaults(_faults_L);
        _rghtFront.getFaults(_faults_R);

        if (_faults_L.SensorOutOfPhase) {
            work += " L sensor is out of phase";
        }
        if (_faults_R.SensorOutOfPhase) {
            work += " R sensor is out of phase";
        }

        /* print to console if btn1 is held down */
        if (btn1) {
            System.out.println(work);
        }
    }   // end of if/then/else for line following

    }

    @Override
    public void robotInit() {

        // System.out.println("Got to robotInit");
        /* factory default values */
        _rghtFront.configFactoryDefault();
        _rghtFollower.configFactoryDefault();
        _leftFront.configFactoryDefault();
        _leftFollower.configFactoryDefault();

        /* set up followers */
        _rghtFollower.follow(_rghtFront);
        _leftFollower.follow(_leftFront);

        /* [3] flip values so robot moves forward when stick-forward/LEDs-green */
        _rghtFront.setInverted(true); // !< Update this
        _leftFront.setInverted(false); // !< Update this

        /*
         * set the invert of the followers to match their respective master controllers
         */
        _rghtFollower.setInverted(InvertType.FollowMaster);
        _leftFollower.setInverted(InvertType.FollowMaster);

        /*
         * [4] adjust sensor phase so sensor moves positive when Talon LEDs are green
         */
        _rghtFront.setSensorPhase(true);
        _leftFront.setSensorPhase(true);

        /*
         * WPI drivetrain classes defaultly assume left and right are opposite. call
         * this so we can apply + to both sides when moving forward. DO NOT CHANGE
         */
        _diffDrive.setRightSideInverted(false);

        kP_turn = 0.03;  // Set this to better value after finding what that is

        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable visionTable = inst.getTable("Vision");
        ballFound = visionTable.getEntry("ball_found");
        ballAngle = visionTable.getEntry("ball_angle");

        

        SmartDashboard.putBoolean("LineDetValid", true);
        SmartDashboard.putNumber("LineOffset", 0.0);
        // SmartDashboard.putNumber ("kP_turn", kP_turn);

        /*
        try {
            Thread server = new Thread(new Server(HOST, PORT));
            server.start();

            System.out.format("Running on %s:%s...\n", HOST, PORT);
        } catch(IOException e) {
            e.printStackTrace();
        }
       */ 
    }
}
