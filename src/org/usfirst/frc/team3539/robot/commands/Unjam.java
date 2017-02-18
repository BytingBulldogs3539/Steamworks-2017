package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Unjam extends Command {

	public Unjam() 
	{
	}

	// Called just before this Command runs the first time
	protected void initialize() 
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.shooter.setAgitatorMotorPower(RobotMap.unjamSpeed);
		System.out.println("Unjam ran");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		return (!Robot.oi.twotriggerl.get());
	}

	// Called once after isFinished returns true
	protected void end() 
	{
		Robot.shooter.setAgitatorMotorPower(0);
	}
}
