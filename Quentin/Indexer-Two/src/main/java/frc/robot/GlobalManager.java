/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Manages state for the entire robot
 */
public class GlobalManager {
    /**
     * Manages states that pertain to the Supersystem
     */
    public static class SupersystemManager {
        /**
         * Is the Indexer full
         */
        public static boolean indexerFull = false;

        /**
         * States that the supersystem can exist in
         */
        public enum SupersystemState {
            NEUTRAL, INTAKING, QUEUEING, ALIGNING, SHOOTING, JAMMED, REVERSING
        }

        /**
         * The current state of the supersystem
         */
        public static SupersystemState supersystemState;

        /**
         * Is the shooter ready to shoot
         */
        public static boolean shooterReady = false;

        /**
         * Is the turret locked and ready to shoot
         */
        public static boolean turretReady = false;

        /**
         * States for objects that extend outside of the frame perimeter INSIDE: Safely
         * inside of the frame perimeter EXTENDED: Unsafe and outside of the frame
         * perimeter
         */
        public enum FramePerimeterState {
            INSIDE, EXTENDED
        }

        /**
         * Is the intake safely inside of the frame perimeter
         */
        public FramePerimeterState intakeFramePerimeterState;

        /**
         * Is the color wheel manipulator safely inside of the frame perimeter
         */
        public FramePerimeterState colorFramePerimeterState;
    }
}
