package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClimbCommand extends Command
{
	public ClimbCommand()
	{
		requires(Robot.intake);
	}

	protected void initialize()
	{
		Robot.intake.lockOn();
	}

	@SuppressWarnings("deprecation")
	protected void execute()
	{
		//Robot.intake.setMotorPower(SmartDashboard.getDouble("climbSpeed", 1));
		Robot.intake.setMotorPower(RobotMap.climbSpeed);

	}

	protected boolean isFinished()
	{
		return !Robot.oi.twobumperr.get();
	}

	protected void end()
	{
		Robot.intake.setMotorPower(0);
	}

	protected void interrupted()
	{
		end();
	}
}
