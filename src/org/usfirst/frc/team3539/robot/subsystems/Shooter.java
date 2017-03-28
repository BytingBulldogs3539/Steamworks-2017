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
	//private CANTalon shooterHoodMotor;
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


		shooterOneMotor.configNominalOutputVoltage(0.0f, -0.0f);
		shooterOneMotor.configPeakOutputVoltage(12.0f, -12.0f);
		


		agitatorMotor = new CANTalon(RobotMap.agitatorTalon);
		agitatorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		agitatorMotor.setSafetyEnabled(false);
		
		agitatorMotor.configNominalOutputVoltage(0.0f, -0.0f);
		agitatorMotor.configPeakOutputVoltage(12.0f, -12.0f);


		
	
		shooterOneMotor.setEncPosition(0);
		agitatorMotor.setEncPosition(0);
	}

	public void setMotorPower(double power)
	{
		shooterOneMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterOneMotor.set(power);
	}


	public void setAgitatorMotorPower(double power)
	{
		agitatorMotor.set(power);
	}

	public double getShooterRPM()
	{
		return shooterOneMotor.getSpeed();
	}
	
	public double getAgitatorRPM()
	{
		return agitatorMotor.getSpeed();
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
		System.out.println(agitatorMotor.getPulseWidthVelocity());
	}

	public void disableAgitatorPID()
	{
		this.agitatorMotor.disable();
	}
	public void disableShooterPID()
	{
		shooterOneMotor.disable();
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

	public double getHoodPosition()
	{
		return 0;//return shooterHoodMotor.getPulseWidthPosition();
	}

	@Override
	@SuppressWarnings("deprecation")
	public void Update()
	{
		SmartDashboard.putDouble("Curent agitator rpm", getAgitatorRPM());
		SmartDashboard.putDouble("Current Shooter RPM", getShooterRPM());
		
		RobotMap.agitatorSpeed = SmartDashboard.getDouble("Agitator Speed");
		
		RobotMap.shooterRpm = SmartDashboard.getDouble("Target RPM for shooter");

		RobotMap.shootPea = SmartDashboard.getDouble("shootPea");
		RobotMap.shootEye = SmartDashboard.getDouble("shootEye");
		RobotMap.shootDee = SmartDashboard.getDouble("shootDee");
		RobotMap.shootEff = SmartDashboard.getDouble("shootEff");
		

		
		RobotMap.agitatorRpm = SmartDashboard.getDouble("Target RPM for agitator");
		
		RobotMap.agitatorPea = SmartDashboard.getDouble("agitatorPea");
		RobotMap.agitatorEye = SmartDashboard.getDouble("agitatorEye");
		RobotMap.agitatorDee = SmartDashboard.getDouble("agitatorDee");
		RobotMap.agitatorEff = SmartDashboard.getDouble("agitatorEff");
	}

	@Override
	@SuppressWarnings("deprecation")
	public void SmartInit()
	{
		SmartDashboard.putDouble("Current Shooter RPM", 0);

		SmartDashboard.putDouble("Agitator Speed", RobotMap.agitatorSpeed);
		SmartDashboard.putDouble("Target RPM for shooter", RobotMap.shooterRpm);
		
		
		SmartDashboard.putDouble("Target RPM for agitator", RobotMap.agitatorRpm);
		

		SmartDashboard.putDouble("shootPea", RobotMap.shootPea);
		SmartDashboard.putDouble("shootEye", RobotMap.shootEye);
		SmartDashboard.putDouble("shootDee", RobotMap.shootDee);
		SmartDashboard.putDouble("shootEff", RobotMap.shootEff);
		


		SmartDashboard.putDouble("agitatorPea", RobotMap.agitatorPea);
		SmartDashboard.putDouble("agitatorEff", RobotMap.agitatorEff);
		SmartDashboard.putDouble("agitatorEye", RobotMap.agitatorEye);
		SmartDashboard.putDouble("agitatorDee", RobotMap.agitatorDee);
	}

	public void initDefaultCommand()
	{
	}
}