package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

/**
 *
 */
public class HoodCommand extends BulldogCommand
{



	public HoodCommand()
	{
		super("HoodCommand");
		requires(Robot.manipulator);
	}

	protected void initialize()
	{
		super.initialize("HoodCommand");
		Robot.manipulator.hoodOpen();
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return !Robot.oi.twobuttonb.get();
	}

	protected void end()
	{
		super.end("HoodCommand");
		Robot.manipulator.hoodClose();
	}

	protected void interrupted()
	{
		end();
	}
}
