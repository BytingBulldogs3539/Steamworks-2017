package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends BulldogSystem
{
	private CANTalon intakeMotor;
	private DoubleSolenoid lock;
	private boolean lockStatus;

	public Intake()
	{
		super("Intake");
		intakeMotor = new CANTalon(RobotMap.intakeMotorTalon);

		lock = new DoubleSolenoid(RobotMap.pcm, RobotMap.lockSolOn, RobotMap.lockSolOff);
		this.lockOff();
		lockStatus = false;
		
		intakeMotor.configNominalOutputVoltage(0.0f, -0.0f);
		intakeMotor.configPeakOutputVoltage(12.0f, -12.0f);
		
		intakeMotor.configMaxOutputVoltage(12);
		intakeMotor.setCurrentLimit(37);
	}

	public void setMotorPower(double power)
	{
		intakeMotor.set(power);
	}

	public void lockOff()
	{
		lock.set(DoubleSolenoid.Value.kForward);
	}

	public void lockOn()
	{
		lock.set(DoubleSolenoid.Value.kReverse);
	}

	public void toggleLock()
	{
		lockStatus = !lockStatus;

		if(lockStatus == true)
		{
			lock.set(DoubleSolenoid.Value.kForward);
		}
		if(lockStatus == false)
		{
			lock.set(DoubleSolenoid.Value.kReverse);
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public void Update()
	{
		SmartDashboard.putBoolean("Lock Status", lockStatus);
		
		RobotMap.intakeSpeed = SmartDashboard.getNumber("Intake Speed");
		RobotMap.unjamIntakeSpeed = SmartDashboard.getNumber("Intake Unjam Speed");
		
		SmartDashboard.putNumber("Intake Voltzish", intakeMotor.getOutputCurrent());
	}

	@Override
	public void SmartInit()
	{
		SmartDashboard.putNumber("Intake Speed", RobotMap.intakeSpeed);
		SmartDashboard.putNumber("Intake Unjam Speed", RobotMap.unjamIntakeSpeed);
		SmartDashboard.putNumber("Intake Voltzish", 0);
	}

	public void initDefaultCommand()
	{
	}
}
