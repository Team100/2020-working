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
    public static final int MOTOR_A_PORT = 0;
    public static final int MOTOR_B_PORT = 1;

    public static final int DIG_IN_PORT_A1 = 0;
    public static final int DIG_IN_PORT_A2 = 1;
    public static final int DIG_IN_PORT_B1 = 2;
    public static final int DIG_IN_PORT_B2 = 3;

    public static final int ANALOG_IN_PORTA = 0;
    public static final int ANALOG_IN_PORTB = 1;

    public static final double TIMEOUT = 5; // seconds
    public static final double HI_THRESHOLD = 4.5; //volts
    public static final double LO_THRESHOLD = 0.5; //volts


}
