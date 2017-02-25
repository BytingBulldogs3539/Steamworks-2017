package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class VisionTrackCommand extends PIDCommand
{
	public VisionTrackCommand()
	{
		super("test", .1, 0, 0);
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		System.out.println("Init Track");
		this.getPIDController().setPID(.1, 0, 0);

		this.setSetpoint(Robot.raspberry.Read()); //possibly needs to be called multiple times

		this.getPIDController().setOutputRange(-1, 1);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
		System.out.println("Ended Track");
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		double read = Robot.raspberry.Read();
		System.out.println("input: " + read);
		return read;
	}

	@Override
	protected void usePIDOutput(double output)
	{
		System.out.println("output: " + output);
		Robot.driveTrain.turnLinear(output);
	}
}
