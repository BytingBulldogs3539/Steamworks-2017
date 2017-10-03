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

		if (Robot.oi.onebumperr.get())
		{
			Robot.driveTrain.driveArcade(-Robot.oi.controller1.getRawAxis(RobotMap.LEFT_TRIGGER), Robot.oi.controller1.getRawAxis(RobotMap.RIGHT_TRIGGER));
		}
		else
		{
			Robot.driveTrain.driveArcade(Robot.oi.controller1.getRawAxis(RobotMap.LEFT_TRIGGER), Robot.oi.controller1.getRawAxis(RobotMap.RIGHT_TRIGGER));
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
