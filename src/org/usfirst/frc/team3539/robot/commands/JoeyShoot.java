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
	private boolean agitatorSpecial = false;
	private boolean Pulse = false;
	private int PulseCounter;
	private int PulsesCounter;

	// Teleop Button Shooting
	public JoeyShoot(boolean visionTurn, Button button, double hoodAngle, double agitatorRpm, double shooterRpm)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);

		this.isTeleop = true;
		this.visionTurn = visionTurn;
		this.button = button;
		this.hoodAngle = hoodAngle;
		this.agitatorRpm = agitatorRpm;
		this.shooterRpm = shooterRpm;
		

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

		System.out.println("Supa man!");

		this.hoodAngle = 0;
		this.shooterRpm = 0;
		this.agitatorRpm = 400;
		this.visionDistance = true;
		this.isTeleop = true;
		this.visionTurn = true;
		this.button = superman;
	}

	// Auton Distance + Turn vision shooting
	public JoeyShoot(double seconds)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);

		System.out.println("Supa man!");

		this.hoodAngle = 0;
		this.shooterRpm = 0;
		this.agitatorRpm = 400;
		this.isTeleop = false;
		this.visionTurn = true;
		this.visionDistance = true;
		this.shootTime = seconds;

	}
	
	public JoeyShoot(double seconds, double agitatorSpeed)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);

		System.out.println("Supa man!");

		this.hoodAngle = 0;
		this.shooterRpm = 0;
		this.agitatorRpm = agitatorSpeed;
		this.isTeleop = false;
		this.visionTurn = true;
		this.visionDistance = true;
		this.shootTime = seconds;
		agitatorSpecial = true;
	}
	public JoeyShoot(double seconds, boolean Pulse)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);

		System.out.println("Supa man!");

		this.hoodAngle = 0;
		this.shooterRpm = 0;
		this.agitatorRpm = 400;
		this.isTeleop = false;
		this.visionTurn = true;
		this.visionDistance = true;
		this.shootTime = seconds;
		
	}

	// auton Shooting
	public JoeyShoot(boolean visionTurn, double hoodAngle, double agitatorRpm, double shooterRpm, double seconds)
	{
		super("JoeyShoot");
		requires(Robot.shooter);
		requires(Robot.hoodSubsystem);

		this.isTeleop = false;
		this.visionTurn = visionTurn;

		this.hoodAngle = hoodAngle;
		this.agitatorRpm = agitatorRpm;
		this.shooterRpm = shooterRpm;
		this.shootTime = seconds;
	}

	

	protected void initialize()
	{
		Robot.raspberry.setCamera(RobotMap.shooterCamera);
		Robot.shooter.resetShooterPID();
		Robot.shooter.resetAgitatorPID();

		Robot.shooter.setShooterPID();
		Robot.shooter.setAgitatorPID();
		
		breakoutCounter = 0;
		PulseCounter = 0;
		PulsesCounter = 0;

		
		if (visionTurn)
		{
			// Scheduler.getInstance().add(new AutoAim());
		}

		if (visionDistance)
		{
			this.hoodAngle = Robot.raspberry.getHoodAngle();
			
			
			if(agitatorSpecial)
			{
				this.shooterRpm = Robot.raspberry.getShooterRPM() - 40;
			}
			else
			{
				this.shooterRpm = Robot.raspberry.getShooterRPM();
			}
			
		}

		//this.hoodAngle = RobotMap.hoodTarget; // for Tuning
		// this.agitatorRpm = RobotMap.agitatorRpm; // for Tuning
		//this.shooterRpm = RobotMap.shooterRpm; // for Tuning
	}

	protected void execute()
	{
		Robot.hoodSubsystem.setAngle(hoodAngle);

		Robot.shooter.startShooter(shooterRpm);

		if (Pulse && PulseCounter > 150)  //&& Robotmap.triggerModified not sure if also needed)
		{
					Robot.shooter.startAgitator(-agitatorRpm);
					PulsesCounter++;
					if (PulsesCounter >25)// .5 seconds  to change time agitator reverses convert desired seconds into milliseconds and divide by 20 ;
					{
						PulseCounter = 0;
						PulsesCounter=0;
					}
		}
		else if (RobotMap.triggerModified)
		{
			Robot.shooter.startAgitator(agitatorRpm);
			
		}
		else if (Robot.shooter.getShooterRPM() <= shooterRpm * .9)
		{
			Robot.shooter.startAgitator(-agitatorRpm);
		}

		
		if (PulseCounter > 150)
{
			Robot.shooter.startAgitator(-agitatorRpm);

}
		breakoutCounter++;
		if(Pulse == true){
			PulseCounter++;
		}
		
	}

	protected boolean isFinished()
	{
		if (isTeleop)
		{
			return !button.get();
		}
		else if (breakoutCounter * 20 > shootTime * 1000)
			return true;
		else
			return false;
	}

	protected void end()
	{
		Robot.hoodSubsystem.disableHoodPid();
		Robot.shooter.resetShooterPID();
		Robot.shooter.resetAgitatorPID();
	}

	protected void interrupted()
	{
		Robot.shooter.disableShooterPID();
		Robot.shooter.disableAgitatorPID();

	}
}
