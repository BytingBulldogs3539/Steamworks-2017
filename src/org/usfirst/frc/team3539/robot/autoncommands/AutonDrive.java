package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonDrive extends PIDCommand
{
	private double myTicks;
	
	public AutonDrive(double inches, double speedlimit)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		Robot.driveTrain.zeroEncoders();
		myTicks = Robot.driveTrain.inchToEnc(inches);
		requires(Robot.driveTrain);
		
		if(speedlimit > 1 || speedlimit < 0)
		{
			speedlimit = 0;
		}
		
		this.getPIDController().setOutputRange(-speedlimit, speedlimit);
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
//		System.out.println("Drive On Target: " + this.getPIDController().onTarget());
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
		Robot.driveTrain.driveLinear(output);
	}
}
