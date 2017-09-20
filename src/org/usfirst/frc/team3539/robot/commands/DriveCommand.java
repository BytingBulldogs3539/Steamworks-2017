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
		// log
		// Rumble
		// Robot.manipulator.rumble();

		// gear change
		if (Robot.oi.onebuttona.get() && !latch)
		{
		//	BulldogLogger.getInstance().logDebug("Gear Switch: true");
			//BulldogLogger.getInstance().logDebug("Gear change to true");
			latch = true;
			Robot.driveTrain.changeGears();
		}
		else if (!Robot.oi.onebuttona.get() && latch)
		{

			latch = false;
		//	BulldogLogger.getInstance().logDebug("Gear Switch: false");
		//	BulldogLogger.getInstance().logDebug("gear change to false");
		}

		// drive
		
		//dominik's rocket leauge control
		
	//	double one = Robot.oi.controller1.getRawAxis(RobotMap.RIGHT_TRIGGER)- Robot.oi.controller1.getRawAxis(RobotMap.LEFT_TRIGGER);
		
  //double two = Robot.oi.controller1.getRawAxis(RobotMap.X_AxisL);
		// normal speed control with joystick
		
		
		
		double one = Robot.oi.controller1.getRawAxis(RobotMap.Y_AxisL);
		double two = Robot.oi.controller1.getRawAxis(RobotMap.X_AxisR);
		double cap = .99;

		if (two < -cap)
		{
			two = -cap;
		}

		if (two > cap)
		{
			two = cap;
		}

		Robot.driveTrain.DriveG(one, two);
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
