package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class LightCommand extends Command
{
	int BallCount;

	public LightCommand()
	{
		System.out.println(BallCount);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{

	}

	// Called repeatedly when t+his Command is scheduled to run
	protected void execute()
	{
		if(Robot.lighhtt.getlight() == true)
		{
			Robot.lighhtt.ballCount();
			System.out.println(BallCount);
			
		}
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
