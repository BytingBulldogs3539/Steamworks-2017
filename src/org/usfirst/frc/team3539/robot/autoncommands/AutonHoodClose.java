package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonHoodClose extends Command
{

	public AutonHoodClose()
	{
		requires(Robot.manipulator);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.manipulator.hoodClose();
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
