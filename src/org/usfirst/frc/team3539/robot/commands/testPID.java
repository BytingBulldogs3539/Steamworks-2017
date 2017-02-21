package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class testPID extends PIDCommand
{

	public testPID()
	{
		super("test", .0003, .000001, 0.0001);
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		Robot.driveTrain.zeroItOut();
		this.setSetpoint(10000);

	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return false;
		// return Math.abs(10000- Robot.driveTrain.lfMotor.getEncPosition() ) >
		// 10;
	}

	protected void end()
	{
		Robot.driveTrain.testDrive(0);

	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		return Robot.driveTrain.lfMotor.getEncPosition();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		double out = output;

		if (output > 1)
			out = 1;
		if (output < -1)
			out = -1;

		Robot.driveTrain.testDrive(out);
	}
}
