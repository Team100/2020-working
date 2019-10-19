/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CustomClasses;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

/**
 * Add your docs here.
 */
public class StoredTrajectory {

    public Trajectory trajectory;
    public String name;

    public StoredTrajectory(String name, Trajectory trajectory){
        this.name = name;
        this.trajectory = trajectory;
    }

    public Segment get(int i){
        return this.trajectory.get(i);
        
    }
    
}
