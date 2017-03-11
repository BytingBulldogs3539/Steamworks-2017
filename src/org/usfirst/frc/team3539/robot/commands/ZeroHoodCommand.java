package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class ZeroHoodCommand extends Command
{

	public ZeroHoodCommand()
	{
		requires(Robot.hoodSubsystem);
	}

	protected void initialize()
	{
		System.out.println("Zeroed Hood");
	}

	protected void execute()
	{
		Robot.hoodSubsystem.setHoodpower(-.2);
	}

	protected boolean isFinished()
	{
		if (Robot.hoodSubsystem.shooterHoodMotor.getOutputCurrent() >= 10)
		{
			Robot.hoodSubsystem.setHoodpower(0);
			Robot.hoodSubsystem.zeroHoodEncoders();
			return true;
		}
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
