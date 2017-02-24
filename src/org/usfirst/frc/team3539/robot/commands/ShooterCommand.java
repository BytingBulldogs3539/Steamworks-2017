package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class ShooterCommand extends PIDCommand
{

	private double rpm;
	private double angle;
	
	public ShooterCommand(double targetRPM, double hoodAngle)
	{
		super("ShooterCommand",1,0,0);
		
		requires(Robot.shooter);
		rpm = targetRPM;
		angle = hoodAngle;
	}

	protected void initialize()
	{
		this.getPIDController().setOutputRange(0, 1);
		this.setSetpoint(rpm);
		Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
	}

	protected void execute()
	{
		if(RobotMap.triggerModified)
		{
			Robot.shooter.setAgitatorMotorPower(RobotMap.unjamAgitatorSpeed);
		}
		else if(true) //is hood angle set?
		{
			
			//Robot.shooter.readyShooter(20000, 100, -.6); //not real values
		//	Robot.shooter.countBall();
		}
	}

	protected boolean isFinished()
	{
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

	@Override
	protected double returnPIDInput()
	{
		// TODO Auto-generated method stub
		return Robot.shooter.GetShooterVelocity();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		// TODO Auto-generated method stub
		Robot.shooter.setMotorPower(output);
		
	}
}
