package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.utilities.BulldogCommand;

/**
 *
 */
public class VoidCommand extends BulldogCommand
{

	public VoidCommand()
	{
		super("VoidCommand");
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return true;
	}

	protected void end()
	{
		super.end("VoidCommand");
	}

	protected void interrupted()
	{
	super.interrupted();
		
	}
}
