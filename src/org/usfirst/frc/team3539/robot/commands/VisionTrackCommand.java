package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionTrackCommand extends BulldogCommand
{
	public VisionTrackCommand()
	{
		super("VisionTrackCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		// Robot.shooter
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