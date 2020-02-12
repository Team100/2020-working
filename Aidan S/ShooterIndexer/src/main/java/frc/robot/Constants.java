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
    public final class Turret {
        public static final double           TICKS_PER_DEG = 21; //3.27;
        public static final int                TICK_OFFSET = 0;
        public static final int                     CAN_ID = 3;
        public static final double                      kP = 2.5; //3.6;
        public static final double                      kI = 0; //0.00038;
        public static final double                      kD = 5; //4;
        public static final double                      kF = 0;
        public static final double             PEAK_OUTPUT = 1;
        public static final double    RAMP_SECONDS_TO_FULL = 0;
        public static final double NOMINAL_VOLTAGE_FORWARD = 1;
        public static final double NOMINAL_VOLTAGE_REVERSE = -1;
        public static final int            ALLOWABLE_ERROR = 10;
        public static final int                LOWER_LIMIT = -2500;
        public static final int                UPPER_LIMIT = 2500;
    }

    public final class Shooter {
        public static final double PEAK_OUTPUT = 1;
        public static final int FALCON_1_CANID = 0;
        public static final int FALCON_2_CANID = 1;
        public static final double ENCODER_CPR = 2048;
        public static final double RAMP_RATE = 0; //seconds to full velocity
        public static final double WHEEL_RADIUS = 0.098/2; // Feet per second
    }

    public final class Indexer {
        public static final int LEFT_SPX_CANID = 13; //Type: CAN ID
        public static final int RIGHT_SPX_CANID = 1; //Type: CAN ID
    }
}
