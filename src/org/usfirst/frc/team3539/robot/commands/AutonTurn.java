package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonTurn extends PIDCommand
{
	private double newAngle;

	public AutonTurn(double angle)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		newAngle = angle;
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);

		Robot.driveTrain.gyroReset();

		this.setSetpoint(newAngle);

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
		Robot.driveTrain.stopDrive();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		//return Robot.driveTrain.getBalancedEncoderPosition();

		return Robot.driveTrain.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		Robot.driveTrain.turnLinear(output);
	}
}
