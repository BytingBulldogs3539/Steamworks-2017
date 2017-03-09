package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FireCommand extends BulldogCommand
{
	private boolean ballControl = false;

	public FireCommand()
	{
		super("FireCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		super.initialize("FireCommand");
		System.out.println("FireCommand init");
		Robot.shooter.resetShooterPID();
		Robot.shooter.setShooterPID();
		Robot.shooter.resetAgitatorPID();
		Robot.shooter.setAgitatorPID();
		Robot.shooter.startShooter(RobotMap.shooterRpm);
	}

	protected void execute()
	{
		
		Robot.shooter.startShooter(RobotMap.shooterRpm);
			

	if (RobotMap.triggerModified)
		{
			Robot.shooter.startAgitator(RobotMap.agitatorRpm);
		}
		else if (Robot.shooter.getShooterRPM() <= RobotMap.shooterRpm)
		{
			Robot.shooter.startAgitator(-RobotMap.agitatorRpm);
		}

		if (Robot.shooter.isBalling() && !ballControl)
		{
			ballControl = true;
			RobotMap.ballCount++;
		}
		else if (!Robot.shooter.isBalling() && ballControl)
		{
			ballControl = false;
		}
	}

	protected boolean isFinished()
	{
		return !Robot.oi.shooterTrigger.get();
	}

	protected void end()
	{
		super.end("FireCommand");
		ballControl = false;
		Robot.shooter.resetShooterPID();
		Robot.shooter.resetAgitatorPID();
		System.out.println("FireCommand ended");
	}

	protected void interrupted()
	{
		end();
	}
}
