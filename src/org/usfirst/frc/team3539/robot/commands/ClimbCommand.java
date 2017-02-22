package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;



public class ClimbCommand extends BulldogCommand
{
	public ClimbCommand()
	{
		super("ClimbCommand");
		requires(Robot.intake);
	}

	protected void initialize()
	{
		Robot.intake.lockOn();
	}
	
	protected void execute()
	{
		Robot.intake.setMotorPower(RobotMap.climbSpeed);
	}

	protected boolean isFinished()
	{
		return !Robot.oi.twobumperr.get();
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
