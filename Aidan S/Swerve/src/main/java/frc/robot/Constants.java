package frc.robot;

public class Constants {

    public static final ControlType CONTROL_TYPE = ControlType.GAMEPAD;
    public enum ControlType {JOYSTICKS, GAMEPAD}
    public enum StickType {LEFT, RIGHT}
    public enum StickDirection {X, Y}
    public static final double DRIVE_MODIFIER = 0.6;

    public static final int FL_TURN_CANID = 4;      //2
    public static final int FL_DRIVE_CANID = 0;     //2
    public static final int FR_TURN_CANID = 11;     //1
    public static final int FR_DRIVE_CANID = 15;    //1
    public static final int BL_TURN_CANID = 5;      //3
    public static final int BL_DRIVE_CANID = 1;     //3
    public static final int BR_TURN_CANID = 10;     //4
    public static final int BR_DRIVE_CANID = 14;    //4
    
    public static final double FL_GEAR_RATIO = -1664;
    public static final double FR_GEAR_RATIO = -1661;
    public static final double BL_GEAR_RATIO = -1629;
    public static final double BR_GEAR_RATIO = -717;

	public static final double FL_TURN_ZERO = -772.0;
	public static final double FR_TURN_ZERO = -818.0;
	public static final double BL_TURN_ZERO = 408.0;
	public static final double BR_TURN_ZERO = -430.0;
}