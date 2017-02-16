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
	@SuppressWarnings("unused")
	//private CANTalon lockCan; //this one too
	private CANTalon intakeMotor;
	private DoubleSolenoid lock;
	private boolean lockStatus;

	public Intake()
	{
		super("Intake");
		intakeMotor = new CANTalon(RobotMap.intakeMotorTalon);
		
		//lockCan = new CANTalon(RobotMap.pcm); // i think this line is bs
		lock = new DoubleSolenoid(RobotMap.pcm, RobotMap.lockSolOn, RobotMap.lockSolOff);
		lockStatus = false;  
	}
	
	public void setMotorPower(double power)
	{
		intakeMotor.set(power);
	}
	
	public void toggleLock()
	{
		lockStatus = !lockStatus;
		
		if(lockStatus == true)
		{
			lock.set(DoubleSolenoid.Value.kForward);
			System.out.println("True");
		}
		if(lockStatus == false)
		{
			lock.set(DoubleSolenoid.Value.kReverse);
			System.out.println("False");

		}
	}
	public void Update()
	{
		SmartDashboard.putBoolean("Lock Status", lockStatus);
		//SmartDashboard.putBoolean("Lock Status", lockStatus);
	}

	
	public void initDefaultCommand()
	{
	}
}
