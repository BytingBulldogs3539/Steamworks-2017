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
	private CANTalon shooterHoodMotor;

	// DigitalInput lightSensorOne = new DigitalInput(1);
	// DigitalInput lightSensorTwo = new DigitalInput(0);

	public HoodSubsystem()
	{
		super("HoodSubsystem",0,0,0);

		shooterHoodMotor = new CANTalon(RobotMap.shooterServoTalon);
		shooterHoodMotor.setSafetyEnabled(false);
		shooterHoodMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
		this.setAbsoluteTolerance(20);
		this.setOutputRange(-.3, .3);
		
		zeroHoodEncoders();
		setMotorPower(0);
		this.setSetpoint(0);
	}

	public void setAngle(double angle)
	{
		System.out.println("setting " + angle);
		this.getPIDController().setPID(SmartDashboard.getDouble("HoodP"), SmartDashboard.getDouble("HoodI"), SmartDashboard.getDouble("HoodD"));
		this.getPIDController().enable();
		this.setSetpoint(angle);
	}
	public void setMotorPower(double power)
	{
		shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterHoodMotor.set(power);
	}

	@Deprecated
	public void settempHoodAngle(double encoderValue)
	{
		shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		shooterHoodMotor.set(encoderValue);
	}

	@Deprecated
	public void setHoodAngle(double encoderValue)
	{
		shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		shooterHoodMotor.setP(RobotMap.hoodPea);
		shooterHoodMotor.setI(RobotMap.hoodEye);
		shooterHoodMotor.setD(RobotMap.hoodDee);

		System.out.println("shooterHoodMotor set: " + encoderValue);
		shooterHoodMotor.set(encoderValue);
	}

	public void zeroHoodEncoders()
	{
		shooterHoodMotor.setEncPosition(0);
	}

	public double getHoodPosition()
	{
		return shooterHoodMotor.getEncPosition() * -1;
	}

	@SuppressWarnings("deprecation")
	public void Update()
	{
		SmartDashboard.putDouble("Shooter Hood Encoder", getHoodPosition());

		RobotMap.hoodTarget = SmartDashboard.getDouble("hoodTarget");
	}

	@SuppressWarnings("deprecation")
	public void SmartInit()
	{
		
		SmartDashboard.putDouble("hoodTarget", RobotMap.hoodTarget);
		SmartDashboard.putDouble("Shooter Hood Encoder", 0);
		SmartDashboard.putDouble("HoodP", 0);
		SmartDashboard.putDouble("HoodI", 0);
		SmartDashboard.putDouble("HoodD", 0);	}

	public void initDefaultCommand()
	{
	}


	@Override
	protected double returnPIDInput()
	{
		// TODO Auto-generated method stub
		return this.getHoodPosition();
	}


	@Override
	protected void usePIDOutput(double output)
	{
		if(!this.onTarget())
		{
			shooterHoodMotor.set(-output);
		}
		// TODO Auto-generated method stub
		
	}
}