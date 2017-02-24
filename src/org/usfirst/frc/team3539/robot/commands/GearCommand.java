
package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

/**
 * Toggles the Gear Holders open or closed based on their previous state
 */

public class GearCommand extends BulldogCommand
{

	public GearCommand()
	{
		super("HoodCommand");
		requires(Robot.manipulator);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.manipulator.holderOpen();
	}

	protected boolean isFinished()
	{
		return !Robot.oi.twobuttona.get();
	}

	protected void end()
	{
		Robot.manipulator.holderClose();
	}

	protected void interrupted()
	{
		end();
	}
}
