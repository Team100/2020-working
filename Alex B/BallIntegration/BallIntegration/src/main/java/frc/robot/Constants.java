/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.FRCLib.Conversions.EncoderConversionFactors;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


    public static final class TurretConstants{
        public static final class TurretMotionParameters{
            public static final double PERCENT_OUTPUT_FOWARD = 0.5;
        }
        public static final class TurretMotors{
            public static final class TurretMotor{
                public static final int CAN_ID = 6;

                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;
            }
        }
    }
    public static final class IndexerConstants{
        public static final class IndexerSensors{
            public static final class FrontSensor{
                public static final int ID = 3;
            }
            public static final class RearSensor{
                public static final int ID = 0;
            }
        }
        public static final class IndexerMotionParameters{
            public static final double STAGE_ONE_PERCENT_OUTPUT_FOWARD = 0.5;
            public static final double STAGE_TWO_PERCENT_OUTPUT_FOWARD = 0.5;

            public static final double STAGE_ONE_PERCENT_OUTPUT_BACKWARD = 0.5;
            public static final double STAGE_TWO_PERCENT_OUTPUT_BACKWARD = 0.5;
        }
        public static final class IndexerMotors{
            public static final class IndexerStageOne{
                public static final int CAN_ID = 4;

                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;

            }
            public static final class IndexerStageTwo{
                public static final int CAN_ID = 11;
                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;


                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;
            }
        }
    }

    public static final class IntakeConstants{
        public static final class IntakeMotionParameters{
            public static final double INTAKE_PERCENT_OUTPUT = 0.7;
            public static final double INTAKE_ZERO = 0;

            public static final double INTAKE_DOWN_DEGREES = EncoderConversionFactors.CONVERT_ANGLE_TO_MA3_ENCODER_TICKS(-40); 
            public static final double INTAKE_UP_DEGREES = EncoderConversionFactors.CONVERT_ANGLE_TO_MA3_ENCODER_TICKS(90);


        }
        
        public static final class IntakeMotors{
            public static final class IntakeSpin{
                public static final int CAN_ID = 5;

                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;

            }
            public static final class IntakePivot{
                public static final int CAN_ID = 10;

                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;

            }
        }
    }

    public static final class ShooterConstants{
        public static final class ShooterMotionParameters{
            public static final double RECOVER_PO = 0.8;
            public static final double SHOOT_PO = 0.5;
            public static final double STOP_PO = 0;

        }
        public static final class ShooterMotors{
            public static final class ShooterMaster{
                public static final int CAN_ID = 2;

                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;
            }
            public static final class ShooterFollower{
                public static final int CAN_ID = 3;

                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;
            }
        }
    }
    


}
