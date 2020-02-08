/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.music.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import java.util.ArrayList;

public class Symphony extends SubsystemBase {
  Orchestra orchestra;
      TalonFX [] FX =  {  new TalonFX(Constants.Symphony.FALCON_1_CANID), 
                          new TalonFX(Constants.Symphony.FALCON_2_CANID), 
                          new TalonFX(Constants.Symphony.FALCON_3_CANID), 
                          new TalonFX(Constants.Symphony.FALCON_4_CANID) };


  public Symphony() {
    ArrayList<TalonFX> instruments = new ArrayList<TalonFX>();

    for (int i = 0; i < FX.length; ++i) {
      instruments.add(FX[i]);
    }
    orchestra = new Orchestra(instruments);
    orchestra.loadMusic("mega.chrp"); 
  }

  public void play(){
    orchestra.play();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
