package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.Raspberry;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonTurn extends PIDCommand
{
	private double newAngle;
	private double tolerance;

	public AutonTurn(double angle)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		newAngle = angle;
		requires(Robot.driveTrain);
		System.out.println("CON");
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Robot.driveTrain.gyroReset();
		Robot.driveTrain.zeroEncoders();

		this.setSetpoint(newAngle);
		
		if(tolerance > 1 || tolerance < 0)
		{
			tolerance = 0;
		}
		
		this.getPIDController().setOutputRange(-.6, .6); // newest .7 --- new .6 --- original -.5. .5
		this.getPIDController().setAbsoluteTolerance(3);
	}

	protected void execute()
	{
		//System.out.println("Turn On Target: " + this.getPIDController().onTarget());
	}

	protected boolean isFinished()
	{
		return this.getPIDController().onTarget();
	}

	protected void end()
	{
		//System.out.println("Ended Turn");
		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.stopTrain();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		//System.out.println("Gyro angle" + Robot.driveTrain.getGyroAngle());
		return Robot.driveTrain.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		//System.out.println("output " + output);
		Robot.driveTrain.turnLinear(output);
	}
}