package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

/**
 *
 */
public class ShooterCommand extends BulldogCommand
{

	public ShooterCommand(double targetRPM, double hoodAngle)
	{
		super("ShooterCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		//set hood angle command?
	}

	protected void execute()
	{

		if(RobotMap.triggerModified)
		{
			Robot.shooter.setAgitatorMotorPower(RobotMap.unjamAgitatorSpeed);
		}
		else if(true) //is hood angle set?
		{
			Robot.shooter.readyShooter(30000, 100); //not real values
			Robot.shooter.countBall();
		}
	}

	protected boolean isFinished()
	{
		return !Robot.oi.shooterTrigger.getValue();
	}

	protected void end()
	{
		Robot.shooter.setMotorPower(0);
		Robot.shooter.setAgitatorMotorPower(0);
	}

	protected void interrupted()
	{
		end();
	}
}
