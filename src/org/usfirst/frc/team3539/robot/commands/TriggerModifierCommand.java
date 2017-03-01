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
		super.initialize("TriggerModifierCommand");
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
		super.end("TriggerModifierCommand");
		RobotMap.triggerModified = false;
	}

	protected void interrupted()
	{
		end();
	}
}
