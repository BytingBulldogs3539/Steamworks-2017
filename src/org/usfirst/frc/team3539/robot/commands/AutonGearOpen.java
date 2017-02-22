package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

public class AutonGearOpen extends BulldogCommand
{

	public AutonGearOpen()
	{
		super("AutonGearOpen");
		requires(Robot.manipulator);
	}

	protected void initialize()
	{
		Robot.manipulator.flipHood();
	}

	protected void execute()
	{
		isFinished();
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
