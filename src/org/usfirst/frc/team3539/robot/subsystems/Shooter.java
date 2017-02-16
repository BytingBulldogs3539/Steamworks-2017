package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;

public class Shooter extends BulldogSystem
{
	private CANTalon shooterOneMotor;
	private CANTalon shooterTwoMotor;
	private CANTalon shooterServo;
	//private Encoder servoEncoder;

	public Shooter()
	{
		super("Shooter");
		shooterOneMotor = new CANTalon(RobotMap.shooterOneMotorTalon);
		shooterOneMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		
		shooterTwoMotor = new CANTalon(RobotMap.shooterTwoMotorTalon);
		shooterServo = new CANTalon(RobotMap.shooterServoTalon);
		//servoEncoder = new Encoder();
	}

	public void shooterOneMotor()
	{
	
	}
	public void setMotorPower(double power)
	{


		shooterOneMotor.set(power);
		System.out.println(shooterOneMotor.getEncPosition());
		shooterTwoMotor.set(power);
	}
	public void Update()
	{
		
	}
	public void setServoAngle(double angle)
	{
		shooterServo.set(angle);
	}

	public void initDefaultCommand()
	{
	}
}