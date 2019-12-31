/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Constants {
    public class RobotCharacteristics {
        public static final double WHEELBASE_WIDTH = 1.5; // Feet
    }

    public class Auto {
        public static final double DT = 0.05;
        public static final double MAX_VELOCITY =1;
        public static final double MAX_ACCELERATION = .25;
        public static final double MAX_JERK = .25;
    }

    public class DTConstants {
        public static final int TICKS_PER_REV = 3072;//8192
        public static final double WHEEL_DIAMETER = 0.333; // Feet

        public static final double KP = 0.0175; //0.0027
        public static final double KI = 0;
        public static final double KD = 0.001;
        public static final double KF = 0;

        public static final double KV = 1 / Auto.MAX_VELOCITY;
        public static final double KA = Auto.MAX_ACCELERATION;
        public static final double VELOCITY_PER_100_MS_CONVERSION_FACTOR = 0;
    }

    public class LeftLeader {
        public static final int CAN_ID = 0;
        public static final double KP = DTConstants.KP;
        public static final double KI = DTConstants.KI;
        public static final double KD = DTConstants.KD;
        public static final double KF = DTConstants.KF;
        public static final double KV = DTConstants.KV;
        public static final double KA = DTConstants.KA;
        public static final int CLOSED_LOOP_ERROR = 0;

        public static final boolean INVERTED = true;
        public static final int TIMEOUT = 10;
        public static final int STATUS_FRAME = 10;

        public static final int MAX_VELOCITY = 1;

        public class Feedback {
            // Specify the encoder type in `Drivetrain.java`
            public static final int PORT = 0;
            public static final boolean SENSOR_PHASE = false;
        }

        public class Output {
            public static final double MAX_OUTPUT_FORWARD = 1;
            public static final double MAX_OUTPUT_REVERSE = -1;
            public static final double NOMINAL_OUTPUT_FORWARD = 0;
            public static final double NOMINAL_OUTPUT_REVERSE = 0;

        }

        public class Power {
            public static final boolean CURRENT_LIMIT = true;
            public static final int MAX_AMP = 30;
        }

    }

    public class LeftFollower {
        public static final int CAN_ID = 2;
        public static final boolean INVERTED = false;

    }

    public class RightLeader {
        public static final int CAN_ID = 15;
        public static final double KP = DTConstants.KP;
        public static final double KI = DTConstants.KI;
        public static final double KD = DTConstants.KD;
        public static final double KF = DTConstants.KF;
        public static final double KV = DTConstants.KV;
        public static final double KA = DTConstants.KA;
        public static final int TIMEOUT = 10;
        public static final int STATUS_FRAME = 10;

        public static final int MAX_VELOCITY = 1;

        public static final int CLOSED_LOOP_ERROR = 0;
        public static final boolean INVERTED = false;

        public class Feedback {
            public static final int PORT = 0;
            public static final boolean SENSOR_PHASE = false;

        }

        public class Output {
            public static final double MAX_OUTPUT_FORWARD = 1;
            public static final double MAX_OUTPUT_REVERSE = -1;
            public static final double NOMINAL_OUTPUT_FORWARD = 0;
            public static final double NOMINAL_OUTPUT_REVERSE = 0;

        }

        public class Power {
            public static final boolean CURRENT_LIMIT = true;
            public static final int MAX_AMP = 30;
        }

    }

    public class RightFollower {
        public static final int CAN_ID = 3;
        public static final boolean INVERTED = false;

    }
}
