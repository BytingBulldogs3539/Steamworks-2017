package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonDriveTicks extends PIDCommand
{
	private double myTicks;

	public AutonDriveTicks(double ticks)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		myTicks = ticks;
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		Robot.driveTrain.zeroEncoders();
		this.setSetpoint(myTicks);
		this.getPIDController().setAbsoluteTolerance(500);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return this.getPIDController().onTarget();
	}

	protected void end()
	{
		Robot.driveTrain.stopTrain();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		return Robot.driveTrain.getBalancedEncoderPosition();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		double out = output;

		if(output > 1)
			out = 1;
		if(output < -1)
			out = -1;

		Robot.driveTrain.driveLinear(out);
	}
}
