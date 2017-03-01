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

	public AutonDrive(double inches)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		inches = Robot.driveTrain.subtractRobotInches(inches);
		myTicks = Robot.driveTrain.inchToEnc(inches);
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		Robot.driveTrain.zeroEncoders();
		this.setSetpoint(myTicks);
		this.getPIDController().setAbsoluteTolerance(2000);
	}

	protected void execute()
	{
		System.out.println("Drive On Target: " + this.getPIDController().onTarget());
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
		System.out.println("Interrupted");
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
