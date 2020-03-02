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
import frc.robot.FRCLib.Motors.FRCTalonSRX;

public class Indexer extends SubsystemBase {

  public FRCTalonSRX indexerStageOne;
  public FRCTalonSRX indexerStageTwo;

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

  public static enum SupersystemStates{
    PAUSED,
    NOT_AT_END,
    AT_END,
    MOVING_BOTH,
    FIRST_OPEN,
    REACHED_END,
    INTAKING,
    INTAKED,
    COMPLETE,
    ERROR
    
  }
  public SupersystemStates supersystemStates;

  public static enum ActionState{
    MOVE_FOWARD,
    MOVE_BACKWARDS,
    STOP
  }
  public ActionState actionState;

  /**
   * Creates a new Indexer.
   */
  public Indexer() {


    // Construct Motor Objects
    indexerStageOne = new FRCTalonSRX.FRCTalonSRXBuilder()
    .withCanID(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.CAN_ID)
    .withInverted(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.INVERT)
    .withFeedbackPort(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.FEEDBACK_PORT)
    .withSensorPhase(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.SENSOR_PHASE)
    .withTimeout(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.TIMEOUT)
    .withCurrentLimitEnabled(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.PEAK_OUTPUT_REVERSE)
    .build();

    indexerStageTwo = new FRCTalonSRX.FRCTalonSRXBuilder()
    .withCanID(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.CAN_ID)
    .withInverted(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.INVERT)
    .withFeedbackPort(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.FEEDBACK_PORT)
    .withSensorPhase(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.SENSOR_PHASE)
    .withTimeout(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.TIMEOUT)
    .withCurrentLimitEnabled(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.OPEN_LOOP_RAMP)
    .withNominalOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.NOMINAL_OUTPUT_FORWARD)
    .withNominalOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.NOMINAL_OUTPUT_REVERSE)
    .withPeakOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.PEAK_OUTPUT_REVERSE)
    .build();


  }

  public void processBallDetectionSensors(){
    if(!frontSensor.get() && lastIterateFront){
      lastIterateFront = false;
    } else if(frontSensor.get() && !lastIterateFront){
      lastIterateFront = true;
      frontCount += 1;
      System.out.println("Front Count: "+frontCount);
      
    }


    if(!rearSensor.get() && lastIterateRear){
      lastIterateRear = false;
    } else if(rearSensor.get() && !lastIterateRear){
      lastIterateRear = true;
      rearCount += 1;
      System.out.println("Rear Count: "+rearCount);

    }
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    processBallDetectionSensors();
  }
}
