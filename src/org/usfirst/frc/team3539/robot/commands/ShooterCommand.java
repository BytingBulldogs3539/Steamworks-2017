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

<<<<<<< HEAD
		Robot.shooter.setMotorPower(RobotMap.shootSpeed);
		
		double desiredSpeed = Math.abs(RobotMap.shootSpeed*28333+2000);
		if(RobotMap.shooterRpm >= desiredSpeed)
=======
		if(RobotMap.triggerModified)
>>>>>>> origin/master
		{
			Robot.shooter.setAgitatorMotorPower(RobotMap.unjamAgitatorSpeed);
		}
		else if(true) //is hood angle set?
		{
			Robot.shooter.readyShooter(30000, 100); //not real values
			Robot.shooter.countBall();
		}
<<<<<<< HEAD
		System.out.println(desiredSpeed);

		Robot.shooter.setMotorPower(SmartDashboard.getDouble("Shooter Speed"));
		
	
	if (RobotMap.light.get() == false)

		    {
			RobotMap.ballCount++;
			}
		}		
	

		
			
		
	

	protected boolean isFinished()
	{
		//return !Robot.oi.shooterTrigger.getValue();
		return !Robot.oi.twobuttonx.get();
=======
	}

	protected boolean isFinished()
	{
		return !Robot.oi.shooterTrigger.getValue();
>>>>>>> origin/master
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
