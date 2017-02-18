package org.usfirst.frc.team3539.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class GearManipulator extends Subsystem
{
	@SuppressWarnings("unused")
	private CANTalon hoodCan;
	@SuppressWarnings("unused")
	private CANTalon gearCan;
	
	private DoubleSolenoid hood;
	private DoubleSolenoid gear;
	
	
	private boolean hoodStatus;
	private boolean gearStatus;

	public GearManipulator()
	{
		super("GearManipulator");
		hoodStatus = true;
		gearStatus = false;
		
		hoodCan = new CANTalon(RobotMap.pcm);
		hood = new DoubleSolenoid(RobotMap.pcm, RobotMap.hoodSolOn, RobotMap.hoodSolOff);
		hood.set(DoubleSolenoid.Value.kForward);

		gearCan = new CANTalon(RobotMap.pcm);
		gear = new DoubleSolenoid(RobotMap.pcm,RobotMap.gearSolOn, RobotMap.gearSolOff);
	}
	
	public void flipHood()
	{
		hoodStatus = !hoodStatus;
		
		if(hoodStatus == true)
		{
			hood.set(DoubleSolenoid.Value.kForward);
		}
		if(hoodStatus == false)
		{
			hood.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void GearHolders()
	{
		gearStatus = !gearStatus;
		
		if(gearStatus == true)
		{
			gear.set(DoubleSolenoid.Value.kForward);
		}
		if(gearStatus == false)
		{
			gear.set(DoubleSolenoid.Value.kReverse);
		}
	}
	public void initDefaultCommand()
	{
	}
	public void update()
	{
		SmartDashboard.putString("Hood Status", "-");
		SmartDashboard.putString("Gear Holder Status", "-");
		if (hoodStatus == true)
		{
			SmartDashboard.putString("Hood Status", "Opened");
		}
		else
		{
			SmartDashboard.putString("Hood Status", "Closed");
		}
		if (gearStatus == true)
		{
			SmartDashboard.putString("Gear Holder Status", "Opened");
		}
		else
		{
			SmartDashboard.putString("Gear Holder Status", "Closed");
		}
	}
}