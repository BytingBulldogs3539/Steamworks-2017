package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class ZeroHoodCommand extends Command
{
	private int ticks = 0;
	public ZeroHoodCommand()
	{
		requires(Robot.hoodSubsystem);
	}

	protected void initialize()
	{
		System.out.println("Zeroed Hood");
		ticks=0;
	}

	protected void execute()
	{
		Robot.hoodSubsystem.setHoodpower(.2);
	}

	protected boolean isFinished()
	{
		Robot.hoodSubsystem.zeroHoodEncoders();

		if (Robot.hoodSubsystem.shooterHoodMotor.getOutputCurrent() >= 10 || ticks > 1000)
		{
			Robot.hoodSubsystem.setHoodpower(0);
			return true;
		}
		ticks++;
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
