package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonTurn extends PIDCommand
{
	private double angle;
	private boolean isGearVision;
	private Button button;

	public AutonTurn(double angle)
	{
		super("AutonTurn", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Init(angle, 5, false, null);
	}

	public AutonTurn(double angle, double seconds)
	{
		super("AutonTurn", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Init(angle, seconds, false, null);
	}

	public AutonTurn(double angle, double seconds, boolean isGearVision)
	{
		super("AutonTurn", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Init(angle, seconds, isGearVision, null);
	}

	public AutonTurn(boolean isGearVision, Button button)
	{
		super("AutonTurn", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Init(0, -1, isGearVision, button);
	}

	private void Init(double angle, double seconds, boolean isGearVision, Button button)
	{
		this.angle = angle;
		requires(Robot.driveTrain);
		this.isGearVision = isGearVision;
		this.button = button;

		if (seconds > 0)
			this.setTimeout(seconds);
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Robot.driveTrain.gyroReset();

		if (angle == 0)
			this.setSetpoint(Robot.raspberry.getTurnAngle());
		else
			this.setSetpoint(angle);

		this.getPIDController().setOutputRange(-.85, .85); // newest .7 --- new
															// .6
															// --- original -.5.
															// .5
		this.getPIDController().setAbsoluteTolerance(1);
		this.getPIDController().setToleranceBuffer(10);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return this.getPIDController().onTarget() || this.isTimedOut() || !this.button.get();
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
