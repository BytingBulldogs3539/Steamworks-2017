package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;

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

		shooterHoodMotor = new CANTalon(RobotMap.shooterServoTalon);

		agitatorMotor = new CANTalon(RobotMap.agitatorTalon);

		shooterHoodMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);

		shooterTwoMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);

		/*		shooterHoodMotor.setForwardSoftLimit(94);
				shooterHoodMotor.enableForwardSoftLimit(false); 	 // Needs To Be Fixed
				shooterHoodMotor.setReverseSoftLimit(944);
				shooterHoodMotor.enableReverseSoftLimit(false);
		*/
		shooterOneMotor.setEncPosition(0);
		//if you set the encoder position to zero then you need to be starting in the same mechanical spot
		//defeats the purpose of using absolute encoder?

	}

	public void setMotorPower(double power)
	{
		shooterOneMotor.set(power);
		shooterTwoMotor.set(power);
	}

	public double getShooterRPM()
	{
		return Math.abs(shooterTwoMotor.getPulseWidthVelocity());
	}

	public void readyShooter(double rpm, double hoodAngle) //rpm caps at ______, hoodAngle starts at ___ and ends at ___
	{
		if(getShooterRPM() >= rpm && hoodAngle == hoodAngle) //replace hoodAngle with boolean from PID
		{
			Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
		}
		else
		{
			Robot.shooter.setAgitatorMotorPower(0);
		}
	}

	public void countBall()
	{
		if(lightSensorOne.get() == true && ballControl == false)
		{
			RobotMap.ballCount++;
			ballControl = true;
		}
		else if(lightSensorOne.get() == false && ballControl == true)
		{
			ballControl = false;
		}
		else
		{
		}
	}

	public double GetPosition()
	{
		return shooterHoodMotor.getEncPosition();
	}

	@Override
	@SuppressWarnings("deprecation")
	public void Update()
	{
		SmartDashboard.putNumber("ballCount", RobotMap.ballCount);
		SmartDashboard.putBoolean("lightSensorTwo", lightSensorTwo.get());
		SmartDashboard.putBoolean("lightSensorOne", lightSensorOne.get());

		SmartDashboard.putDouble("Shooter RPM", getShooterRPM());
		SmartDashboard.putDouble("Shooter Hood Encoder", shooterHoodMotor.getPulseWidthPosition());
		SmartDashboard.putInt("Ball Count", RobotMap.ballCount);
		SmartDashboard.putDouble("Agitator Speed", RobotMap.agitatorSpeed);
		RobotMap.shootSpeed = SmartDashboard.getNumber("Shooter Speed");

	}

	public void SmartInit()
	{
		SmartDashboard.putNumber("Shooter Speed", (RobotMap.shootSpeed * -1));
	}

	public void setHoodAngle(double angle)
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