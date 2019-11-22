package frc.robot.libswerve.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.libswerve.drive.BaseEnclosure;
import frc.robot.libswerve.drive.SwerveEnclosure;

/**
 * An implementation of the SwerveEnclosure using CanTalon motors and encoders
 */
public class CanTalonSwerveEnclosure extends BaseEnclosure implements SwerveEnclosure {

	private WPI_TalonSRX driveMotor;
	private WPI_TalonSRX steerMotor;
	
    private boolean reverseEncoder = false;
    private boolean reverseSteer = false;
    private double driveModifier;

    public CanTalonSwerveEnclosure(String name, WPI_TalonSRX driveMotor, WPI_TalonSRX steerMotor, double gearRatio, double driveModifier) {

        super(name, gearRatio);

        this.driveMotor = driveMotor;
        this.steerMotor = steerMotor;
        this.driveModifier = driveModifier;
    }

    @Override
    public void stop() {
        // TODO: deprecated...
        this.steerMotor.stopMotor();
        this.driveMotor.stopMotor();
    }

    @Override
    public void setSpeed(double speed) {
    	driveMotor.set(ControlMode.PercentOutput, speed * driveModifier);
    }

    @Override
    public void setAngle(double angle) {
    	steerMotor.set(ControlMode.Position, (reverseSteer ? -1 : 1) * angle * gearRatio);
    }

    @Override
    public int getEncPosition() {
        int reverse = reverseEncoder ? -1 : 1;
        return reverse * steerMotor.getSelectedSensorPosition(0);
    }	

    @Override
    public void setEncPosition(int position) {
    	steerMotor.setSelectedSensorPosition(position, 0, 10);
    }

    public WPI_TalonSRX getDriveMotor()
	{
		return driveMotor;
	}
	
	public WPI_TalonSRX getSteerMotor()
	{
		return steerMotor;
	}
	
	public boolean isReverseEncoder()
	{
		return reverseEncoder;
    }
	
	public void setReverseEncoder(boolean reverseEncoder)
	{
		this.reverseEncoder = reverseEncoder;
	}
	
	public void setReverseSteerMotor(boolean reverseSteer)
	{
		this.reverseSteer = reverseSteer;
	}	
}
