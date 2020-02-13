/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class OI {
        public static final int INDEX_FORWARD = 8;
        public static final int INDEX_UNJAM = 7;
        public static final int INDEX_END = 2;
        public static final int INDEX_UP = 4;
        public static final int INDEX_STAGE1 = 1;

        public static final int SHOOTER_UP = 6;
        public static final int SHOOTER_DOWN = 5;
        public static final int SHOOTER_OFF = 3;
        public static final int SHOOTER_TOGGLE = 10;
    }
 
    public final class Shooter {
        public static final double PEAK_OUTPUT = 1;
        public static final int FALCON_1_CANID = 15;
        public static final int FALCON_2_CANID = 14;
        public static final double ENCODER_CPR = 2048;
        public static final double RAMP_RATE = 0; //seconds to full velocity
        public static final double WHEEL_RADIUS = 0.098/2; // Feet per second
    }

    public final class Indexer {
        public static final int LEFT_SPX_CANID = 1; //Stage 2
        public static final int RIGHT_SPX_CANID = 3; //Stage 1
    }
}
