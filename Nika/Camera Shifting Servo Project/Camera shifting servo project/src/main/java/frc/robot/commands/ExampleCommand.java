/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Cameratiltsubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * An example command that uses an example subsystem.
 */
public class ExampleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Cameratiltsubsystem m_subsystem;
  private final Joystick joystick;
  private final JoystickButton top;
  private final JoystickButton middle;
  private final JoystickButton up;
  private final JoystickButton down;
  private int servosetpoint;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(Cameratiltsubsystem subsystem) {
    m_subsystem = subsystem;
    joystick = new Joystick(0);
    top = new JoystickButton(joystick, 4);
    middle = new JoystickButton(joystick, 1);
    up = new JoystickButton(joystick, 6);
    down = new JoystickButton(joystick, 5);

    top.whenPressed(new InstantCommand(() -> {servosetpoint = Constants.Camera.SETPOINT_TOP;}));
    middle.whenPressed(new InstantCommand(() -> {servosetpoint = Constants.Camera.SETPOINT_MIDDLE;}));
    up.whenPressed(new InstantCommand(() -> {servosetpoint += Constants.Camera.INCREMENT;}));
    down.whenPressed(new InstantCommand(() -> {servosetpoint -= Constants.Camera.INCREMENT;}));

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.setsetpoint(servosetpoint);
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
