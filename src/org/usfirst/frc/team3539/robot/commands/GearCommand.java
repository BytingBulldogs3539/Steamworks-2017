
package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

/**
 * Toggles the Gear Holders open or closed based on their previous state
 */

public class GearCommand extends BulldogCommand
{

	public GearCommand()
	{
		super("GearCommand");
		requires(Robot.manipulator);
	}

	protected void initialize()
	{
		super.initialize("GearCommand");
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
		super.end("GearCommand");
		Robot.manipulator.holderClose();
	}

	protected void interrupted()
	{
		end();
	}
}
