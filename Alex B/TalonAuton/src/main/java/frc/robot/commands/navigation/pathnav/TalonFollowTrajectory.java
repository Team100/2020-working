/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.navigation.pathnav;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Constants.LeftFollower;
import frc.robot.supporting.TalonPathFollower;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.modifiers.TankModifier;

public class TalonFollowTrajectory extends Command {
  TalonPathFollower lFollower;
  TalonPathFollower rFollower;
  Supplier<Trajectory> tSupplier;
  Trajectory trajectory;
  TankModifier modifier;
  double angleDifference;
  boolean backwards;
  double startingAngle;
  double leftSetpoint;
  double rightSetpoint;
  double leftFeedforward;
  double rightFeedforward;

  double followerLoopTime;
  double followerdt;

  double lastTime;
  private Notifier notifier;

  public class DebugInfo{
    public double leftPositionError;
    public double leftVelocityError;
    public double leftSetpoint;
    public double leftTargetVelocity;
    public double leftTargetAcceleration;

    public double rightPositionError;
    public double rightVelocityError;
    public double rightSetpoint;
    public double rightTargetVelocity;
    public double rightTargetAcceleration;

    public double dt;
    public double loopTime;
  }
  public DebugInfo debugInfo;

  public TalonFollowTrajectory(Supplier<Trajectory> tSupplier){
    this(tSupplier, false, 0);
  }
  public TalonFollowTrajectory(Trajectory trajectory){
    this(()-> trajectory, false, 0);
  }
  public TalonFollowTrajectory(Trajectory trajectory, boolean backwards){
    this(()->trajectory, backwards, 0);
  }
  public TalonFollowTrajectory(Supplier<Trajectory> tSupplier, double startingAngle){
    this(tSupplier, false, startingAngle);
  }
  public TalonFollowTrajectory(Trajectory trajectory, double startingAngle){
    this(() -> trajectory, false, startingAngle);
  }
  public TalonFollowTrajectory(Trajectory trajectory, boolean backwards, double startingAngle){
    this(()->trajectory, backwards, startingAngle);
  }

  public TalonFollowTrajectory(Supplier<Trajectory> tSupplier, boolean backwards, double startingAngle){
    this.tSupplier = tSupplier;
    this.backwards = backwards;
    this.startingAngle = startingAngle;
    requires(Robot.drivetrain);
    notifier = new Notifier(this::notifierExecute);
  }
  


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.trajectory = tSupplier.get();
    this.modifier = new TankModifier(this.trajectory).modify(Constants.RobotCharacteristics.WHEELBASE_WIDTH);
    modifier.modify(Constants.RobotCharacteristics.WHEELBASE_WIDTH);

    lFollower = new TalonPathFollower(modifier.getLeftTrajectory());
    rFollower = new TalonPathFollower(modifier.getRightTrajectory());

    lFollower.reset();
    rFollower.reset();
    Robot.drivetrain.leftLeader.setSelectedSensorPosition(0);
    Robot.drivetrain.rightLeader.setSelectedSensorPosition(0);

    Robot.drivetrain.gyro.reset();
    leftSetpoint = 0;
    rightSetpoint = 0;
    lastTime = Timer.getFPGATimestamp();
    followerLoopTime = 0;
    followerdt = 0;
    debugInfo = new DebugInfo();
    notifier.startPeriodic(0.01);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  protected void notifierExecute(){
    System.out.println("EXECUTING IN NOTIFIER");
    double time = Timer.getFPGATimestamp();
    followerdt = (time-lastTime);
    lastTime = time;
    double gyroHeading =-1*Robot.drivetrain.getGyroValue();
    //double gyroHeading = 0;
    double desiredHeading =Pathfinder.r2d(lFollower.getHeading() - startingAngle); //d2r
    angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
    SmartDashboard.putNumber("DT Desired Heading", desiredHeading);
    SmartDashboard.putNumber("Angle Difference", angleDifference);
    double turn = Constants.DTConstants.KP*angleDifference;
    leftFeedforward = lFollower.getSegment().velocity * Constants.DTConstants.KV;
    rightFeedforward = rFollower.getSegment().velocity * Constants.DTConstants.KV;
    leftSetpoint = lFollower.calculate();
    rightSetpoint = rFollower.calculate();

    
    if(!this.isFinished()){
      if(!backwards){
        Robot.drivetrain.positionPDauxF(leftSetpoint, Constants.LeftLeader.KF, rightSetpoint, Constants.RightLeader.KF);
      }
      else{
        //TODO Drive PDauxF
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return lFollower.isFinished() && rFollower.isFinished() /*TODO Drivetrain At Target*/;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.halt();
    leftSetpoint = 0;
    rightSetpoint = 0;
    lFollower.reset();
    rFollower.reset();
    Robot.drivetrain.gyro.reset();
    notifier.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
