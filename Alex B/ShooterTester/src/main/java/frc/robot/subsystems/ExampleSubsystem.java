/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {

  ///////////////////////////////////////////////////////////////////////////////////
  //                                                                               //
  // Information About Robot Configuration                                         //
  //                                                                               //
  ///////////////////////////////////////////////////////////////////////////////////
  //                                                                               //
  // The robot settings were originally set with the ShooterBringup project        //
  //                                                                               //
  // The ports are as follow:                                                      //
  // - shooterMaster (14)                                                          //
  // - shooterFollower (2)                                                         //
  //                                                                               //
  // In addition, the following changes were made from the standard configuration: //
  // - sensorPhase = true                                                          //
  // - follower.inverted = true                                                    //
  //                                                                               //
  ///////////////////////////////////////////////////////////////////////////////////

  public TalonSRX shooterMaster;

  public TalonSRX shooterFollower;

  public Preferences preferences;

  public PowerDistributionPanel pdp = new PowerDistributionPanel();

  public class NetworkTableNames {
    public static final String SD_PERCENT_OUTPUT = "SD Percent Output";
    public static final String SD_Flush = "SD Flush SD";
    public static final String SD_Velocity = "SD Sensor Velocity";
    public static final String SD_Current = "SD Master Current";
    public static final String SD_Burn = "SD Burn and Flush P";

    public static final String P_MASTER_CAN_ID = "P Master CAN ID";
    public static final String P_FOLLOWER_CAN_ID = "P Follower CAN ID";
    public static final String P_MASTER_INVERTED = "P Master Inverted";
    public static final String P_FOLLOWER_INVERTED = "P Follower Inverted";
    public static final String P_INITIALIZED = "P Initialized";
  }

  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem() {
    preferences = Preferences.getInstance();
    shooterMaster = new TalonSRX(14);
    shooterFollower = new TalonSRX(2);

    shooterMaster.setSensorPhase(true);

    shooterFollower.follow(shooterMaster);
    shooterFollower.setInverted(true);
    /*if (SmartDashboard.getNumber(NetworkTableNames.SD_PERCENT_OUTPUT, -32767) == -32767) {
      publishSettingsToSmartDashboard();
    }*/
    publishSettingsToSmartDashboard();

    /*if (preferences.getBoolean(NetworkTableNames.P_INITIALIZED, false)) {
      updateSmartDashboardFromPreferences();
    }*/

    //updateSmartDashboardFromPreferences();
  }

  public void publishSettingsToRobotPreferences() {
    preferences.putInt(NetworkTableNames.P_MASTER_CAN_ID, 14);
    preferences.putInt(NetworkTableNames.P_FOLLOWER_CAN_ID, 2);

    preferences.putBoolean(NetworkTableNames.P_MASTER_INVERTED, false);
    preferences.putBoolean(NetworkTableNames.P_FOLLOWER_INVERTED, true);

    preferences.putBoolean(NetworkTableNames.P_INITIALIZED, true);

  }

  public void publishSettingsToSmartDashboard() {
    SmartDashboard.putNumber(NetworkTableNames.SD_PERCENT_OUTPUT, 0);
    SmartDashboard.putBoolean(NetworkTableNames.SD_Flush, false);
    SmartDashboard.putBoolean(NetworkTableNames.SD_Burn, false);

  }

  public void syncVelocityWithSmartDashboard() {
    SmartDashboard.putNumber(NetworkTableNames.SD_Velocity, shooterMaster.getSelectedSensorVelocity());
    SmartDashboard.putNumber(NetworkTableNames.SD_Current, pdp.getCurrent(14));
  }

  public void updateSmartDashboardFromPreferences() {
    publishSettingsToRobotPreferences();
    
    SmartDashboard.putNumber(NetworkTableNames.P_MASTER_CAN_ID,
        preferences.getInt(NetworkTableNames.P_MASTER_CAN_ID, 0));
    SmartDashboard.putNumber(NetworkTableNames.P_FOLLOWER_CAN_ID,
        preferences.getInt(NetworkTableNames.P_FOLLOWER_CAN_ID, 1));
    SmartDashboard.putBoolean(NetworkTableNames.P_MASTER_INVERTED,
        preferences.getBoolean(NetworkTableNames.P_MASTER_INVERTED, false));
    SmartDashboard.putBoolean(NetworkTableNames.P_FOLLOWER_INVERTED,
        preferences.getBoolean(NetworkTableNames.P_FOLLOWER_INVERTED, false));
  }

  public void flushAndBurnPreferences() {
    if (SmartDashboard.getNumber(NetworkTableNames.P_MASTER_CAN_ID, -1) != this.shooterMaster.getDeviceID()
        && SmartDashboard.getNumber(NetworkTableNames.P_MASTER_CAN_ID, -1) != -1) {
      int masterNewCANID = (int) SmartDashboard.getNumber(NetworkTableNames.P_MASTER_CAN_ID, -1);
      int followNewCANID = (int) SmartDashboard.getNumber(NetworkTableNames.P_FOLLOWER_CAN_ID, -1);

      preferences.putInt(NetworkTableNames.P_MASTER_CAN_ID, masterNewCANID);
      preferences.putInt(NetworkTableNames.P_FOLLOWER_CAN_ID, followNewCANID);
      this.shooterMaster.set(ControlMode.PercentOutput, 0);
      this.shooterFollower.set(ControlMode.PercentOutput, 0);
      for (int i = 0; i < 1000; i++) {
        System.out.println("UPDATING CAN IDS TO " + masterNewCANID + " | " + followNewCANID);
      }

      try {
        this.shooterMaster = null;
        this.shooterFollower = null;

        this.shooterMaster = new TalonSRX(masterNewCANID);
        this.shooterFollower = new TalonSRX(followNewCANID);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.exit(254);
      }
    }
    boolean masterNewInvert = SmartDashboard.getBoolean(NetworkTableNames.P_MASTER_INVERTED, false);
    boolean followerNewInvert = SmartDashboard.getBoolean(NetworkTableNames.P_FOLLOWER_INVERTED, false);

    preferences.putBoolean(NetworkTableNames.P_MASTER_INVERTED, masterNewInvert);
    preferences.putBoolean(NetworkTableNames.P_FOLLOWER_INVERTED, followerNewInvert);

    this.shooterMaster.setInverted(masterNewInvert);
    this.shooterFollower.setInverted(followerNewInvert);
    System.out.println(followerNewInvert);


    updateSmartDashboardFromPreferences();

    SmartDashboard.putBoolean(NetworkTableNames.SD_Burn, false);

  }

  public void flushSmartDashboard() {
    System.out.println("FLUSHING");
    double speed = SmartDashboard.getNumber(NetworkTableNames.SD_PERCENT_OUTPUT, 0);
    System.out.println(speed);
    shooterMaster.set(ControlMode.PercentOutput, speed);
    SmartDashboard.putBoolean(NetworkTableNames.SD_Flush, false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if (SmartDashboard.getBoolean(NetworkTableNames.SD_Flush, false)) {
      this.flushSmartDashboard();
    }
/*
    if (SmartDashboard.getBoolean(NetworkTableNames.SD_Burn, false)){
      this.flushAndBurnPreferences();
    }*/


    syncVelocityWithSmartDashboard();

  }
}
