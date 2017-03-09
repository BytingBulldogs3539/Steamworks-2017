package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

/**
 *
 */
public class IntakeCommand extends BulldogCommand
{
	public IntakeCommand()
	{
		super("IntakeCommand");
		requires(Robot.intake);
	}

	protected void initialize()
	{
		super.initialize("IntakeCommand");
		Robot.intake.lockOff();
	}

	protected void execute()
	{
		if(RobotMap.triggerModified)
		{
			Robot.intake.setMotorPower(RobotMap.unjamIntakeSpeed);
			Robot.intake.lockOff();
		}
		else
		{
			Robot.intake.setMotorPower(RobotMap.intakeSpeed);
		}
	}

	protected boolean isFinished()
	{
		return !Robot.oi.intakeTrigger.get();
	}

	protected void end()
	{
		super.end("IntakeCommand");
		Robot.intake.setMotorPower(0);
	}

	protected void interrupted()
	{
		end();
	}
}
