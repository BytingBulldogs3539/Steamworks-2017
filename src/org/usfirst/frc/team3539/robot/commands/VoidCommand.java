package org.usfirst.frc.team3539.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

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
	}

	protected void interrupted()
	{
		end();
	}
}
