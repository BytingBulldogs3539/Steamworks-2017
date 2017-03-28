package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HoodSubsystem extends PIDSubsystem
{
	public CANTalon shooterHoodMotor;

	// DigitalInput lightSensorOne = new DigitalInput(1);
	// DigitalInput lightSensorTwo = new DigitalInput(0);

	public HoodSubsystem()
	{
		super("HoodSubsystem", 0, 0, 0);

		shooterHoodMotor = new CANTalon(RobotMap.shooterServoTalon);
		shooterHoodMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterHoodMotor.setSafetyEnabled(false);

		this.setAbsoluteTolerance(20);
		this.setOutputRange(-.35, .35);

		zeroHoodEncoders();
		this.setSetpoint(0);
	}
	
	public void disableHoodPid()
	{
		this.getPIDController().disable();
	}

	public void setAngle(double angle)
	{
		this.getPIDController().setPID(SmartDashboard.getDouble("HoodP"), SmartDashboard.getDouble("HoodI"),SmartDashboard.getDouble("HoodD"));
		this.getPIDController().enable();
		this.setSetpoint(angle);
	}

	public void zeroHoodEncoders()
	{
		shooterHoodMotor.setPosition(0);
		shooterHoodMotor.setEncPosition(0);
	}

	public double getHoodPosition()
	{
		//return shooterHoodMotor.getPosition();
		return shooterHoodMotor.getEncPosition()*-1;
	}
	public void setHoodpower(double power)
	{
		shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterHoodMotor.set(power);
	}
	@SuppressWarnings("deprecation")
	public void Update()
	{
		SmartDashboard.putDouble("Shooter Hood Encoder", getHoodPosition());

		RobotMap.hoodTarget = SmartDashboard.getDouble("hoodTarget");
		SmartDashboard.putNumber("HoodCurrent", Robot.hoodSubsystem.shooterHoodMotor.getOutputCurrent());
	}

	@SuppressWarnings("deprecation")
	public void SmartInit()
	{

		SmartDashboard.putDouble("hoodTarget", RobotMap.hoodTarget);
		SmartDashboard.putDouble("Shooter Hood Encoder", 0);
		SmartDashboard.putDouble("HoodP", RobotMap.hoodPea);
		SmartDashboard.putDouble("HoodI", RobotMap.hoodEye);
		SmartDashboard.putDouble("HoodD", RobotMap.hoodDee);
	}
	

	public void initDefaultCommand()
	{
	}

	@Override
	protected double returnPIDInput()
	{
		return this.getHoodPosition();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		if (!this.onTarget())
		{
			shooterHoodMotor.set(-output);
		}
		else
		{
		//	this.getPIDController().disable();
		}

	}
}