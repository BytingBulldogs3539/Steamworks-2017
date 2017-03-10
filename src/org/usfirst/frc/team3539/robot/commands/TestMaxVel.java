package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		super.initialize("TestMaxVel");
		Robot.shooter.setMotorPower(-1);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.shooter.setMotorPower(-1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		System.out.println("ended max velocity");
		super.end("TestMaxVel");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
