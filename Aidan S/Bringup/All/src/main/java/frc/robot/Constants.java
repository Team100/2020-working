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
        public static final int LEFT_JOYSTICK_PORT = 0;
        public static final int RIGHT_JOYSTICK_PORT = 1;
        
        public static final int TURRET_FORWARD = 6;
        public static final int TURRET_REVERSE = 5;
        public static final int TURRET_ZERO = 6;

        public static final int SHOOTER_INCREASE = 8;
        public static final int SHOOTER_DECREASE = 10;
        public static final int SHOOTER_STOP = 12;

        public static final int PIVOT_UP = 7;
        public static final int PIVOT_DOWN = 9;
        public static final int PIVOT_ZERO = 11;
        public static final int SPIN = 1;
        
        public static final int STAGE_ONE_FORWARD = 4;
        public static final int STAGE_ONE_REVERSE = 3;
        public static final int STAGE_TWO_FORWARD = 4;
        public static final int STAGE_TWO_REVERSE = 3;

        public static final int SPINNER_FORWARD = 9;
        public static final int SPINNER_REVERSE = 11;
    }

    public final class Turret {
        public static final int MOTOR_CAN_ID = 4;
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    public final class Shooter {
        public static final int MASTER_CAN_ID = 2;
        public static final int FOLLOWER_CAN_ID = 13;
    }

    public final class Intake {
        public static final int PIVOT_MOTOR_CAN_ID = 10;
        public static final int SPIN_MOTOR_CAN_ID = 9;
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    public final class StageOne {
        public static final int MOTOR_CAN_ID = 8;
    }

    public final class StageTwo {
        public static final int MOTOR_CAN_ID = 7;
    }

    public final class Drivetrain {
        public static final int LEFT_MASTER_CAN_ID = 15;
        public static final int LEFT_FOLLOWER_CAN_ID = 14;
        public static final int RIGHT_MASTER_CAN_ID = 0;
        public static final int RIGHT_FOLLOWER_CAN_ID = 1;
    }

    public final class Spinner {
        public static final int MOTOR_CAN_ID = 11;
    }
}
