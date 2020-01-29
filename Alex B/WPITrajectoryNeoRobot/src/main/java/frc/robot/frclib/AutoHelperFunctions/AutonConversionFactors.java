/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.frclib.AutoHelperFunctions;


/**
 * Handles conversions between NEO/Spark MAX, WPILib, and Real World units
 */
public class AutonConversionFactors {
	public static final double conversionFactor = 6;
    ///////////////////////////////////////////////////////////////////////////////////////////
	//                                                                                       //
	// Understanding WPILib and Spark MAX Units                                              //
	//                                                                                       //
	///////////////////////////////////////////////////////////////////////////////////////////
	//                                                                                       //
	// WPILib Trajectory utilizes meters per second (m/s) for all velocity values            //
	// The Spark MAX and Neo use revolutions per minute (rpm)                                //
	//                                                                                       //
	// This file acts as a conversion for units between WPILib Trajectory and Spark MAX/NEO. //
	//                                                                                       //
	// All distances provided to WPILib Trajectory should be in meters                       //
	//                                                                                       //
	///////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * Convert velocities from Revolutions per Minute to Meters per Second
	 * @param neoVelocity 	the native velocity of a NEO in RPM
	 * @param wheelDiameter the width of the wheel in meters
	 * @param gearingRatio 	the ratio of gearing from the Neo to output wheel
	 * @return 				the velocity of the Neo in Meters per Second (WPILib Trajectory)
	 */
	public static final double convertRPMToMpS(double neoVelocity, double wheelDiameter, double gearingRatio){
		double result = (neoVelocity * Math.PI * wheelDiameter)/(60*gearingRatio)/4;
		return result;
	}

	/**
	 * Convert velocities from Meters per Second to Revolutions per Minute
	 * @param metersPerSecond 	the velocity from WPILib Trajectory
	 * @param wheelDiameter		the diameter of the wheel in meters
	 * @param gearingRatio		the ratio of the gearing from the Neo to the output wheel
	 * @return					the velocity from Trajectory in Revolutions per Minute (NEO)
	 */
	public static final double convertMpSToRPM(double metersPerSecond, double wheelDiameter, double gearingRatio){
		double result = (metersPerSecond/(Math.PI*wheelDiameter))*gearingRatio*60*conversionFactor;
			// meters/sec * gearRatio * 60
			//-------------------------------
			//         pi * diameter
		return result;
	}

	public static final double convertTicksToMeters(int ticks, double wheelDiameter, int ticksPerRev){
		double result = ticks;
		double circumference = Math.PI*wheelDiameter;
		double metersPerTick = circumference/ticksPerRev;
		result = result * metersPerTick;
		return result/conversionFactor;
	}


    
}