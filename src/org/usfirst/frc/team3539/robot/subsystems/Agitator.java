package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Agitator extends Subsystem
{
	private CANTalon agitatorTalon;

	public Agitator()
	{
		super("Agitator");
		agitatorTalon = new CANTalon(RobotMap.agitatorTalon);
	}

	public void setMotorPower(double power)
	{
		agitatorTalon.set(power);
		System.out.println("agitator ran");
	}

	public void initDefaultCommand()
	{
	}
}
