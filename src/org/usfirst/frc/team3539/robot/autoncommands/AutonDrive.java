package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.utilities.BulldogPIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonDrive extends PIDCommand
{
	private double myTicks;
	private BulldogPIDOutput pidOutput = new BulldogPIDOutput();
	private PIDController anglePID;

	// private final PIDOutput angle_output = useAnglePIDOutput;
	/**
	 * A source which calls {@link PIDCommand#returnPIDInput()}.
	 */
	private final PIDSource angle_output_source = new PIDSource()
	{
		public void setPIDSourceType(PIDSourceType pidSource)
		{
		}

		public PIDSourceType getPIDSourceType()
		{
			return PIDSourceType.kDisplacement;
		}

		public double pidGet()
		{
			return Robot.driveTrain.getGyroAngle();
		}
	};

	public AutonDrive(double inches)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		myTicks = Robot.driveTrain.inchToEnc2(inches);
		requires(Robot.driveTrain);

		this.getPIDController().setOutputRange(-.7, .7);
		
		this.setTimeout(7);
	}

	public AutonDrive(double inches, double seconds)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		myTicks = Robot.driveTrain.inchToEnc2(inches);
		requires(Robot.driveTrain);

		this.getPIDController().setOutputRange(-.7, .7);
		
		this.setTimeout(seconds);
	}

	public double returnAnglePIDInput()
	{
		return Robot.raspberry.getAngle();
	}

	protected void initialize()
	{
		Robot.raspberry.UpdateCamera(0);
		anglePID = new PIDController(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee, angle_output_source,
				pidOutput);
		anglePID.setSetpoint(0);
		this.getPIDController().setAbsoluteTolerance(2);
		this.getPIDController().setToleranceBuffer(10);
		pidOutput.Reset();

		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		Robot.driveTrain.gyroReset();
		Robot.driveTrain.zeroEncoders();
		this.setSetpoint(myTicks);

		anglePID.enable();
	}

	protected void execute()
	{
		anglePID.setSetpoint(0);
	}

	protected boolean isFinished()
	{
		return (this.getPIDController().onTarget() || this.isTimedOut());
	}

	protected void end()
	{
		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.stopTrain();
		Robot.raspberry.UpdateCamera(0);
		anglePID.disable();
		pidOutput.Reset();
	}

	protected void interrupted()
	{
		System.out.println("AutonDrive interrupted");
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		return Robot.driveTrain.getBalancedEncoderPosition();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		Robot.driveTrain.driveArcade(output, pidOutput.Get());
	}
}
