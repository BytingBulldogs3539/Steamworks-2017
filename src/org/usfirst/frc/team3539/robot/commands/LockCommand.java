package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LockCommand extends Command
{

	public LockCommand()
	{
		super("LockCommand");
		requires(Robot.intake);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.intake.toggleLock();
		System.out.println("Lock command was run!!!!213412423rs");
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
