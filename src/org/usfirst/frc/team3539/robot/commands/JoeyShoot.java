package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class JoeyShoot extends Command
{
	private boolean isTeleop;
	private boolean visionTurn;
	private boolean visionDistance;
	private double hoodAngle;
	private double agitatorRpm;
	private double shooterRpm;
	private Button button;
	private int breakoutCounter;
	private double shootTime;
	private boolean isIntaking = false;
	private boolean agitatorSpecial = false;

	// Teleop Button Shooting
	public JoeyShoot(boolean visionTurn, Button button, double hoodAngle, double agitatorRpm, double shooterRpm)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);
		requires(Robot.intake);

		this.isTeleop = true;
		this.visionTurn = visionTurn;
		this.button = button;
		this.hoodAngle = hoodAngle;
		this.agitatorRpm = agitatorRpm;
		this.shooterRpm = shooterRpm;
		this.isIntaking = false;
		this.agitatorSpecial = false;
		// this.hoodAngle = RobotMap.hoodTarget; // for Tuning
		// this.agitatorRpm = RobotMap.agitatorRpm;
		// this.shooterRpm = RobotMap.shooterRpm;
	}

	// superman button
	public JoeyShoot(Button superman)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);
		requires(Robot.intake);

		this.hoodAngle = 0;
		this.shooterRpm = 0;
		this.agitatorRpm = 400;
		this.visionDistance = true;
		this.isTeleop = true;
		this.visionTurn = true;
		this.button = superman;
		this.isIntaking = false;
		this.agitatorSpecial = false;
	}

	// Auton Distance + Turn vision shooting
	public JoeyShoot(double seconds)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);
		requires(Robot.intake);

		this.hoodAngle = 0;
		this.shooterRpm = 0;
		this.agitatorRpm = 400;
		this.isTeleop = false;
		this.visionTurn = true;
		this.visionDistance = true;
		this.shootTime = seconds;
		this.isIntaking = true;
		this.agitatorSpecial = true;
	}

	public JoeyShoot(double seconds, double agitatorSpeed)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);
		requires(Robot.intake);

		this.hoodAngle = 0;
		this.shooterRpm = 0;
		this.agitatorRpm = agitatorSpeed;
		this.isTeleop = false;
		this.visionTurn = true;
		this.visionDistance = true;
		this.shootTime = seconds;
		this.isIntaking = false;
	}

	// auton Shooting
	public JoeyShoot(boolean visionTurn, double hoodAngle, double agitatorRpm, double shooterRpm, double seconds)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);
		requires(Robot.intake);

		this.isTeleop = false;
		this.visionTurn = visionTurn;

		this.hoodAngle = hoodAngle;
		this.agitatorRpm = agitatorRpm;
		this.shooterRpm = shooterRpm;
		this.shootTime = seconds;
		this.isIntaking = false;
	}

	protected void initialize()
	{
		Robot.raspberry.setCamera(RobotMap.shooterCamera);
		// Robot.shooter.resetShooterPID();
		Robot.shooter.resetAgitatorPID();

		Robot.shooter.setShooterPID();
		Robot.shooter.setAgitatorPID();

		breakoutCounter = 0;

		if(visionTurn)
		{
			// Scheduler.getInstance().add(new AutoAim());
		}

		if(visionDistance)
		{
			this.hoodAngle = Robot.raspberry.getHoodAngle();

			if(agitatorSpecial)
			{
				this.shooterRpm = Robot.raspberry.getShooterRPM() - 50;
			}
			else
			{
				this.shooterRpm = Robot.raspberry.getShooterRPM();
			}
			
			if(isIntaking)
				Robot.intake.setMotorPower(1);

		}

		// this.hoodAngle = RobotMap.hoodTarget; // for Tuning
		// this.agitatorRpm = RobotMap.agitatorRpm; // for Tuning
		// this.shooterRpm = RobotMap.shooterRpm; // for Tuning
	}

	protected void execute()
	{
		Robot.hoodSubsystem.setAngle(hoodAngle);

		Robot.shooter.startShooter(shooterRpm);

		if(RobotMap.triggerModified)
		{
			Robot.shooter.startAgitator(agitatorRpm);
		}
		else if(Robot.shooter.getShooterRPM() <= shooterRpm * .9)
		{
			Robot.shooter.startAgitator(-agitatorRpm);
			if (isIntaking)
			{
				Robot.intake.setMotorPower(1); // test
				
			}
		}

		breakoutCounter++;
	}

	protected boolean isFinished()
	{
		if(isTeleop)
		{
			return !button.get();
		}
		else if(breakoutCounter * 20 > shootTime * 1000)
			return true;
		else
			return false;
	}

	protected void end()
	{
		Robot.hoodSubsystem.disableHoodPid();
		Robot.shooter.resetShooterPID();
		Robot.shooter.resetAgitatorPID();
		if(isIntaking)
			Robot.intake.setMotorPower(0);
	}

	protected void interrupted()
	{
		Robot.shooter.disableShooterPID();
		Robot.shooter.disableAgitatorPID();
		end();
	}
}
