/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Indexer extends SubsystemBase {
  public static enum ActionState {
    MOVE_FOWARD, MOVE_BACKWARDS, STOP
  }

  public enum IndexerStates {
    EMPTY, ONE_BALL, TWO_BALL, LOW_THREE_BALL, HIGH_THREE_BALL, FOUR_BALL, FIVE_BALL, READY_SHOOT, SHOOTING, JAMMED
  }

  public IndexerStates indexerState = IndexerStates.EMPTY;

  /**
   * Sensors
   */
  public DigitalInput frontSensor = new DigitalInput(Constants.IndexerConstants.IndexerSensors.FrontSensor.ID);
  public DigitalInput rearSensor = new DigitalInput(Constants.IndexerConstants.IndexerSensors.RearSensor.ID);

  /**
   * Keeps track of whether the last iteration was positive or not
   */
  public boolean lastIterateFront, lastIterateRear;

  /**
   * Keeps track of how many objects have passed the sensor
   */
  public int frontCount, rearCount;

  /**
   * Lets Indexer know when to switch states
   */
  public boolean frontSwitchState, rearSwitchState;

  /**
   * Creates a new Indexer.
   */
  public Indexer() {
    frontCount = 0;
    rearCount = 0;

    lastIterateFront = false;
    lastIterateRear = false;

    frontSwitchState = false;
    rearSwitchState = false;

    indexerState = IndexerStates.EMPTY;

  }

  public void processBallDetectionSensors() {
    if (!frontSensor.get() && lastIterateFront) {
      lastIterateFront = false;

    } else if (frontSensor.get() && !lastIterateFront) {
      lastIterateFront = true;
      frontSwitchState = true;
      frontCount += 1;
      System.out.println("Front Count: " + frontCount);
    }
    // if(!frontSensor.get() && !lastIterateFront){
    //   frontSwitchState = true;
    // }

    if (!rearSensor.get() && lastIterateRear) {
      lastIterateRear = false;
    } else if (rearSensor.get() && !lastIterateRear) {
      lastIterateRear = true;
      rearSwitchState = true;
      rearCount += 1;
      System.out.println("Rear Count: " + rearCount);

    }


  }

  /**
   * Tells what state robot is currently in
   */
  public void getState() {
    System.out.println();
  }

  /**
   * Update any states
   */
  public void updateState() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    processBallDetectionSensors();
    updateState();

    //*****NOTE: frontSensor.get() returns true if it detects no object

    // Puts all values we want to the dashboard
    SmartDashboard.putBoolean("frontSensor", frontSensor.get());
    SmartDashboard.putBoolean("backSensor", rearSensor.get());
    SmartDashboard.putString("currentindexerState", indexerState.toString());
    SmartDashboard.putNumber("Front Count", frontCount - 1);
    SmartDashboard.putNumber("Rear Count", rearCount -1);

  }
}
