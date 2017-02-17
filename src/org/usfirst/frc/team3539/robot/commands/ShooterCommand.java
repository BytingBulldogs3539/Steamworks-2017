package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command
{

	public ShooterCommand()
	{
		super("ShooterCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.shooter.setMotorPower(RobotMap.shootSpeed);
		Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
	}

	protected boolean isFinished()
	{
		System.out.println(Robot.oi.shooterTrigger.getValue());
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