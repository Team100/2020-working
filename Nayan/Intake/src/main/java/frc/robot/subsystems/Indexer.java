
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private static WPI_VictorSPX leftSpx;
  private static WPI_VictorSPX rightSpx;
  

  public Indexer() {
    leftSpx = new WPI_VictorSPX(Constants.LEFT_SPX_CANID);
    leftSpx.configPeakOutputForward(0.5);
    leftSpx.configPeakOutputReverse(-0.5);

    // rightSpx = new WPI_VictorSPX(Constants.RIGHT_SPX_CANID);
    rightSpx = new WPI_VictorSPX(0);
    rightSpx.configPeakOutputForward(0.5);
    rightSpx.configPeakOutputReverse(-0.5);
  }

  public static void foward() {
    leftSpx.set(ControlMode.PercentOutput, 50);
    rightSpx.set(ControlMode.PercentOutput, 50);
    SmartDashboard.putString("fowardCall", "yes");
  }

  @Override
  public void periodic() {
    SmartDashboard.putString("periodic", "yes");
    

  }
}