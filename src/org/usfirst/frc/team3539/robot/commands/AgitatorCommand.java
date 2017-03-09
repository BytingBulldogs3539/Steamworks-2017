
package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

/**
 *Runs the Agitator clockwise and stops on trigger depress
 */

public class AgitatorCommand extends BulldogCommand
{
	public AgitatorCommand()
	{
		super("AgitatorCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		super.initialize("AgitatorCommand");
	}

	protected void execute()
	{
		Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
	}

	protected boolean isFinished()
	{
		return(!Robot.oi.shooterTrigger.triggerValue);
	}

	protected void end()
	{
		Robot.shooter.setAgitatorMotorPower(0);
		super.end("AgitatorCommand");
	}

	protected void interrupted()
	{
		end();
	}
}
