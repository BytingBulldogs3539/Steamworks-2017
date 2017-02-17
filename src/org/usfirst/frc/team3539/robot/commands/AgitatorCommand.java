package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AgitatorCommand extends Command
{
	public AgitatorCommand()
	{
		super("AgitatorCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
		System.out.println("Agitator ran");
	}

	protected boolean isFinished()
	{
		return (!Robot.oi.shooterTrigger.triggerValue);
			
	}

	protected void end()
	{
		Robot.shooter.setMotorPower(0);
	}

	protected void interrupted()
	{
		end();
	}
}
