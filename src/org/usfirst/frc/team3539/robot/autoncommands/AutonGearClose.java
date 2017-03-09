package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonGearClose extends Command
{

	public AutonGearClose()
	{
		requires(Robot.manipulator);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.manipulator.holderClose();
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
