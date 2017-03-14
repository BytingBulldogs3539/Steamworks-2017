
package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3539.robot.subsystems.HoodSubsystem;

/**
 *
 */
public class HoodManual extends Command
{

	public HoodManual()
	{

	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.hoodSubsystem.disableHoodPid();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{

		Robot.hoodSubsystem.setHoodpower(.25);
		if(Robot.oi.controller2.getRawAxis(RobotMap.Y_AxisL) > 0)
		{
			Robot.hoodSubsystem.setHoodpower(-.1);
		}
		if(Robot.oi.controller2.getRawAxis(RobotMap.Y_AxisL) < 0)
		{
			Robot.hoodSubsystem.setHoodpower(.1);
		}
		if(Robot.oi.controller2.getRawAxis(RobotMap.Y_AxisL) == 0)
		{
			Robot.hoodSubsystem.setHoodpower(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		if(Robot.hoodSubsystem.getHoodPosition() == 0)
		{
			
			
			
			Robot.hoodSubsystem.setHoodpower(0);
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{Robot.hoodSubsystem.enable();
Robot.hoodSubsystem.setHoodpower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}