package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class JoeyShoot extends Command
{

	private boolean isTeleop;
	private boolean vision;
	private double hoodAngle;
	private double agitatorRpm;
	private double shooterRpm;
	private Button button;

	public JoeyShoot(boolean vision, Button button, double hoodAngle, double agitatorRpm, double shooterRpm)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);

		this.isTeleop = true;
		this.vision = vision;
		this.button = button;
		this.hoodAngle = hoodAngle;
		this.agitatorRpm = agitatorRpm;
		this.shooterRpm = shooterRpm;
//
//		this.hoodAngle = RobotMap.hoodTarget; // for Tuning
//		this.agitatorRpm = RobotMap.agitatorRpm;
//		this.shooterRpm = RobotMap.shooterRpm;
	}

	public JoeyShoot(boolean vision, double hoodAngle, double agitatorRpm, double shooterRpm)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);

		this.isTeleop = false;
		this.vision = vision;
		this.hoodAngle = hoodAngle;
		this.agitatorRpm = agitatorRpm;
		this.shooterRpm = shooterRpm;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.shooter.resetShooterPID();
		Robot.shooter.resetAgitatorPID();

		Robot.shooter.setShooterPID();
		Robot.shooter.setAgitatorPID();
//
//		this.hoodAngle = RobotMap.hoodTarget; // for Tuning
//		this.agitatorRpm = RobotMap.agitatorRpm;
//		this.shooterRpm = RobotMap.shooterRpm;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.hoodSubsystem.setAngle(hoodAngle);
		
		Robot.shooter.startShooter(shooterRpm);

		if (RobotMap.triggerModified)

		{
			Robot.shooter.startAgitator(agitatorRpm);
		}
		else if (Robot.shooter.getShooterRPM() <= shooterRpm * .9)
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
		Robot.hoodSubsystem.resetHoodPID();
		Robot.shooter.resetShooterPID();
		Robot.shooter.resetAgitatorPID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
