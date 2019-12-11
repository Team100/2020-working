package frc.robot.supporting;

/**
 * Handles many of the calculations regarding drivetrain motion
 */
public class MotionCalc {

    //Based on the code from https://acb.link/iDzQ

    /**
     * Convert any set of ticks to rotation counts
     * @param ticks The number of ticks to convert
     * @param ticksPerRotation The conversion factor of ticks per rotation
     * @param encoderRatio Value of the gear reduction
     * @return Sensor position in rotations
     */

    public static double ticksToRotations(double ticks, double ticksPerRotation, double encoderRatio){
        return (ticks/ticksPerRotation)*encoderRatio;
    }

    /**
     * Convert wheel rotations to real life units
     * @param rotations Rotations to convert
     * @param circumference The circumference of the rotating object
     * @return The sensor distance in the units of @param circumference
     */
    public static double rotationsToDistance(double rotations, double circumference){
        return (rotations * circumference);
    }

    /**
     * Convert sensor units to rotations per seconds
     * @param ticks The number of ticks to convert
     * @param ticksPerRotation The number of native ticks in each rotation
     * @param period The velocity sampling in seconds (should be .1)
     * @param encoderRatio Account for gearing reduction
     * @return The rotations per second of a given set of ticks
     */
    public static double ticksToRPS(double ticks, double ticksPerRotation, double period, double encoderRatio){
        return ticks * ((1/period)/ticksPerRotation)*encoderRatio;
    }

    /**
     * Convert a distance to wheel rotations
     * @param distance The distance travelled by the rotating object
     * @param circumference The circumference of the rotating object
     * @return the sensor distance in the units of @param circumference
     */
    public static double distanceToRotations(double distance, double circumference){
        return distance/circumference;
    }

    /**
     * Convert sensor units to rotations per minute
     * @param ticks The number of ticks to convert
     * @param ticksPerRotation The number of native ticks in each rotation
     * @param period The velocity sampling in seconds (should be .1)
     * @param encoderRatio Account for gearing reduction
     * @return The rotations per second of a given set of ticks
     */
    public static double ticksToRPM(double ticks, double ticksPerRotation, double period, double encoderRatio){
        return ticksToRPS(ticks, ticksPerRotation, period, encoderRatio)*60;
    }
}