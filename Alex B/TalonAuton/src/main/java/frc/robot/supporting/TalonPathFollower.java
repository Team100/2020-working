/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.supporting;

import jaci.pathfinder.Trajectory;

public class TalonPathFollower {
    //This is based on http://acb.link/Fc4u

    /**
     * The last error returned from the PID
     */
    double lastError;

    /**
     * The heading that can be used as an extra factor in the control loop
     */
    double heading;

    /**
     * The index of the current segment
     */
    int segment;

    /**
     * The active trajectory
     */
    Trajectory trajectory;

    /**
     * The data to be sent to the Talon
     */
    TalonData td;

    /**
     * Data to be sent to the talon
     */
    public class TalonData{
        /**
         * The setpoint for position control mode
         */
        public double positionSetpoint;
        /**
         * The feedforward arbitary value
         */
        public double auxFeedForward;
    }

    /**
     * Create a generator with a trajectory
     * @param traj the current trajectory
     */
    public TalonPathFollower(Trajectory traj){
        this.trajectory = traj;
        td = new TalonData();
    }

    /**
     * Create a generator without a trajectory
     */
    public TalonPathFollower(){
        td = new TalonData();
    }
    
    /**
     * Set the active trajectory
     * @param traj the trajectory to be set
     */
    public void setTrajectory(Trajectory traj){
        this.trajectory = traj;
    }

    /**
     * Reset the values on the path generator
     */
    public void reset(){
        lastError = 0;
        segment = 0;
    }
    /**
     * Calculate the current setpoint
     * @return The position for the setpoint
     */
    public double calculate(){
        if(segment < trajectory.length()){
            return trajectory.get(segment++).position;
        } else{
            return 0;
        }
    }

    /**
     * Get the heading of the robot
     * @return the current heading
     */
    public double getHeading(){
        return heading;
    }

    /**
     * The error from the PID Loop
     * @return the error
     */
    public double getError(){
        return lastError;
    }

    
    /**
     * Get the current Segment
     * @return the current trajectory
     */
    public Trajectory.Segment getSegment(){
        Trajectory.Segment seg = new Trajectory.Segment(0,0,0,0,0,0,0,0);
        try{
            seg = trajectory.get(segment);
        }catch (ArrayIndexOutOfBoundsException e){
            // Do nothing
        }
        return seg;

    }

    /**
     * Determine if the path is finished
     * @return TRUE if it is finished and FALSE if it still needs to continue
     */
    public boolean isFinished(){
        return segment >= trajectory.length();
    }

}
