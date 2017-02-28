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
		Robot.driveTrain.zeroEncoders();

		this.setSetpoint(newAngle);


		System.out.println("Init Turn");
		
		this.getPIDController().setOutputRange(-.5, .5);
		this.getPIDController().setAbsoluteTolerance(5);
	}

	protected void execute()
	{
		System.out.println("Turn On Target: " + this.getPIDController().onTarget());
	}

	protected boolean isFinished()
	{
		return this.getPIDController().onTarget();
	}

	protected void end()
	{
		System.out.println("Ended Turn");
		Robot.driveTrain.stopTrain();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		System.out.println("Gyro angle" + Robot.driveTrain.getGyroAngle());
		return Robot.driveTrain.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		System.out.println("output " + output);
		Robot.driveTrain.turnLinear(output);
	}
}
