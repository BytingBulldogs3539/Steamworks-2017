package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonDriveForward extends Command
{
	private boolean yes = true;
	
	public AutonDriveForward()
	{
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		if(yes)
		{
			yes = false;
			System.out.println("Execute Inner");
			Robot.driveTrain.talonControlPosition();
			Robot.driveTrain.enableControl();
			Robot.driveTrain.driveXTicks(50000);
		}
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
		System.out.println("end");
		Robot.driveTrain.talonControlVBus();
	}

	protected void interrupted()
	{
		end();
	}
}
