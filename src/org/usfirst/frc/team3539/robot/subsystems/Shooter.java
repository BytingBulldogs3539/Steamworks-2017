package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends BulldogSystem
{
	private CANTalon shooterOneMotor;
	private CANTalon shooterTwoMotor;
	private CANTalon shooterServo;
	private CANTalon agitatorTalon;
	
	//private Encoder servoEncoder;
	
	public Shooter()
	{
		super("Shooter");
		shooterOneMotor = new CANTalon(RobotMap.shooterOneMotorTalon);
		
		
		//shooterTwoMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
    	//shooterTwoMotor.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, 10);
    	//shooterTwoMotor.setEncPosition(0);
		
		//shooterOneMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
    	//shooterOneMotor.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, 10);
    	//shooterOneMotor.setEncPosition(0);
    		
		shooterTwoMotor = new CANTalon(RobotMap.shooterTwoMotorTalon);
		shooterServo = new CANTalon(RobotMap.shooterServoTalon);
		//servoEncoder = new Encoder();
		agitatorTalon = new CANTalon(RobotMap.agitatorTalon);
		shooterTwoMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		shooterOneMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
	}

	public void shooterOneMotor()
	{
	
	}
	public void setMotorPower(double power)
	{
		shooterOneMotor.set(power);
		shooterTwoMotor.set(power);
		System.out.println(shooterOneMotor.getPulseWidthVelocity());
		System.out.println(shooterTwoMotor.getPulseWidthVelocity());
		shooterTwoMotor.set(power);
	}
	
	public void Update()
	{
		RobotMap.shootSpeed = SmartDashboard.getNumber("Shooter Speed");
	}
	
	public void setServoAngle(double angle)
	{
		shooterServo.set(angle);
	}
	
	public void setAgitatorMotorPower(double power)
	{
		agitatorTalon.set(power);
		System.out.println("agitator ran");
	}

	public void initDefaultCommand()
	{
	}
}