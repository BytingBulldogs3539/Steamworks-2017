package autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.utilities.DpadButton;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class VisionTurn extends PIDCommand
{
	private double newAngle;

	public VisionTurn(double angle)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		newAngle = angle;
		requires(Robot.driveTrain);
		System.out.println("CON");
	}

	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.vturnPea, RobotMap.vturnEye, RobotMap.vturnDee);

		Robot.driveTrain.gyroReset();
		Robot.driveTrain.zeroEncoders();

		this.setSetpoint(0);
//HACK
this.setSetpoint(Robot.raspberry.getAngle());

		//System.out.println("Init Turn");
		
		this.getPIDController().setOutputRange(-.6, .6); // original -.5. .5
		this.getPIDController().setAbsoluteTolerance(.2);
	}

	protected void execute()
	{
		//System.out.println("Turn On Target: " + this.getPIDController().onTarget());
	}

	protected boolean isFinished()
	{
		return !Robot.oi.visionButton.checkValue();
	}

	protected void end()
	{
		//System.out.println("Ended Turn");
		Robot.driveTrain.stopTrain();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		//System.out.println("Gyro angle" + Robot.driveTrain.getGyroAngle());
		return Robot.driveTrain.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		//System.out.println("output " + output);
		Robot.driveTrain.turnLinear(output);
	}
}
