package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TestMaxVel extends BulldogCommand
{

	public TestMaxVel()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("TestMaxVel");
		requires(Robot.hoodSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double angle = SmartDashboard.getDouble("hoodTarget");
		Robot.hoodSubsystem.setAngle(angle);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return true;
	}

	// Called once after isFinished returns true
	{

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
