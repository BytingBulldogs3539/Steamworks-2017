package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroHoodCommand extends Command
{

	public ZeroHoodCommand()
	{
		requires(Robot.hoodSubsystem);
	}

	protected void initialize()
	{
		System.out.println("Zeroed Hood");
	}

	protected void execute()
	{
		Robot.hoodSubsystem.zeroHoodEncoders();
	}

	protected boolean isFinished()
	{
		return true;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		end();
	}
}
