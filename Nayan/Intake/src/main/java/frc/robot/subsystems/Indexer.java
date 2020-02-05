
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
  static DigitalInput frontSensor = new DigitalInput(1);
  static DigitalInput outSensor = new DigitalInput(0);

  PowerDistributionPanel PDP = new PowerDistributionPanel(0);

  public Indexer() {
    leftSpx = new WPI_VictorSPX(Constants.LEFT_SPX_CANID);
    leftSpx.configPeakOutputForward(0.5);
    leftSpx.configPeakOutputReverse(-0.5);

    rightSpx = new WPI_VictorSPX(Constants.RIGHT_SPX_CANID);
    rightSpx.configPeakOutputForward(0.5);
    rightSpx.configPeakOutputReverse(-0.5);

    SmartDashboard.putNumber("PercentOutLeft", 0.3);
    SmartDashboard.putNumber("PercentOutRight", 0.3);
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


  public static void TillStop() {


    while(outSensor.get()) {
      leftSpx.set(ControlMode.PercentOutput, 0.3);
      rightSpx.set(ControlMode.PercentOutput, 0.3);
    }
    stop();
  }

  public static void MoveUp() {

    leftSpx.set(ControlMode.PercentOutput, 0.3);
    rightSpx.set(ControlMode.PercentOutput, 0.3);
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

    SmartDashboard.putBoolean("frontSensor", frontSensor.get());
    SmartDashboard.putBoolean("outSensor", outSensor.get());

    SmartDashboard.putNumber("Current Draw Can 2", PDP.getCurrent(2));
    SmartDashboard.putNumber("Current Draw Can 14", PDP.getCurrent(14));
  }
}