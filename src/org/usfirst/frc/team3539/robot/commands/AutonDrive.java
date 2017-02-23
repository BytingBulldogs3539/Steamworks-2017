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
		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		//SmartDashboard.putData(this.getPIDController().getTable());
		Robot.driveTrain.zeroEncoders();
		this.setSetpoint(myTicks);

		//possible methods

		//this.getPIDController().updateTable();
		//this.getPIDController().setOutputRange(-1, 1);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		if(Math.abs(myTicks - Robot.driveTrain.getBalancedEncoderPosition()) < 50)
		{
			return true;
		}
		else
		{
			return false;
		}
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
