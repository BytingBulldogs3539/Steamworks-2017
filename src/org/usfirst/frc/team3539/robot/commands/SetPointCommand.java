package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;


/**
 *
 */
public class SetPointCommand extends PIDCommand
{
	public int setpoint;
	public SetPointCommand(int mysetpoint)
	{
		super("SetPointCommand",.0001,0,0);
		requires(Robot.shooter);
		setpoint = mysetpoint;
	}

	protected void initialize()
	{
		this.setSetpoint(setpoint);
		
		
	}

	protected void execute()
	{
	//	Robot.shooter.setHoodAngle(Robot.oi.controller2.getRawAxis(RobotMap.Y_AxisL));
		
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
		RobotMap.shootSpeed = .6;
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		// TODO Auto-generated method stub
		return Robot.shooter.GetPosition();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		// TODO Auto-generated method stub
		Robot.shooter.setHoodAngle(output/3);
	}
}
