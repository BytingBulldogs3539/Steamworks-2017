package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

/**
 *
 */
public class TriggerModifierCommand extends BulldogCommand
{

	public TriggerModifierCommand()
	{
		super("TriggerModifierCommand");
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		RobotMap.triggerModified = true;
	}

	protected boolean isFinished()
	{
		return !Robot.oi.twobuttonx.get();
	}

	protected void end()
	{
		RobotMap.triggerModified = false;
	}

	protected void interrupted()
	{
		end();
	}
}
