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
    public final class Robot {
        public static final int JOYSTICK_PORT = 0;
    }

    public final class Drivetrain {
        public static final int CAN_ID_LEFT = 0;
        public static final int CAN_ID_RIGHT = 15;
        public static final double PEAK_OUTPUT = 0.5;
    }

    public final class ClimberLock {
        public static final int PWM_ID_SERVO = 0;
        public static final int LOCK_POSITION = 270;
        public static final int UNLOCK_POSITION = 0;
    }
}

