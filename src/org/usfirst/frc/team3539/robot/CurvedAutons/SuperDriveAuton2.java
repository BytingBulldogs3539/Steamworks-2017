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
public class SuperDriveAuton2 extends PIDCommand {
	private double myTicks;

	private double turnSpeed = 0;
	private double turnAngle = 0;
	private Boolean useVision = false;
	private Boolean latchOnVision = false;
	
	private double defaultTime = 7;

	private PIDController anglePID;
	private BulldogPIDOutput anglePID_output = new BulldogPIDOutput();
	private BulldogPIDFeedback anglePID_feedback = new BulldogPIDFeedback();

	public SuperDriveAuton2(double inches) {
		super("SuperDrive", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.AutonInit(inches, 0, 0, false, defaultTime);
	}
	public SuperDriveAuton2(double inches, double seconds) {
		super("SuperDrive", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.AutonInit(inches, 0, 0, false, seconds);
	}

	public SuperDriveAuton2(double inches, double turnSpeed, double turnAngle, Boolean useVision) {
		super("SuperDrive", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.AutonInit(inches, turnSpeed, turnAngle, useVision, defaultTime);
	}

	public SuperDriveAuton2(double inches, double turnSpeed, double turnAngle, Boolean useVision, double seconds) {
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

		// this.getPIDController().setOutputRange(-.85, .85);
	}

	protected void initialize() {
		
		latchOnVision = false;
		Robot.driveTrain.gyroReset();
		
		anglePID = new PIDController(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee, anglePID_feedback, anglePID_output);
		

		anglePID.setAbsoluteTolerance(2);
		anglePID.setToleranceBuffer(5);
		anglePID.setOutputRange(-1, 1);
		anglePID_feedback.setVisionFeedback(false);
		if(useVision)
		{
			anglePID.setSetpoint(0);
		}
		else
		{
			anglePID.setSetpoint(turnAngle);			
		}
		
		anglePID_output.Reset();

		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		this.getPIDController().setAbsoluteTolerance(2000);
		this.getPIDController().setToleranceBuffer(20);

		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.gyroReset();

		this.setSetpoint(myTicks);
	}

	protected void execute() {
		// Steer by Sight?
		if (turnSpeed != 0) {
			if ( Robot.driveTrain.getGyroAngle() < -55) {
				//Robot.driveTrain.gyroReset();
				//anglePID.setSetpoint(Robot.raspberry.getTurnAngle());
				//anglePID.enable();
				turnSpeed=0;
				anglePID_output.pidWrite(0);
				latchOnVision = true;
				System.out.println("angle pid enabled");
			} else {
				anglePID_output.pidWrite(this.turnSpeed);
			}

		}
		if (anglePID.isEnabled() && (anglePID.onTarget())) {
			anglePID.disable();
			anglePID_output.pidWrite(0);
			System.out.println("angle pid disablwe");
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
