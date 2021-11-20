/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Breech;
import frc.robot.subsystems.PressureBox;

public class Reload extends SequentialCommandGroup {
  PressureBox pressureBox;
  Breech breech;
  boolean done = false;


  public Reload(PressureBox pb, Breech b) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    new BreechOpen(b);
    new WaitCommand(1);
    new BreechEject(b);
  }
}
