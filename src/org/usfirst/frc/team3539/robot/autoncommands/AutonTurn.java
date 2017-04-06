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
		Init(0, 3, isGearVision, button);
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
		
		if(Math.abs(angle) > 0 )
			this.getPIDController().setOutputRange(-1, 1);
		else
			this.getPIDController().setOutputRange(-1, 1);
			
		this.getPIDController().setAbsoluteTolerance(1);
		this.getPIDController().setToleranceBuffer(15);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		boolean buttonpressed = true;
		if (this.button != null)
		{
			buttonpressed = this.button.get();
		}
			
		if (this.getPIDController().onTarget() || this.isTimedOut()|| !buttonpressed)
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
		if (output > 0)
			output = (1-RobotMap.deadband) * output + RobotMap.deadband;

		if (output < 0)
			output = (1-RobotMap.deadband) * output - RobotMap.deadband;

		Robot.driveTrain.turnLinear(output);
		
	}
}
