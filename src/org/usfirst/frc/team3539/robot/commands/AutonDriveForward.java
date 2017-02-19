package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonDriveForward extends Command
{
	private int myTicks;
	
	public AutonDriveForward(int ticks)
	{
		requires(Robot.driveTrain);
		myTicks = ticks;
	}

	protected void initialize()
	{
		Robot.driveTrain.driveXTicks(myTicks);
	}

	protected void execute()
	{
		System.out.println("Execute");
	}

	protected boolean isFinished()
	{
		return true;
	}

	protected void end()
	{
		System.out.println("end");
	}

	protected void interrupted()
	{
		end();
	}
}
