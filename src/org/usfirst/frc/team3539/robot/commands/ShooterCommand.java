package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterCommand extends BulldogCommand
{

	public ShooterCommand()
	{
		super("ShooterCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{

	}

	@SuppressWarnings("deprecation")
	protected void execute()
	{

		Robot.shooter.setMotorPower(RobotMap.shootSpeed);
		//System.out.println(RobotMap.light.get());

		if(RobotMap.shooterRpm >= RobotMap.shootSpeed * 28333.33 - 2000)
		{

			Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
		}
		else
		{
			Robot.shooter.setAgitatorMotorPower(0);
		}

		Robot.shooter.setMotorPower(SmartDashboard.getDouble("Shooter Speed"));

		Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
		
		if (RobotMap.light.get() == false)

		{
			RobotMap.ballCount++;
		}//ballcount
	
		
	

		
			
		
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
