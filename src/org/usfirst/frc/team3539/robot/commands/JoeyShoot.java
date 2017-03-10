package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class JoeyShoot extends Command
{

	boolean isTeleop;
	boolean vision;
	double hoodAngle;
	double agitatorRpm;
	double shooterRpm;
	Button button;

	public JoeyShoot(boolean vision, Button button, double hoodAngle, double agitatorRpm, double shooterRpm)
	{
		super("JoeyShoot");
		requires(Robot.shooter);

		this.isTeleop = true;
		this.vision = vision;
		this.button = button;
		this.hoodAngle = hoodAngle;
		this.agitatorRpm = agitatorRpm;
		this.shooterRpm = shooterRpm;
	}

	public JoeyShoot(boolean vision, double hoodAngle, double agitatorRpm, double shooterRpm)
	{
		super("JoeyShoot");
		requires(Robot.shooter);

		this.isTeleop = false;
		this.vision = vision;
		this.hoodAngle = hoodAngle;
		this.agitatorRpm = agitatorRpm;
		this.shooterRpm = shooterRpm;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (Robot.hoodSubsystem.getHoodPosition() > (hoodAngle + 7)
				|| Robot.hoodSubsystem.getHoodPosition() < (hoodAngle - 7))
		{
			if (Robot.hoodSubsystem.getHoodPosition() < hoodAngle)
			{
				Robot.hoodSubsystem.setMotorPower(.15);
			}
			else if (Robot.hoodSubsystem.getHoodPosition() > hoodAngle)
			{

				Robot.hoodSubsystem.setMotorPower(-.15);
			}
		}
		else
		{
			Robot.hoodSubsystem.setMotorPower(0);
		}

		Robot.shooter.startShooter(shooterRpm);

		if (RobotMap.triggerModified)

		{
			Robot.shooter.startAgitator(agitatorRpm);
		}
		else if (Robot.shooter.getShooterRPM() <= shooterRpm)
		{
			Robot.shooter.startAgitator(-agitatorRpm);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		if (isTeleop)
		{
			return !button.get();
		}
		else
		{
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
