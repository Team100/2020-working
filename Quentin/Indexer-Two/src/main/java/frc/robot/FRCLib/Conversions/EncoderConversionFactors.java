/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.FRCLib.Conversions;

/**
 * Add your docs here.
 */
public class EncoderConversionFactors {

    /**
     * Converts an angle to encoder ticks
     * 
     * Angle zero is parallel to the ground
     */
    public static final int CONVERT_ANGLE_TO_TALON_FX_ENCODER_TICKS(double angle) {// Talon FX has 2048 cpr encoder
        int encoderTicksFX = (int) ((angle / 360) * 2048);
        return encoderTicksFX;
    }

    public static final int CONVERT_ANGLE_TO_MA3_ENCODER_TICKS(double angle) {// MA3 is an absolute encoder
        boolean tenBit = true;// because this is an absoulute encoder, change this to true if we are using 10
                              // bit or false if 12
        int encoderTicksMA3;
        if (tenBit) {
            encoderTicksMA3 = (int) ((angle / 360) * 1024);
        } else {
            encoderTicksMA3 = (int) ((angle / 360) * 4096);
        }
        return encoderTicksMA3;
    }

    public static final int CONVERT_ANGLE_TO_ARMBOT_ENCODER_TICKS(double angle) {// Armbot RS7 is a 12 cpr encoder,
                                                                                 // though good to check
        int encoderTicksArmbot = (int) ((angle / 360) * 12);
        return encoderTicksArmbot;
    }

    public static final int CONVERT_ANGLE_TO_PLG_ENCODER_TICKS(double angle) {// no idea which one we are using, will
                                                                              // check, if plg motor 44.4 ppr than this
                                                                              // should work
        int encoderTicksPLG = (int) ((angle / 360) * 8.108);
        return encoderTicksPLG;
    }

    public static final int CONVERT_ANGLE_TO_CUI_ENCODER_TICKS(double angle) {// no idea which one we are using, will
                                                                              // check, if AMT10 at 2048ppr this is
                                                                              // correct
        int encoderTicksCUI = (int) ((angle / 360) * 2048);
        return encoderTicksCUI;
    }
}