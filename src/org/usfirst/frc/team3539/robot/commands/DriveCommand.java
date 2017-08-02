package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

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
		//log
		//Rumble
		//		Robot.manipulator.rumble();


		//gear change
		if (Robot.oi.onebuttona.get() && !latch)
		{
			Robot.bl.logDebug("Gear Switch: true");
			Robot.bl.logDebug("Gear change to true");
			latch = true;
			Robot.driveTrain.changeGears();
		} else if (!Robot.oi.onebuttona.get() && latch)
		{
			
			latch = false;
			Robot.bl.logDebug("Gear Switch: false");
			Robot.bl.logDebug("gear change to false");
		}

		//drive

		Robot.driveTrain.DriveG(Robot.oi.controller1.getRawAxis(RobotMap.Y_AxisL), Robot.oi.controller1.getRawAxis(RobotMap.X_AxisR));
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
		end();
	}
}
