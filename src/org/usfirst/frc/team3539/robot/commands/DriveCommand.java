package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.utilities.BulldogCommand;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

/**
 *
 */
public class DriveCommand extends BulldogCommand
{
	private boolean latch = false;

	public DriveCommand()
	{
		super("DriveCommand");
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		super.initialize("DriveCommand");
		Robot.driveTrain.talonControlVBus();
	}

	protected void execute()
	{
		if (Robot.oi.onebuttona.get() && !latch)
		{
		
			latch = true;
			Robot.driveTrain.changeGears();
		}
		else if (!Robot.oi.onebuttona.get() && latch)
		{

			latch = false;
	
		}
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
		super.end("DriveCommand");
	}

	protected void interrupted()
	{
		super.interrupted();
	}
}
