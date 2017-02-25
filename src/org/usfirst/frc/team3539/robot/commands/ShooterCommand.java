//package org.usfirst.frc.team3539.robot.commands;
//
//import org.usfirst.frc.team3539.robot.Robot;
//import org.usfirst.frc.team3539.robot.RobotMap;
//
//import edu.wpi.first.wpilibj.command.PIDCommand;
//
///**
// *
// */
//public class ShooterCommand extends PIDCommand
//{
//
//	private double rpm;
//	private double angle;
//
//	public ShooterCommand(double targetRPM, double hoodAngle)
//	{
//		super("ShooterCommand", RobotMap.shootPea, RobotMap.shootEye, RobotMap.shootDee);
//
//		requires(Robot.shooter);
//		rpm = targetRPM;
//		angle = hoodAngle;
//	}
//
//	protected void initialize()
//	{
//		this.getPIDController().setPID(RobotMap.shootPea, RobotMap.shootEye, RobotMap.shootDee);
//		this.getPIDController().setOutputRange(0, 1);
//		//this.setSetpoint(rpm);
//		
//		Robot.shooter.setAgitatorMotorPower(RobotMap.agitatorSpeed);
//	}
//
//	protected void execute()
//	{
//		//Robot.shooter.setMotorPower(RobotMap.shooterRpm);
//		if (RobotMap.triggerModified)
//		{
//			Robot.shooter.setAgitatorMotorPower(RobotMap.unjamAgitatorSpeed);
//		} else if (true) // is hood angle set?
//		{
//			Robot.shooter.readyShooter(20000, 100, -.6); //not real values
//			// Robot.shooter.countBall();
//		}
//	}
//
//	protected boolean isFinished()
//	{
//		return !Robot.oi.shooterTrigger.getValue();
//	}
//
//	protected void end()
//	{
//		Robot.shooter.setMotorPower(0);
//		Robot.shooter.setAgitatorMotorPower(0);
//	}
//
//	protected void interrupted()
//	{
//		end();
//	}
//
//	@Override
//	protected double returnPIDInput()
//	{
//		System.out.println("GetShooterVelocity " + Robot.shooter.getShooterRPM());
//		return Robot.shooter.getShooterRPM();
//	}
//
//	@Override
//	protected void usePIDOutput(double output)
//	{
//		System.out.println("ouput: " + output);
//		//Robot.shooter.setMotorPower(-output);
//
//	}
//}
