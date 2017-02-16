package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveCommand extends Command
{
	public TankDriveCommand()
	{
		super("TankDriveCommand");
		requires(Robot.tankDriveTrain);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.tankDriveTrain.driveArcade(Robot.oi.controller1.getRawAxis(RobotMap.Y_AxisL),
				Robot.oi.controller1.getRawAxis(RobotMap.X_AxisR));
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		end();
	}
}
