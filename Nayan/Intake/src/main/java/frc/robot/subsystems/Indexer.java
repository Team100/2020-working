
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private static WPI_VictorSPX leftSpx;
  private static WPI_VictorSPX rightSpx;
  static DigitalInput frontSensor = new DigitalInput(0);
  static DigitalInput outSensor = new DigitalInput(1);
  public static double outputLimit = 0.5;

  PowerDistributionPanel PDP = new PowerDistributionPanel(0);

  public Indexer() {
    leftSpx = new WPI_VictorSPX(Constants.LEFT_SPX_CANID);

    rightSpx = new WPI_VictorSPX(Constants.RIGHT_SPX_CANID);

    SmartDashboard.putNumber("PercentOutLeft", 0.3);
    SmartDashboard.putNumber("PercentOutRight", 0.3);

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
    
    SmartDashboard.putBoolean("frontSensor", frontSensor.get());
    SmartDashboard.putBoolean("outSensor", outSensor.get());

    // SmartDashboard.putNumber("Current Draw Can 2", PDP.getCurrent(0));
    // SmartDashboard.putNumber("Current Draw Can 14", PDP.getCurrent(1));
  }
}