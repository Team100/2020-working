
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private static TalonSRX leftSpx;
  private static TalonSRX rightSpx;

  /**
   * Keeps track of whether the last iteration was positive or not
   */
  public boolean lastIterateFront, lastIterateRear;

  /**
   * Keeps track of how many objects have passed the sensor
   */
  public int frontCount, rearCount;
  static DigitalInput frontSensor = new DigitalInput(3);
  static DigitalInput outSensor = new DigitalInput(0);
  public static double outputLimit = 1;

  PowerDistributionPanel PDP = new PowerDistributionPanel(0);

  public Indexer() {

    lastIterateFront = false;
    lastIterateRear = false;
    frontCount = 0;
    rearCount = 0;
    leftSpx = new TalonSRX(Constants.LEFT_SPX_CANID);

    rightSpx = new TalonSRX(Constants.RIGHT_SPX_CANID);

    leftSpx.enableCurrentLimit(true);
    leftSpx.configPeakCurrentLimit(30);
    leftSpx.configContinuousCurrentLimit(25);
    leftSpx.configPeakCurrentDuration(100);

    rightSpx.enableCurrentLimit(true);
    rightSpx.configPeakCurrentLimit(30);
    rightSpx.configContinuousCurrentLimit(25);
    rightSpx.configPeakCurrentDuration(100);

    leftSpx.configOpenloopRamp(0.1);
    rightSpx.configOpenloopRamp(0.1);

    

  
  

    SmartDashboard.putNumber("PercentOutLeft", .7);
    SmartDashboard.putNumber("PercentOutRight", 0.5*.7);

    SmartDashboard.putNumber("outputLimit", outputLimit);
  }

  public static void moveFoward() {
    double leftSpeed = SmartDashboard.getNumber("PercentOutLeft", 0.3);
    double rightSpeed = SmartDashboard.getNumber("PercentOutRight", 0.3);
    rightSpx.set(ControlMode.PercentOutput, (rightSpeed));
    leftSpx.set(ControlMode.PercentOutput, (leftSpeed));
  }

  public static void moveStageOne() {
    double rightSpeed = SmartDashboard.getNumber("PercentOutRight", 0.3);
    rightSpx.set(ControlMode.PercentOutput, (rightSpeed));
  }

  public static void unJam() {
    double leftSpeed = SmartDashboard.getNumber("PercentOutLeft", 0.3);
    double rightSpeed = SmartDashboard.getNumber("PercentOutRight", 0.3);
    rightSpx.set(ControlMode.PercentOutput, -(rightSpeed));
    leftSpx.set(ControlMode.PercentOutput, -(leftSpeed));
  }

  public static void peakOutput() {
    double outputLimit = SmartDashboard.getNumber("outputLimit", 0.5);

    leftSpx.configPeakOutputForward(outputLimit);
    leftSpx.configPeakOutputReverse(-outputLimit);

    rightSpx.configPeakOutputForward(outputLimit);
    rightSpx.configPeakOutputReverse(-outputLimit);
  }


  public static void TillStop() {


    while(outSensor.get()) {
      double leftSpeed = SmartDashboard.getNumber("PercentOutLeft", 0.3);
      double rightSpeed = SmartDashboard.getNumber("PercentOutRight", 0.3);

      leftSpx.set(ControlMode.PercentOutput, leftSpeed);
      rightSpx.set(ControlMode.PercentOutput, rightSpeed);
    }
    stop();
  }

  public static void MoveUp() {
    double leftSpeed = SmartDashboard.getNumber("PercentOutLeft", 0.3);
    double rightSpeed = SmartDashboard.getNumber("PercentOutRight", 0.3);

    leftSpx.set(ControlMode.PercentOutput, leftSpeed);
    rightSpx.set(ControlMode.PercentOutput, rightSpeed);
    try {
      Thread.sleep(400);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    stop();
  }

  public static void stop(){
    leftSpx.set(ControlMode.PercentOutput, 0.00);
    rightSpx.set(ControlMode.PercentOutput, 0.00);
  }

  @Override
  public void periodic() {
    stop();
    peakOutput();

    if(!frontSensor.get() && lastIterateFront){
      lastIterateFront = false;
    } else if(frontSensor.get() && !lastIterateFront){
      lastIterateFront = true;
      frontCount += 1;
      System.out.println("Front Count: "+frontCount);
      
    }


    if(!outSensor.get() && lastIterateRear){
      lastIterateRear = false;
    } else if(outSensor.get() && !lastIterateRear){
      lastIterateRear = true;
      rearCount += 1;
      System.out.println("Rear Count: "+rearCount);

    }
    
    SmartDashboard.putBoolean("frontSensor", frontSensor.get());
    SmartDashboard.putBoolean("outSensor", outSensor.get());

   
    SmartDashboard.putNumber("Current Draw Can 0", PDP.getCurrent(Constants.LEFT_SPX_CANID));
    SmartDashboard.putNumber("Current Draw Can 1", PDP.getCurrent(Constants.RIGHT_SPX_CANID));
  }
}