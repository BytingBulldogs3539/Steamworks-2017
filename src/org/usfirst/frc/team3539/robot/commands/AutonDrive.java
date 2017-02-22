package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonDrive extends PIDCommand
{
	private double myTicks;
	private int confidence;

	public AutonDrive(double ticks)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		System.out.println("Create");
		myTicks = ticks;
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		System.out.println("Init");
		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		System.out.println("P: " + RobotMap.drivePea);

		Robot.driveTrain.zeroEncoders();
		this.setSetpoint(myTicks);
		confidence = 0;

	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		if (Math.abs(myTicks - Robot.driveTrain.getBalancedEncoderPosition()) < 50)
		{
			confidence++;
			if (confidence > 20)
			{
				//System.out.println("Building confidence: " + confidence + " / 20");
				return true;
			}
			return false;
		} 
		else
		{
			//System.out.println("Confidence zero");
			confidence = 0;
			return false;
		}
	}

	protected void end()
	{
		Robot.driveTrain.stopDrive();
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

		if (output > 1)
			out = 1;
		if (output < -1)
			out = -1;

		Robot.driveTrain.driveLinear(out);
	}
}
