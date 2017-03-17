package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutoAim extends PIDCommand
{
	public AutoAim()
	{
		super("AutoAim", RobotMap.vturnPea, RobotMap.vturnEye, RobotMap.vturnDee);
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.vturnPea, RobotMap.vturnEye, RobotMap.vturnDee);

		this.setSetpoint(0);
		
		this.getPIDController().setOutputRange(-.5, .5); //-.5,.5
		this.getPIDController().setAbsoluteTolerance(.2);
		Robot.raspberry.UpdateCamera(1);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		//return false;
		return (this.getPIDController().onTarget() || !Robot.oi.visionButton.get());
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
		double read = Robot.raspberry.getAngle();
		return read;
	}

	@Override
	protected void usePIDOutput(double output)
	{
		Robot.driveTrain.turnLinear(-output);
	}
}
