package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;


/**
 *
 */
public class AutonDriveForward extends BulldogCommand
{
	
	public AutonDriveForward()
	{
		super("AutonDriveForward");
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{			
			System.out.println("Execute");
			Robot.driveTrain.driveXTicks(1000);
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
		System.out.println("end");
		Robot.driveTrain.talonControlVBus();
	}

	protected void interrupted()
	{
		System.out.println("Interupted");
		end();
	}
}
