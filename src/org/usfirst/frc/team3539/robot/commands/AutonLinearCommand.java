package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.utilities.KennyPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonLinearCommand extends BulldogCommand
{
	KennyPID linear;

	public AutonLinearCommand()
	{
		super("AutonLinearCommand");
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		linear = new KennyPID(0, 20000, 5000, 1000, 200, 10, true);
	}

	protected void execute()
	{
		Robot.driveTrain
				.driveLinear(linear.linearCalc(Robot.driveTrain.getLeftEncoderPosition(), Robot.driveTrain.getRightEncoderPosition()));
	}

	protected boolean isFinished()
	{
		return linear.isFinished();
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
	}
}
