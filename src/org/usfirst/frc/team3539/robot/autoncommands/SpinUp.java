package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class SpinUp extends Command
{
	private double seconds = 0;
	private double shooterRpm = 0;
	
	public SpinUp(double seconds, double shooterRpm)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		this.seconds = seconds;
		this.shooterRpm = -1 * shooterRpm;
	}

	protected void initialize()
	{
		Robot.shooter.resetShooterPID();
		Robot.shooter.setShooterPID();
		
		Robot.shooter.enableCurrentLimit();
		
		if (seconds > 0)
			this.setTimeout(seconds);
	}

	protected void execute()
	{
		Robot.shooter.startShooter(shooterRpm);
	}

	protected boolean isFinished()
	{
		return isTimedOut();
	}

	protected void end()
	{
		Robot.shooter.disableCurrentLimit();
	}

	protected void interrupted()
	{
		end();
	}
}
