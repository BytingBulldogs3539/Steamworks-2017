package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends BulldogSystem
{
	private CANTalon shooterOneMotor;
	private CANTalon shooterTwoMotor;
	private CANTalon shooterHoodMotor;
	private CANTalon agitatorMotor;

	DigitalInput lightSensorOne = new DigitalInput(1);
	DigitalInput lightSensorTwo = new DigitalInput(0);
	private boolean ballControl = false;

	public Shooter()
	{
		super("Shooter");

		shooterOneMotor = new CANTalon(RobotMap.shooterOneMotorTalon);
		shooterTwoMotor = new CANTalon(RobotMap.shooterTwoMotorTalon);

		shooterTwoMotor.changeControlMode(TalonControlMode.Follower);
		shooterTwoMotor.set(shooterOneMotor.getDeviceID());

		shooterOneMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		shooterOneMotor.configNominalOutputVoltage(0, 0);
		shooterOneMotor.configPeakOutputVoltage(12.0, -12.0);
		shooterOneMotor.reverseSensor(true);

		shooterHoodMotor = new CANTalon(RobotMap.shooterServoTalon);

		agitatorMotor = new CANTalon(RobotMap.agitatorTalon);

		shooterHoodMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);

		/*
		 * shooterHoodMotor.setForwardSoftLimit(94);
		 * shooterHoodMotor.enableForwardSoftLimit(false); // Needs To Be Fixed
		 * shooterHoodMotor.setReverseSoftLimit(944);
		 * shooterHoodMotor.enableReverseSoftLimit(false);
		 */
		shooterOneMotor.setEncPosition(0);
		// if you set the encoder position to zero then you need to be starting
		// in the same mechanical spot
		// defeats the purpose of using absolute encoder?

	}

	public void setMotorPower(double power)
	{
		shooterOneMotor.set(power);
		shooterTwoMotor.set(power);
	}

	public double getShooterRPM()
	{
		return shooterOneMotor.getPulseWidthVelocity();
	}

	public void initShooter()
	{
		shooterOneMotor.setProfile(0);
		shooterOneMotor.setF(0);
		shooterOneMotor.setP(0);
		shooterOneMotor.setI(0);
		shooterOneMotor.setD(0);
		shooterOneMotor.set(0);
	}
	public void startShooter(double rpm)
	{
		shooterOneMotor.reverseSensor(true);
		shooterOneMotor.setF(0);
		shooterOneMotor.setP(.01);
		shooterOneMotor.setI(0);
		shooterOneMotor.setD(.001);
		shooterOneMotor.set(200);
	}
	public void readyShooter(double rpm, double hoodAngle, double power) // rpm
																			// caps
																			// at
																			// ______,
																			// hoodAngle
																			// starts
																			// at
																			// ___
																			// and
																			// ends
																			// at
																			// ___
	{
		// desiredRPM / maxRMX + pidControllerError

		Robot.shooter.setMotorPower(power);

		if (getShooterRPM() >= rpm && hoodAngle == hoodAngle) // replace
																// hoodAngle
																// with boolean
																// from PID
		{
			Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
		} else
		{
			Robot.shooter.setAgitatorMotorPower(0);
		}
	}

	public void countBall()
	{
		if (lightSensorOne.get() == true && ballControl == false)
		{
			RobotMap.ballCount++;
			ballControl = true;
		} else if (lightSensorOne.get() == false && ballControl == true)
		{
			ballControl = false;
		} else
		{
		}
	}

	// public int GetShooterVelocity()
	// {
	// return shooterTwoMotor.getEncVelocity();
	// }

	public double GetPosition()
	{
		return shooterHoodMotor.getEncPosition();
	}

	@Override
	@SuppressWarnings("deprecation")
	public void Update()
	{
		SmartDashboard.putNumber("Ball Count", RobotMap.ballCount);
		SmartDashboard.putBoolean("lightSensorTwo", lightSensorTwo.get());
		SmartDashboard.putBoolean("lightSensorOne", lightSensorOne.get());

		SmartDashboard.putDouble("Current Shooter RPM", shooterOneMotor.getSpeed());
		SmartDashboard.putDouble("Shooter Hood Encoder", shooterHoodMotor.getPulseWidthPosition());

		SmartDashboard.putDouble("Agitator Speed", RobotMap.agitatorSpeed);
		
		RobotMap.agitatorSpeed = SmartDashboard.getNumber("Agitator Speed");
		RobotMap.shooterRpm = SmartDashboard.getDouble("Target RPM for shooter");

		RobotMap.shootPea = SmartDashboard.getDouble("shootPea");
		RobotMap.shootEye = SmartDashboard.getDouble("shootEye");
		RobotMap.shootDee = SmartDashboard.getDouble("shootDee");
	}

	@Override
	@SuppressWarnings("deprecation")
	public void SmartInit()
	{
		SmartDashboard.putNumber("Ball Count", RobotMap.ballCount);
		SmartDashboard.putBoolean("lightSensorTwo", lightSensorTwo.get());
		SmartDashboard.putBoolean("lightSensorOne", lightSensorOne.get());

		SmartDashboard.putDouble("Current Shooter RPM", 0);
		SmartDashboard.putDouble("Shooter Hood Encoder", shooterHoodMotor.getPulseWidthPosition());

		SmartDashboard.putDouble("Agitator Speed", RobotMap.agitatorSpeed);
		SmartDashboard.putDouble("Target RPM for shooter", RobotMap.shooterRpm);

		SmartDashboard.putDouble("shootPea", RobotMap.shootPea);
		SmartDashboard.putDouble("shootEye", RobotMap.shootEye);
		SmartDashboard.putDouble("shootDee", RobotMap.shootDee);
	}

	public void setHoodAngle(double angle) // This needs to be integrated into
											// PID
	{
		shooterHoodMotor.set(angle * -1);
		System.out.println("Angle" + angle);
	}

	public void setAgitatorMotorPower(double power)
	{
		agitatorMotor.set(power);
	}

	public void initDefaultCommand()
	{
	}
}