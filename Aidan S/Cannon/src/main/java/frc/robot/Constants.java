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
    public static final class Breech {
        public static final int BARREL_SOLENOID_CAN_ID = 0;
        public static final int EJECT_SOLENOID_CAN_ID = 1;
        public static final int CARTRIDGE_LIMIT_SWITCH_DIO_ID = 1;
    }

    public static final class PressureBox {
        public static final int PISTON_SOLENOID_CAN_ID = 2;
        public static final int FILL_VALVE_SOLENOID_CAN_ID = 3;
        public static final int TRANSDUCER_ANALOG_ID = 2;
        public static final double TRANSDUCER_SCALE = 250;
        public static final double TRANSDUCER_OFFSET = -25;
        public static final int TARGET_PRESSURE = 100; // PSI
        public static final int FIRING_PRESSURE = 95; // PSI
    }

    public static final class Safety {
        public static final int ULTRASONIC_DIO_ID_1 = 3;
        public static final int ULTRASONIC_DIO_ID_2 = 4;
        public static final int ULTRASONIC_RANGE = 24; // Inches
    }

    public static final class Aiming {
        public static final int TOP_LIMIT_DIO_ID = 5;
        public static final int BOTTOM_LIMIT_DIO_ID = 6;
        public static final int MOTOR_CAN_ID = 4;
    }
}
