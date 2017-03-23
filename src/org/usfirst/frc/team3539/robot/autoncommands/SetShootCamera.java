package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShootCamera extends Command
{

	public SetShootCamera()
	{
		requires(Robot.raspberry);
	}

	protected void initialize()
	{
		Robot.raspberry.setCamera(RobotMap.shooterCamera);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return true;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		System.out.println("Camera Switch Interrupted");
		end();
	}
}
