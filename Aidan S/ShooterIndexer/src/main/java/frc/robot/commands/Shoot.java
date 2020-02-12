/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  private Shooter shooter;
  private Joystick joystick;
  private JoystickButton up, down, off, toggle;
  private boolean useButtons = false;
  private double sp = 0;

  /**
   * Creates a new Shoot.
   */
  public Shoot(Shooter s, Joystick j) {
    shooter = s;
    joystick = j;

    down = new JoystickButton(joystick, Constants.OI.SHOOTER_DOWN);
    up = new JoystickButton(joystick, Constants.OI.SHOOTER_UP);
    off = new JoystickButton(joystick, Constants.OI.SHOOTER_OFF);
    toggle = new JoystickButton(joystick, Constants.OI.SHOOTER_TOGGLE);

    toggle.whenPressed(new InstantCommand(() -> {
      useButtons = !useButtons;
      sp = Math.floor(sp * 10) / 10;
    }));
    up.whenPressed(new InstantCommand(() -> { if (useButtons && sp < 0.9) { sp += 0.1; } }));
    down.whenPressed(new InstantCommand(() -> { if (useButtons && sp > 0.1) { sp -= 0.1; } }));
    off.whenPressed(new InstantCommand(() -> sp = 0));

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!useButtons) sp = -joystick.getRawAxis(3)*Constants.Shooter.PEAK_OUTPUT;
    shooter.set(sp);
  }

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
