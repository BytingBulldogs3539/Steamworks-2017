package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	@SuppressWarnings("deprecation")
	protected void execute()
	{
		
		Robot.shooter.setMotorPower(RobotMap.shootSpeed);
		System.out.println(Robot.shooter.getlight());
		if (RobotMap.shooterRpm >= SmartDashboard.getDouble("Select Agitator Activate Speed"))
		{
			Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
		}
		else
		{
			Robot.shooter.setAgitatorMotorPower(0);
		}
		/*if (Robot.shooter.getlight() == 1)
		{
			Robot.shooter.ballCount();
			System.out.println("dominik" + RobotMap.ballCount);
		} else
		{
			System.out.println("dominik" + RobotMap.ballCount);
		}
		*/

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
