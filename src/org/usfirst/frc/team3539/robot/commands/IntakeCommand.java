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
		Robot.intake.lockOff();
	}

	protected void execute()
	{
		Robot.intake.setMotorPower(RobotMap.intakeSpeed);
	}

	protected boolean isFinished()
	{
		return !Robot.oi.intakeTrigger.getValue();
	}

	protected void end()
	{
		Robot.intake.setMotorPower(0);
	}

	protected void interrupted()
	{
		end();
	}
}
