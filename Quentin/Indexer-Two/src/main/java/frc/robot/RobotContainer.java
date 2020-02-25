/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.FRCLib.AutoHelperFunctions.PathGenerator;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.supersystem.indexer.IndexerDriveBackward;
import frc.robot.commands.supersystem.indexer.IndexerDriveForward;
import frc.robot.commands.supersystem.indexer.Load;
import frc.robot.commands.supersystem.indexer.ShootOne;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneDriveForward;
import frc.robot.commands.supersystem.indexer.indexStageOne.IndexerStageOneStop;
import frc.robot.commands.supersystem.indexer.indexStageTwo.IndexerStageTwoStop;
import frc.robot.commands.supersystem.intake.IntakeForward;
import frc.robot.commands.supersystem.intake.IntakeReverse;
import frc.robot.commands.supersystem.intake.IntakeStop;
import frc.robot.commands.supersystem.intake.intakePivot.IntakeMoveJoystick;
import frc.robot.commands.supersystem.shooter.ShooterRun;
import frc.robot.commands.supersystem.shooter.ShooterStop;
import frc.robot.commands.supersystem.turret.TurretStop;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IndexerStageOne;
import frc.robot.subsystems.IndexerStageTwo;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakePivot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    public final Drivetrain drivetrain;
    public final Indexer indexer;
    public final IndexerStageOne stageOne;
    public final IndexerStageTwo stageTwo;
    public final Intake intake;
    public final IntakePivot intakePivot;
    public final Shooter shooter;
    public final Turret turret;
    public final Joystick leftJoystick;
    public final Joystick rightJoystick;
    public final Joystick gamepad;
    public final Joystick gamepad2;

    public JoystickButton wholeIndexerForward;
    public JoystickButton wholeIndexerReverse;
    public JoystickButton indexerStageOneForward;
    public JoystickButton indexerStageTwoForward;
    public JoystickButton stateTester;  // runts on button 4

    public JoystickButton shootOne;     // runs on button 8 (kicking ou shooter stop)

    public JoystickButton shooterShoot; // runs on button 7
    public JoystickButton shooterStop;  // runs on button 8

    public JoystickButton intakeForward; // runs on button 5
    public JoystickButton intakeReverse; // runs on button 6

    public JoystickButton intakePivotUp; // HOLD runs on button 7
    public JoystickButton intakePivotDown; // HOLD runs on button 8

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // OI Initiation
        leftJoystick = new Joystick(0);
        rightJoystick = new Joystick(1);
        gamepad = new Joystick(2);
        // add addition gamepage
        gamepad2 = new Joystick(3);

        // Subsystem Initiation
        drivetrain = new Drivetrain();
        indexer = new Indexer();
        stageOne = new IndexerStageOne();
        stageTwo = new IndexerStageTwo();
        intake = new Intake();
        intakePivot = new IntakePivot();
        shooter = new Shooter();
        turret = new Turret();

        // Default Commands
        this.setDefaultCommands();


        // Button to Command Mapping
        configureButtonBindings();
    }

    public void setDefaultCommands() {
        drivetrain.setDefaultCommand(new ArcadeDrive(drivetrain, leftJoystick, rightJoystick));
        stageOne.setDefaultCommand(new IndexerStageOneStop(stageOne));
        stageTwo.setDefaultCommand(new IndexerStageTwoStop(stageTwo));
        intake.setDefaultCommand(new IntakeStop(intake));
        intakePivot.setDefaultCommand(new IntakeMoveJoystick(intakePivot, gamepad));
        shooter.setDefaultCommand(new ShooterStop(shooter));
        turret.setDefaultCommand(new TurretStop(turret));
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        wholeIndexerForward = new JoystickButton(gamepad, 1);
        wholeIndexerReverse = new JoystickButton(gamepad, 3);
        indexerStageOneForward = new JoystickButton(gamepad, 2);
        // Button used to test indexer state machine
        stateTester = new JoystickButton(gamepad, 4);

        wholeIndexerForward.whileHeld(new IndexerDriveForward(stageOne, stageTwo));
        wholeIndexerReverse.whileHeld(new IndexerDriveBackward(stageOne, stageTwo, indexer));
        indexerStageOneForward.whileHeld(new IndexerStageOneDriveForward(stageOne));
        // Button used to test indexer state machine
        stateTester.whenPressed(new Load(stageOne, stageTwo, indexer));

        ////////////////////////////////////////////////////////////////////////////
        intakeForward = new JoystickButton(gamepad, 5);
        intakeForward.whileHeld(new IntakeForward(intake));
        intakeReverse = new JoystickButton(gamepad, 6);
        intakeReverse.whileHeld(new IntakeReverse(intake));
        
        ////////////////////////////////////////////////////////////////////////////
        //shooterShoot = new JoystickButton(gamepad, 6);
        //shooterShoot.whileHeld(new ShooterRun(shooter));

        shooterShoot = new JoystickButton(gamepad2, 1);
        shooterShoot.whenPressed(new ShooterRun(shooter));
        shooterStop = new JoystickButton(gamepad2, 2);
        shooterStop.whenPressed(new ShooterStop(shooter));

        // indexer shoot
        shootOne = new JoystickButton(gamepad, 8);
        shootOne.whenPressed(new ShootOne(stageOne, stageTwo, indexer));

        ////////////////////////////////////////////////////////////////////////////
        //intakePivotUp = new JoystickButton(gamepad, 7);
        //intakePivotUp.whileHeld(new IntakePivotUp(intakePivot));
        //intakePivotDown = new JoystickButton(gamepad, 8);
        //intakePivotDown.whileHeld(new IntakePivotDown(intakePivot));

    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous

        Pose2d start = new Pose2d(0, 0, new Rotation2d(0));
        List<Translation2d> waypoints = List.of();
        Pose2d end = new Pose2d(2, 0, new Rotation2d(0));
        return PathGenerator.createAutoNavigationCommand(drivetrain, start, waypoints, end);
    }
}
