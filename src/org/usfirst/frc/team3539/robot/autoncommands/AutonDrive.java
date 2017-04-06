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

	private Boolean useVision = false;
	
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
		this.useVision = false;
		//this.getPIDController().setOutputRange(-.85, .85);//
		
		this.setTimeout(7);
	}

	public AutonDrive(double inches, Boolean useVision)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		myTicks = Robot.driveTrain.inchToEnc2(inches);
		requires(Robot.driveTrain);
		this.useVision = useVision;
		//this.getPIDController().setOutputRange(-.85, .85);//
		
		this.setTimeout(7);
	}
	
	public AutonDrive(double inches, double seconds)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		myTicks = Robot.driveTrain.inchToEnc2(inches);
		requires(Robot.driveTrain);
		this.useVision = false;
		//this.getPIDController().setOutputRange(-.85, .85);
		
		this.setTimeout(seconds);
	}

	public double returnAnglePIDInput()
	{
		return Robot.raspberry.getTurnAngle();
	}

	protected void initialize()
	{
		anglePID = new PIDController(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee, angle_output_source,
				pidOutput);
		if(useVision)
			anglePID.setSetpoint(Robot.raspberry.getTurnAngle());
		else
			anglePID.setSetpoint(0);

		anglePID.setAbsoluteTolerance(2);
		anglePID.setToleranceBuffer(10);
		
		this.getPIDController().setAbsoluteTolerance(2000);
		this.getPIDController().setToleranceBuffer(20);
		pidOutput.Reset();

		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		Robot.driveTrain.gyroReset();
		Robot.driveTrain.zeroEncoders();
		this.setSetpoint(myTicks);

		anglePID.enable();
	}

	protected void execute()
	{
		//anglePID.setSetpoint(0);
		//if(anglePID.onTarget())
		//	anglePID.disable();
	}

	protected boolean isFinished()
	{
		return (this.getPIDController().onTarget() || this.isTimedOut());
	}

	protected void end()
	{
		Robot.driveTrain.zeroEncoders();
		//Robot.driveTrain.stopTrain();
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
		if (output > 0)
			output = (1-RobotMap.deadband) * output + RobotMap.deadband;

		if (output < 0)
			output = (1-RobotMap.deadband) * output - RobotMap.deadband;
		
		Robot.driveTrain.driveArcade(output, pidOutput.Get());
	}
}
