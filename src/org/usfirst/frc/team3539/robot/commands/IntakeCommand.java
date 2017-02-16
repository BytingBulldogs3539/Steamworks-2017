package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.OI;
import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command
{
	public IntakeCommand()
	{
		super("IntakeCommand");
		System.out.println("IntakeCommand constructor");
		requires(Robot.intake);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		System.out.println("beforeSetMotor in intakeCommand");
		Robot.intake.setMotorPower(1);
		System.out.println("ran intakecommand execute");
	}

	protected boolean isFinished()
	{
		//return !Robot.oi.intakeTrigger.triggerValue;
		return !Robot.oi.intakeTrigger.getValue();
	}

	protected void end()
	{
		Robot.intake.setMotorPower(0);
		System.out.println("ended intakecommand");
	}

	protected void interrupted()
	{
		end();
	}
}
