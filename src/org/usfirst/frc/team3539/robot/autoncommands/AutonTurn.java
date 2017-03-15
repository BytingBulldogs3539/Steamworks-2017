package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonTurn extends PIDCommand
{
	private double angle;
	private boolean isVision;

	public AutonTurn(double angle)
	{
		super("test", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		this.angle = angle;
		this.isVision = false;
		requires(Robot.driveTrain);
	}

	public AutonTurn()
    {
        super("test", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
        requires(Robot.driveTrain);
        this.isVision = true;
    }
	
	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
		Robot.driveTrain.gyroReset();
		Robot.driveTrain.zeroEncoders();

		this.setSetpoint(angle);
		
		this.getPIDController().setOutputRange(-.8, .8); // newest .7 --- new .6 --- original -.5. .5
		this.getPIDController().setAbsoluteTolerance(3);
		this.getPIDController().setToleranceBuffer(20);
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return this.getPIDController().onTarget();
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
	    if(isVision)
	    {
	        return Robot.raspberry.getAngle();
	    }
	    else
	    {
	        return Robot.driveTrain.getGyroAngle();
	    }
	}

	@Override
	protected void usePIDOutput(double output)
	{
	    if(isVision)
	    {
	        Robot.driveTrain.turnLinear(-output);
	    }
	    else
	    {
	        Robot.driveTrain.turnLinear(output);
	    }
	}
}
