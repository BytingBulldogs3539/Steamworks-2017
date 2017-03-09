package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoWait extends Command
{
	private double seconds = 0;
	private int counter;

	public AutoWait(double inputSeconds)
	{
		requires(Robot.driveTrain);
		seconds = inputSeconds;
		counter = 0;
	}

	protected void initialize()
	{
		counter = 0;
	}

	protected void execute()
	{
		counter++;
	}

	protected boolean isFinished()
	{
		if (counter * 20 >= seconds * 1000)
		{
			return true;
		}
		else
			return false;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		end();
	}
}
