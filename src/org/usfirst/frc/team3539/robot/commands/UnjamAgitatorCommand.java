package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UnjamAgitatorCommand extends Command
{

	public UnjamAgitatorCommand()
	{
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
		return(!Robot.oi.twotriggerl.get());
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
