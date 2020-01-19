/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import frc.robot.frclib.AutoHelperFunctions.AutonConversionFactors;

public class Constants {
    public class RobotCharacteristics {
    public static final double WHEELBASE_WIDTH = 0.4572; // Meters
    }

    public class Auto {
        public static final double DT = 0.05;
        public static final double MAX_VELOCITY =7;
        public static final double MAX_ACCELERATION = .25;
        public static final double MAX_JERK = .25;
    }

    public static class DTConstants {
        public static final int TICKS_PER_REV = 8192;// 8192
        public static final double WHEEL_DIAMETER = 0.333; // Feet

        public static final double KP = 0.017; // 0.0027
        public static final double KI = 0;
        public static final double KD = 20;
        public static final double KF = 0.085;
        //public static final double KF = 11.41199782;

        public static final double KS = 0.699;
        public static final double KV = 0.694;
        public static final double KTRACK_WIDTH = 1.5082966671798224;
        public static final double KA = 0.0993;
        public static final double VELOCITY_PER_100_MS_CONVERSION_FACTOR = 0;

        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(KTRACK_WIDTH);

        public static final double RAMSETE_B = 2; // From WPILib Docs
        public static final double RAMSETE_ZETA = 0.7; // From WPILib Docs

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
