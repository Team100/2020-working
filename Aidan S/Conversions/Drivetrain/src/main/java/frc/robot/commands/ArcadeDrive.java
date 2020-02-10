/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain drivetrain;
  private final Joystick joystick;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
public ArcadeDrive(Drivetrain d, Joystick j) {
    drivetrain = d;
    joystick = j;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double left = -joystick.getRawAxis(1) + joystick.getRawAxis(2);
    double right = -joystick.getRawAxis(1) - joystick.getRawAxis(2);
    drivetrain.set(cap(left), cap(right));
    // drivetrain.set(left + skim(right), right + skim(left));
  }

  private double cap(double val) {
    if (val > 1.0) return val - 1.0;
    else if (val < -1.0) return val + 1.0;
    else return val;
  }

  // private double skim(double val) {
  //   if (val > 1.0) return 1.0-val;
  //   else if (val < -1.0) return -1-val;
  //   else return 0;
  // }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
