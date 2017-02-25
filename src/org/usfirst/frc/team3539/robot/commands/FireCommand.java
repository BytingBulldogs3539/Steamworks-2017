package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FireCommand extends Command
{

	public FireCommand()
	{
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		System.out.println("FireCommand init");
		Robot.shooter.initShooter();
		Robot.shooter.startShooter(RobotMap.shooterRpm);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return !Robot.oi.shooterTrigger.getValue();
	}

	protected void end()
	{
		Robot.shooter.initShooter();
		System.out.println("FireCommand ended");
	}

	protected void interrupted()
	{
		end();
	}
}
