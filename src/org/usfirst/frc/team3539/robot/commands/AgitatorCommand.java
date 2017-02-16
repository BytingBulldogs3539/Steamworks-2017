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
		requires(Robot.agitator);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.agitator.setMotorPower(RobotMap.agitatorSpeed);
		System.out.println("Agitator ran");
	}

	protected boolean isFinished()
	{
		return (!Robot.oi.onebuttona.get());
	
		//return (!Robot.oi.twotriggerl.get());
		
	}

	protected void end()
	{
		Robot.agitator.setMotorPower(0);
	}

	protected void interrupted()
	{
		end();
	}
}
