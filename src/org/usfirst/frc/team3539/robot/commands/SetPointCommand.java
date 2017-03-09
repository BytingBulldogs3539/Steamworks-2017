//package org.usfirst.frc.team3539.robot.commands;
//
//import org.usfirst.frc.team3539.robot.Robot;
//import org.usfirst.frc.team3539.robot.RobotMap;
//
//import edu.wpi.first.wpilibj.command.PIDCommand;
//
//
///**
// *
// */
//public class SetPointCommand extends PIDCommand
//{
//	public double setpoint;
//	public SetPointCommand(double mysetpoint)
//	{
//		super("SetPointCommand", RobotMap.hoodPea, RobotMap.hoodEye, RobotMap.hoodDee); // p, i, d
//		requires(Robot.shooter);
//		setpoint = mysetpoint;
//	}
//
//	protected void initialize()
//	{
//		this.setSetpoint(setpoint);
//		System.out.println("It Initialized");
////	    Robot.driveTrain.zeroEncoders();
//		this.getPIDController().setAbsoluteTolerance(50);
//
//	}
//
//	protected void execute()
//	{
//		Robot.shooter.setHoodAngle(RobotMap.hoodTarget);
//	}
//
//	protected boolean isFinished()
//	{
//		return this.getPIDController().onTarget();
//	}
//
//	protected void end()
//	{
//		super.end();
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
//		// TODO Auto-generated method stub
//		return Robot.shooter.getHoodPosition();
//	}
//
//	@Override
//	protected void usePIDOutput(double output)
//	{
//		// TODO Auto-generated method stub
//		Robot.shooter.settempHoodAngle(output);
//	}
//}
