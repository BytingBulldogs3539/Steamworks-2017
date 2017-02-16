package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TransmissionCommand extends Command
{

	public TransmissionCommand()
	{
		super("TransmissionCommand");
		requires(Robot.tankDriveTrain);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.tankDriveTrain.changeGears();
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
