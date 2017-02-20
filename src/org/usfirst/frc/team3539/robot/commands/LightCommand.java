package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class LightCommand extends Command
{	
	public LightCommand()
	{
		System.out.println(RobotMap.ballCount);
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{

	}

	// Called repeatedly when t+his Command is scheduled to run
	protected void execute()
	{
		/*if(Robot.shooter.getlight() == false)
		{
			Robot.shooter.ballCount();
			System.out.println(RobotMap.ballCount);
		}*/
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
