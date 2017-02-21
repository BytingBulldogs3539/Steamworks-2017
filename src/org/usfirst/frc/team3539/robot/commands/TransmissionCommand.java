package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;


/**
 *
 */
public class TransmissionCommand extends BulldogCommand
{

	public TransmissionCommand()
	{
		super("TransmissionCommand");
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		System.out.println("Init TransmissionCommand");
	}

	protected void execute()
	{
		Robot.driveTrain.changeGears();
	}

	protected boolean isFinished()
	{
		return true;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		end();
	}
}
