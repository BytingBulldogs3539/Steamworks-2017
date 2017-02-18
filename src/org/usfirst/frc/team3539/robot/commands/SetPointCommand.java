package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetPointCommand extends Command
{

	public SetPointCommand()
	{
		super("SetPointCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		
	}

	protected void execute()
	{
		Robot.shooter.setServoAngle(Robot.oi.controller2.getRawAxis(RobotMap.Y_AxisL));
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		end();
	}
}
