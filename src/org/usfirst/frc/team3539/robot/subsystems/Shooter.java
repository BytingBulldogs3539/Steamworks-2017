package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.commands.SetPointCommand;

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
	
	public Shooter()
	{
		super("Shooter");

		shooterOneMotor = new CANTalon(RobotMap.shooterOneMotorTalon);
		shooterTwoMotor = new CANTalon(RobotMap.shooterTwoMotorTalon);
		
		shooterOneMotor.setSafetyEnabled(false);
		shooterTwoMotor.setSafetyEnabled(false);

		shooterTwoMotor.changeControlMode(TalonControlMode.Follower);
		shooterTwoMotor.set(shooterOneMotor.getDeviceID());

		shooterOneMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		shooterOneMotor.reverseSensor(true);

		shooterHoodMotor = new CANTalon(RobotMap.shooterServoTalon);
		shooterHoodMotor.setSafetyEnabled(false);
		shooterHoodMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);

		agitatorMotor = new CANTalon(RobotMap.agitatorTalon);
		agitatorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		agitatorMotor.setSafetyEnabled(false);
		
		//shooterHoodMotor.setF(RobotMap.hoodEff);
		//shooterHoodMotor.setP(RobotMap.hoodPea);
		//shooterHoodMotor.setI(RobotMap.hoodEye);
		//shooterHoodMotor.setD(RobotMap.hoodDee);
		//shooterHoodMotor.enableControl();
		/*
		 * shooterHoodMotor.setForwardSoftLimit(94);
		 * shooterHoodMotor.enableForwardSoftLimit(false); // Needs To Be Fixed
		 * shooterHoodMotor.setReverseSoftLimit(944);
		 * shooterHoodMotor.enableReverseSoftLimit(false);
		 */
		shooterOneMotor.setEncPosition(0);
		agitatorMotor.setEncPosition(0);
	}

	public void setMotorPower(double power)
	{
		shooterOneMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterOneMotor.set(power);
	}

	public void settempHoodAngle(double encoderValue)
	{
		shooterHoodMotor.set(encoderValue);
	}
	
	public void setHoodAngle(double encoderValue)
	{
		shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		//shooterHoodMotor.reverseSensor(true);
		shooterHoodMotor.setF(RobotMap.hoodEff);
		shooterHoodMotor.setP(RobotMap.hoodPea);
		shooterHoodMotor.setI(RobotMap.hoodEye);
		shooterHoodMotor.setD(RobotMap.hoodDee);
		//shooterHoodMotor.enableControl();
	
		System.out.println("shooterHoodMotor set: " + encoderValue);
		shooterHoodMotor.set(encoderValue);
	}

	public void setAgitatorMotorPower(double power)
	{
		agitatorMotor.set(power);
	}

	public double getShooterRPM()
	{
		return shooterOneMotor.getSpeed();
	}
	
	public void resetAgitatorPID()
	{
		agitatorMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		agitatorMotor.setProfile(0);
		agitatorMotor.setF(0);
		agitatorMotor.setP(0);
		agitatorMotor.setI(0);
		agitatorMotor.setD(0);
		agitatorMotor.set(0);
		agitatorMotor.ClearIaccum();
		agitatorMotor.enableControl();
	}
	
	public void setAgitatorPID()
	{
		agitatorMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		agitatorMotor.setF(RobotMap.agitatorEff);
		agitatorMotor.setP(RobotMap.agitatorPea);
		agitatorMotor.setI(RobotMap.agitatorEye);
		agitatorMotor.setD(RobotMap.agitatorDee);
		agitatorMotor.enableControl();
	}
	
	public void startAgitator(double rpm)
	{
		agitatorMotor.set(rpm);
	}

	public void resetShooterPID()
	{
		shooterOneMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterOneMotor.setProfile(0);
		shooterOneMotor.setF(0);
		shooterOneMotor.setP(0);
		shooterOneMotor.setI(0);
		shooterOneMotor.setD(0);
		shooterOneMotor.set(0);
		shooterOneMotor.ClearIaccum();
		shooterOneMotor.enableControl();
	}
	
	public void setShooterPID()
	{
		shooterOneMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterOneMotor.reverseSensor(true);
		shooterOneMotor.setF(RobotMap.shootEff);
		shooterOneMotor.setP(RobotMap.shootPea);
		shooterOneMotor.setI(RobotMap.shootEye);
		shooterOneMotor.setD(RobotMap.shootDee);
		shooterOneMotor.enableControl();
	}
	
	public void startShooter(double rpm)
	{
		shooterOneMotor.set(rpm);
	}

	public void ballPlusPlus()
	{
		RobotMap.ballCount++;
	}
	
	public boolean isBalling()
	{
		return lightSensorOne.get();
	}

	public double getHoodPosition()
	{
		return shooterHoodMotor.getPulseWidthPosition();
	}

	@Override
	@SuppressWarnings("deprecation")
	public void Update()
	{
		SmartDashboard.putNumber("Ball Count", RobotMap.ballCount);
		SmartDashboard.putBoolean("lightSensorTwo", lightSensorTwo.get());
		SmartDashboard.putBoolean("lightSensorOne", lightSensorOne.get());

		SmartDashboard.putDouble("Current Shooter RPM", shooterOneMotor.getSpeed());
		SmartDashboard.putDouble("Shooter Hood Encoder", getHoodPosition());
		
		RobotMap.agitatorSpeed = SmartDashboard.getDouble("Agitator Speed");
		RobotMap.shooterRpm = SmartDashboard.getDouble("Target RPM for shooter");

		RobotMap.shootPea = SmartDashboard.getDouble("shootPea");
		RobotMap.shootEye = SmartDashboard.getDouble("shootEye");
		RobotMap.shootDee = SmartDashboard.getDouble("shootDee");
		
		RobotMap.hoodPea = SmartDashboard.getDouble("hoodPea");
		RobotMap.hoodEye = SmartDashboard.getDouble("hoodEye");
		RobotMap.hoodDee = SmartDashboard.getDouble("hoodDee");
		RobotMap.hoodEff = SmartDashboard.getDouble("hoodEff");
		
		RobotMap.hoodTarget = SmartDashboard.getDouble("hoodTarget");
		
		//RobotMap.agitatorRpm = SmartDashboard.getDouble("agitatorTarget");
		
		RobotMap.agitatorPea = SmartDashboard.getDouble("agitatorPea");
		RobotMap.agitatorEye = SmartDashboard.getDouble("agitatorEye");
		RobotMap.agitatorDee = SmartDashboard.getDouble("agitatorDee");
		RobotMap.agitatorEff = SmartDashboard.getDouble("agitatorEff");
		
	}

	@Override
	@SuppressWarnings("deprecation")
	public void SmartInit()
	{
		SmartDashboard.putData(new SetPointCommand(RobotMap.hoodTarget));
		SmartDashboard.putNumber("Ball Count", RobotMap.ballCount);
		SmartDashboard.putBoolean("lightSensorTwo", lightSensorTwo.get());
		SmartDashboard.putBoolean("lightSensorOne", lightSensorOne.get());

		SmartDashboard.putDouble("Current Shooter RPM", 0);
		SmartDashboard.putDouble("Shooter Hood Encoder", 0);

		SmartDashboard.putDouble("Agitator Speed", RobotMap.agitatorSpeed);
		SmartDashboard.putDouble("Target RPM for shooter", RobotMap.shooterRpm);
		
		
		SmartDashboard.putDouble("Target RPM for agitator", RobotMap.agitatorRpm);
		

		

		SmartDashboard.putDouble("shootPea", RobotMap.shootPea);
		SmartDashboard.putDouble("shootEye", RobotMap.shootEye);
		SmartDashboard.putDouble("shootDee", RobotMap.shootDee);
		
		SmartDashboard.putDouble("hoodPea", RobotMap.hoodPea);
		SmartDashboard.putDouble("hoodEye", RobotMap.hoodEye);
		SmartDashboard.putDouble("hoodDee", RobotMap.hoodDee);
		SmartDashboard.putDouble("hoodEff", RobotMap.hoodEff);
		
		SmartDashboard.putDouble("agitatorPea", RobotMap.agitatorPea);
		SmartDashboard.putDouble("agitatorEff", RobotMap.agitatorEff);
		SmartDashboard.putDouble("agitatorEye", RobotMap.agitatorEye);
		SmartDashboard.putDouble("agitatorDee", RobotMap.agitatorDee);

	
		SmartDashboard.putDouble("hoodTarget", RobotMap.hoodTarget);
	}

	public void initDefaultCommand()
	{
	}
}