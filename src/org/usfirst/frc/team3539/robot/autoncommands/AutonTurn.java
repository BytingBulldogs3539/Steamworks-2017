package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonTurn extends PIDCommand
{
	private double angle;

	public AutonTurn(double angle)
	{
		super("test", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		this.angle = angle;
		requires(Robot.driveTrain);
		
		this.setTimeout(5);
	}
	
	public AutonTurn(double angle, double seconds)
	{
		super("test", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		this.angle = angle;
		requires(Robot.driveTrain);
		
		this.setTimeout(seconds);
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Robot.driveTrain.gyroReset();

		this.setSetpoint(angle);

		this.getPIDController().setOutputRange(-.8, .8); // newest .7 --- new .6
															// --- original -.5.
															// .5
		this.getPIDController().setAbsoluteTolerance(3);
		this.getPIDController().setToleranceBuffer(10);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return (this.getPIDController().onTarget() || this.isTimedOut());
	}

	protected void end()
	{
		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.stopTrain();
		Robot.driveTrain.gyroReset();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		return Robot.driveTrain.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		Robot.driveTrain.turnLinear(output);
	}
}
