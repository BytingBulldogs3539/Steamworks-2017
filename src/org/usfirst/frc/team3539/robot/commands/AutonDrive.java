package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonDrive extends PIDCommand
{
	private double myTicks;
	
	public AutonDrive(double ticks)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		myTicks = ticks;
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		Robot.driveTrain.zeroItOut();
		this.setSetpoint(myTicks);

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
