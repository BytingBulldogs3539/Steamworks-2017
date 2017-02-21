package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UnjamIntakeCommand extends BulldogCommand
{

	public UnjamIntakeCommand()
	{
		super("UnjamIntakeCommand");
		requires(Robot.intake);
	}

	protected void initialize()
	{
		Robot.intake.lockOff();
	}

	protected void execute()
	{
		Robot.intake.setMotorPower(RobotMap.unjamIntakeSpeed);
	}

	protected boolean isFinished()
	{
		return(!Robot.oi.twobumperl.get());
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
