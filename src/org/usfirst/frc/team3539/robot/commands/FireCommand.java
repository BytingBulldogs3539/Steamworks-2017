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
		Robot.shooter.resetShooterPID();
		Robot.shooter.setShooterPID();
		Robot.shooter.startShooter(RobotMap.shooterRpm);
	}

	protected void execute()
	{
		Robot.shooter.startShooter(RobotMap.shooterRpm);
		
		if(RobotMap.triggerModified)
		{
			Robot.shooter.setAgitatorMotorPower(RobotMap.unjamAgitatorSpeed);
		}
		else if(Robot.shooter.getShooterRPM() <= RobotMap.shooterRpm)
		{
			Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
		}
	}

	protected boolean isFinished()
	{
		return !Robot.oi.shooterTrigger.getValue();
	}

	protected void end()
	{
		Robot.shooter.resetShooterPID();
		Robot.shooter.setAgitatorMotorPower(0);
		System.out.println("FireCommand ended");
	}

	protected void interrupted()
	{
		end();
	}
}
