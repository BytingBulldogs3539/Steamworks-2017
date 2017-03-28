package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoodReset extends Command
{
	private double seconds;

	public HoodReset(double seconds)
	{
		requires(Robot.hoodSubsystem);
		this.seconds = seconds;
	}

	protected void initialize()
	{
		this.setTimeout(seconds);
	}

	protected void execute()
	{
		Robot.hoodSubsystem.setHoodpower(.2);
	}

	protected boolean isFinished()
	{
		return isTimedOut();
	}

	protected void end()
	{
        Robot.hoodSubsystem.setHoodpower(0);
        Robot.hoodSubsystem.zeroHoodEncoders();
	}

	protected void interrupted()
	{
		end();
	}
}
