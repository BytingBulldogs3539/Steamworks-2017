package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AlphaTurn extends Command
{
	private double seconds;
	private double breakoutCounter;
	private double speed;

	public AlphaTurn(double speed, double seconds)
	{
		this.speed = speed;
		this.breakoutCounter = 0;

		if(seconds > 0)
			this.seconds = seconds;
	}

	protected void initialize()
	{
		Robot.driveTrain.gyroReset();
	}

	protected void execute()
	{
		Robot.driveTrain.turnLinear(speed);

		breakoutCounter++;
	}

	protected boolean isFinished()
	{
		if(breakoutCounter * 20 > seconds * 1000)
			return true;
		else
			return false;
	}

	protected void end()
	{
		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.stopTrain();
		Robot.driveTrain.gyroReset();
	}

	protected void interrupted()
	{
		end();
	}
}
