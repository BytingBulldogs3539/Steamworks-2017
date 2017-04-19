package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TriangleTurn extends PIDCommand
{
	
	private double finalX;
	private double finalY;
	private double totalX;
	private double totalY;
	double encPos = 0.0;
	private double currentAngle;
	
	private double angle;
	private boolean isGearVision;
	private Button button;

	public TriangleTurn(double x, double y)
	{
		super("AutonTurn", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Init(angle, 5, false, null);
		this.finalX = x;
		this.finalY = y;
	}

	public TriangleTurn(double angle, double seconds, boolean isGearVision)
	{
		super("AutonTurn", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Init(angle, seconds, isGearVision, null);
	}

	public TriangleTurn(boolean isGearVision, Button button)
	{
		super("AutonTurn", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Init(0, 3, isGearVision, button);
	}

	public double calcAngle()
	{
		return (Math.atan((finalX - totalX) / (finalY - totalY))) * (180 / Math.PI);
	}
	
	private void Init(double angle, double seconds, boolean isGearVision, Button button)
	{
		this.angle = angle;
		requires(Robot.driveTrain);
		this.isGearVision = isGearVision;
		this.button = button;

		if (seconds > 0)
			this.setTimeout(seconds);
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Robot.driveTrain.gyroReset();
		
		this.totalX = 0;
		this.totalY = 0;
		this.encPos = Robot.driveTrain.getBalancedEncoderPosition();
		
		this.setSetpoint(calcAngle());
		
		this.getPIDController().setOutputRange(-1, 1);
			
		this.getPIDController().setAbsoluteTolerance(1);
		this.getPIDController().setToleranceBuffer(15);
		//Robot.driveTrain.zeroEncoders();
	}

	protected void execute()
	{
		
		double distance = Robot.driveTrain.encToInch(Robot.driveTrain.getBalancedEncoderPosition() - encPos);
		SmartDashboard.putDouble("Encoder Position triangle", Robot.driveTrain.encToInch(Robot.driveTrain.getBalancedEncoderPosition()));
		System.out.println("distance: " + distance);
		this.encPos = Robot.driveTrain.getBalancedEncoderPosition();
		System.out.println("new enc pos: " + encPos);
		this.totalX += distance * Math.sin(Robot.driveTrain.getGyroAngle() * (Math.PI / 180));
		this.totalY += distance * Math.cos((Robot.driveTrain.getGyroAngle() * (Math.PI / 180)));
		System.out.println(totalX + " x,   " + totalY + " y");
		this.setSetpoint(calcAngle());
		System.out.println("new angle: " + calcAngle());
		SmartDashboard.putDouble("Triangle X", totalX);
		SmartDashboard.putDouble("Triangle Y", totalY);
	}

	protected boolean isFinished()
	{
		if(Math.abs(totalX) >= Math.abs(finalX) && Math.abs(totalY) >= Math.abs(finalY))
		{
			return true;
		}
		return false;
	}

	protected void end()
	{
		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.stopTrain();
		Robot.driveTrain.gyroReset();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		return Robot.driveTrain.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		if (output > 0)
			output = (1-RobotMap.deadband) * output + RobotMap.deadband;

		if (output < 0)
			output = (1-RobotMap.deadband) * output - RobotMap.deadband;

		Robot.driveTrain.driveArcade(.7, output);
		
	}
}
