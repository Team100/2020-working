
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private static WPI_VictorSPX leftSpx;
  private static WPI_VictorSPX rightSpx;
  DigitalInput frontSensor = new DigitalInput(1);
  DigitalInput outSensor = new DigitalInput(0);

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

  public static void LeftFoward() {
    double leftSpeed = SmartDashboard.getNumber("PercentOutLeft", 0.3);
    leftSpx.set(ControlMode.PercentOutput, leftSpeed);
  }

public static void RightFoward() {
  double rightSpeed = SmartDashboard.getNumber("PercentOutLeft", 0.3);
  rightSpx.set(ControlMode.PercentOutput, rightSpeed);
}

  @Override
  public void periodic() {
    leftSpx.set(ControlMode.PercentOutput, 0.00);
    rightSpx.set(ControlMode.PercentOutput, 0.00);

    SmartDashboard.putBoolean("frontSensor", frontSensor.get());
    SmartDashboard.putBoolean("outSensor", outSensor.get());
  }
}