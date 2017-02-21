package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UnjamAgitatorCommand extends BulldogCommand
{

	public UnjamAgitatorCommand()
	{
		super("UnjamAgitatorCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.shooter.setAgitatorMotorPower(RobotMap.unjamAgitatorSpeed);
	}

	protected boolean isFinished()
	{
		return(!Robot.oi.twobumperr.get());
	}

	protected void end()
	{
		Robot.shooter.setAgitatorMotorPower(0);
	}

	protected void interrupted()
	{
		end();
	}
}
