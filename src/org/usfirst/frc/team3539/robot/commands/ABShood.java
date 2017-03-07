package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.subsystems.*;
import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
// if less than target go up and if greater go down
public class ABShood extends Command
{

	public double setpoint;

	public ABShood(/*double point*/)
	{
	//	setpoint = point;

	}

	protected void initialize()
	{

	}

	protected void execute()
	{
		if (Robot.HoodSubsystem.getHoodPosition() > (RobotMap.hoodTarget + 5)
				|| Robot.HoodSubsystem.getHoodPosition() < (RobotMap.hoodTarget - 5))
		{

			if (Robot.HoodSubsystem.getHoodPosition() < RobotMap.hoodTarget)
			{
				Robot.HoodSubsystem.setMotorPower(.1);
			} else
			{
				if (Robot.HoodSubsystem.getHoodPosition() > RobotMap.hoodTarget)
				{

					Robot.HoodSubsystem.setMotorPower(-.1);
				}

				else
				{
					Robot.HoodSubsystem.setMotorPower(0);
				}
			}
		}
	}

	protected boolean isFinished()
	{if (Robot.HoodSubsystem.getHoodPosition() > (RobotMap.hoodTarget + 5)
			|| Robot.HoodSubsystem.getHoodPosition() < (RobotMap.hoodTarget - 5))
	{
		return false;
	}
		return true;
	}

	protected void end()
	{
		super.end();
	}

	protected void interrupted()
	{
		end();
	}
}
