package org.usfirst.frc.team3539.robot.CurvedAutons;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.utilities.BulldogPIDFeedback;
import org.usfirst.frc.team3539.robot.utilities.BulldogPIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class SuperDriveAuton extends PIDCommand {
	private double myTicks;

	private double turnSpeed = 0;
	private double turnAngle = 0;
	private Boolean useVision = false;
	private double driveLimits = .85;
	private double defaultTime = 7;
	private double DriveDistance = Robot.driveTrain.inchToEnc2(70);

	private PIDController anglePID;
	private BulldogPIDOutput anglePID_output = new BulldogPIDOutput();
	private BulldogPIDFeedback anglePID_feedback = new BulldogPIDFeedback();

	public SuperDriveAuton(double inches) {
		super("SuperDrive", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.AutonInit(inches, 0, 0, false, defaultTime);
	}
	public SuperDriveAuton(double inches, double seconds) {
		super("SuperDrive", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.AutonInit(inches, 0, 0, false, seconds);
	}

	public SuperDriveAuton(double inches, double turnSpeed, double turnAngle, Boolean useVision) {
		super("SuperDrive", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.AutonInit(inches, turnSpeed, turnAngle, useVision, defaultTime);
	}

	public SuperDriveAuton(double inches, double turnSpeed, double turnAngle, Boolean useVision, double seconds) {
		super("SuperDrive", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.AutonInit(inches, turnSpeed, turnAngle, useVision, seconds);
	}

	private void AutonInit(double inches, double turnSpeed, double turnAngle, Boolean useVision, double seconds) {
		requires(Robot.driveTrain);
		
		this.myTicks = Robot.driveTrain.inchToEnc2(inches);
		this.turnSpeed = turnSpeed;
		this.turnAngle = turnAngle;
		this.useVision = useVision;
		
		this.setTimeout(seconds);

		// 
	}

	protected void initialize() {
		
		Robot.raspberry.setCamera(RobotMap.gearCamera);
		
		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.gyroReset();	
		
		//Angle PID Settings
		anglePID = new PIDController(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee, anglePID_feedback, anglePID_output);
		anglePID.setAbsoluteTolerance(2);
		anglePID.setToleranceBuffer(10);
		
		anglePID_feedback.setVisionFeedback(false);
		anglePID_output.pidWrite(0);
		

		//Drive PID Settings
		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.getPIDController().setOutputRange(-driveLimits, driveLimits);
		this.getPIDController().setAbsoluteTolerance(2000);
		this.getPIDController().setToleranceBuffer(20);
		this.setSetpoint(myTicks);

	}

	protected void execute() {
		
		if (!useVision && anglePID.isEnabled() && anglePID.onTarget())
		{
			System.out.println("Disable Vision");
			// vision is done tracking
			Robot.driveTrain.gyroReset();
			anglePID_feedback.setVisionFeedback(false);
			anglePID.setSetpoint(0);
			anglePID.reset();
			anglePID_output.Reset();
			useVision = true;
		
		}
		if (DriveDistance < Robot.driveTrain.getBalancedEncoderPosition())
		{
			anglePID_output.pidWrite(turnSpeed);
			
		}
		
		if (!anglePID.isEnabled() && Math.abs(Robot.driveTrain.getGyroAngle()) > this.turnAngle *.9)
		{
			System.out.println("Reached Angle");
			Robot.driveTrain.gyroReset();
			anglePID_feedback.setVisionFeedback(false);
			
			if(Robot.raspberry.getTurnAngle() == 0 || !useVision)
			{
				anglePID.setSetpoint(0);
			} 
			else
			{
				anglePID.setSetpoint(Robot.raspberry.getTurnAngle());
				useVision = false;
			}
			
			anglePID.reset();
			anglePID.enable();
		}
	}

	protected boolean isFinished() {
		return (this.getPIDController().onTarget() || this.isTimedOut());
	}

	protected void end() {
		Robot.driveTrain.zeroEncoders();
		//Robot.driveTrain.stopTrain();
		anglePID_output.Reset();
		anglePID.disable();
	}

	protected void interrupted() {
		System.out.println("AutonDrive interrupted");
		end();
	}

	@Override
	protected double returnPIDInput() {
		return Robot.driveTrain.getBalancedEncoderPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (output > 0)
			output = (1 - RobotMap.deadband) * output + RobotMap.deadband;

		if (output < 0)
			output = (1 - RobotMap.deadband) * output - RobotMap.deadband;

		Robot.driveTrain.driveArcade(output, anglePID_output.Get());
	}
}
