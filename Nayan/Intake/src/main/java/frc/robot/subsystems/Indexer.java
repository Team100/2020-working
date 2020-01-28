
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private WPI_VictorSPX leftSpx;
  private WPI_VictorSPX rightSpx;

  public Indexer() {
    leftSpx = new WPI_VictorSPX(Constants.LEFT_SPX_CANID);
    leftSpx.configPeakOutputForward(0.5);
    leftSpx.configPeakOutputReverse(-0.5);

    rightSpx = new WPI_VictorSPX(Constants.RIGHT_SPX_CANID);
    rightSpx.configPeakOutputForward(0.5);
    rightSpx.configPeakOutputReverse(-0.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}