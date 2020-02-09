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
        public static final int JOYSTICK_PORT = 0;
        public static final int BUTTON_ID = 1;
        public static final int BUTTON_PLAY = 2;
        public static final int BUTTON_DOWN = 5;
        public static final int BUTTON_UP = 6;
    }

    public final class Symphony {
        public static final int FALCON_1_CANID = 0;
        public static final int FALCON_2_CANID = 1;
        public static final int FALCON_3_CANID = 15;
        public static final int FALCON_4_CANID = 14;
    }
}
