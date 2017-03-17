package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonDriveGentle extends PIDCommand
{
	private double myTicks;
	private double speedLimit;
	
	public AutonDriveGentle(double inches, double speedLimit)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		Robot.driveTrain.zeroEncoders();
		myTicks = Robot.driveTrain.inchToEnc2(inches);
		requires(Robot.driveTrain);
		
		this.speedLimit = speedLimit;
		
		this.getPIDController().setOutputRange(-.7, .7);
		setSetpoint(myTicks);
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
	}

	protected boolean isFinished()
	{
		return this.getPIDController().onTarget();
	}

	protected void end()
	{
		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.stopTrain();
	}

	protected void interrupted()
	{
		System.out.println("AutonDrive interrupted");
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
		if(output > speedLimit)
		{
			output = speedLimit;
		}
		else if(output < -speedLimit)
		{
			output = -speedLimit;
		}
		Robot.driveTrain.driveLinear(output);
	}
}
