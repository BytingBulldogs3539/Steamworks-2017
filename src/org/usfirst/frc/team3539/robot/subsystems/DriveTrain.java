package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Kenny T.
 */
@SuppressWarnings("unused")
public class DriveTrain extends BulldogSystem
{
	public CANTalon lfMotor, lbMotor, rfMotor, rbMotor;

	public FeedbackDevice lEncoder;
	private CANTalon driveCan;

	private RobotDrive drive;
	private DoubleSolenoid manipulatorSol;
	private boolean manipulatorStatus;
	private ADXRS450_Gyro gyro;
	private int persistentTick, eGyro;
	private double distanceTraveled;

	public DriveTrain()
	{
		super("DriveTrain");

		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

		lfMotor = new CANTalon(RobotMap.lfMotorTalon);
		lbMotor = new CANTalon(RobotMap.lbMotorTalon);
		rfMotor = new CANTalon(RobotMap.rfMotorTalon);
		rbMotor = new CANTalon(RobotMap.rbMotorTalon);

		lfMotor.changeControlMode(TalonControlMode.PercentVbus);
		rfMotor.changeControlMode(TalonControlMode.PercentVbus);
		rbMotor.changeControlMode(TalonControlMode.PercentVbus);
		lbMotor.changeControlMode(TalonControlMode.PercentVbus);

		lfMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		rfMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		rbMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		lbMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);

		drive = new RobotDrive(lfMotor, lbMotor, rfMotor, rbMotor);
		drive.setSafetyEnabled(false);

		lfMotor.setSafetyEnabled(false);
		rfMotor.setSafetyEnabled(false);
		lbMotor.setSafetyEnabled(false);
		rbMotor.setSafetyEnabled(false);

		driveCan = new CANTalon(RobotMap.pcm);

		manipulatorSol = new DoubleSolenoid(RobotMap.pcm, RobotMap.driveSolOn, RobotMap.driveSolOff);

		manipulatorStatus = false;

		manipulatorSol.set(DoubleSolenoid.Value.kOff);

		this.zeroEncoders();

		persistentTick = 0;
		distanceTraveled = 0;
		eGyro = 0;
	}

	public void driveLinear(double speed)
	{
		if(speed > 0.8)
		{
			speed = 0.8;
		}
		drive.tankDrive(speed, speed);
	}

	public double getBalancedEncoderPosition()
	{
		return (lfMotor.getEncPosition() - rfMotor.getEncPosition()) / 2;
	}

	public void zeroEncoders()
	{
		lfMotor.setEncPosition(0);
		rfMotor.setEncPosition(0);
		rbMotor.setEncPosition(0);
		lbMotor.setEncPosition(0);

	}

	public void disablePIDControl()
	{
		lfMotor.disableControl();
		rfMotor.disableControl();
		lbMotor.disableControl();
		rbMotor.disableControl();

	}

	public void turnToAngle(double angle)
	{
	}

	public double getTotalDistance()
	{
		distanceTraveled = Math.abs((lfMotor.getEncPosition() + rfMotor.getEncPosition()) / 2);
		return distanceTraveled;
	}

	public void talonControlVBus()
	{
		lfMotor.changeControlMode(TalonControlMode.PercentVbus);
		rfMotor.changeControlMode(TalonControlMode.PercentVbus);
		lbMotor.changeControlMode(TalonControlMode.PercentVbus);
		rbMotor.changeControlMode(TalonControlMode.PercentVbus);

	}

	public void driveArcade(double leftStick, double rightStick)
	{
		if (Robot.oi.invertTrigger.checkValue())
		{
			drive.arcadeDrive(-leftStick, rightStick);
		} else
		{
			drive.arcadeDrive(leftStick, rightStick);
		}
	}

	public void changeGears()
	{
		manipulatorStatus = !manipulatorStatus;

		if (manipulatorStatus == true)
		{
			manipulatorSol.set(DoubleSolenoid.Value.kForward);
		}
		if (manipulatorStatus == false)
		{
			manipulatorSol.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void gyroReset()
	{
		eGyro = 0;
		gyro.reset();
		System.out.println("Gyro Zeroed");
	}

	public double getGyroRelative()
	{
		return gyro.getAngle() % 360;
	}

	public double getGyroAngle()
	{
		return gyro.getAngle();
	}

	@Override
	@SuppressWarnings("deprecation")
	public void Update()
	{

		if (manipulatorStatus == true)
		{
			SmartDashboard.putString("Drive Gear", "High");
		} else
		{
			SmartDashboard.putString("Drive Gear", "Low");
		}

		SmartDashboard.putDouble("Gyro Velocity", gyro.getRate());

		SmartDashboard.putDouble("Gyro Absolute Angle", getGyroAngle());
		SmartDashboard.putDouble("Gryo Relative Angle", getGyroRelative());

		SmartDashboard.putDouble("Left Encoder Value", lfMotor.getEncPosition());
		SmartDashboard.putDouble("Right Encoder Value", rfMotor.getEncPosition());

		RobotMap.drivePea = SmartDashboard.getDouble("RobotMap.drivePea");
		RobotMap.driveEye = SmartDashboard.getDouble("RobotMap.driveEye");
		RobotMap.driveDee = SmartDashboard.getDouble("RobotMap.driveDee");

	}

	@Override
	@SuppressWarnings("deprecation")
	public void SmartInit()
	{
		System.out.println("SmartInit Drive Train");
		SmartDashboard.putString("Drive Gear", "--");

		SmartDashboard.putDouble("Left Encoder Value", 0);
		SmartDashboard.putDouble("Right Encoder Value", 0);

		SmartDashboard.putDouble("RobotMap.drivePea", RobotMap.drivePea);
		SmartDashboard.putDouble("RobotMap.driveEye", RobotMap.driveEye);
		SmartDashboard.putDouble("RobotMap.driveDee", RobotMap.driveDee);

		SmartDashboard.putDouble("Left Front Motor", RobotMap.lfMotorTalon);
		SmartDashboard.putDouble("Left Back Motor", RobotMap.lbMotorTalon);
		SmartDashboard.putDouble("Right Back Motor", RobotMap.rbMotorTalon);
		SmartDashboard.putDouble("Right Front Motor", RobotMap.rfMotorTalon);

		SmartDashboard.putDouble("RobotMap.turnPea", RobotMap.turnPea);
		SmartDashboard.putDouble("RobotMap.turnEye", RobotMap.turnEye);
		SmartDashboard.putDouble("RobotMap.turnDee", RobotMap.turnDee);

		RobotMap.drivePea = SmartDashboard.getDouble("RobotMap.drivePea");
		RobotMap.driveEye = SmartDashboard.getDouble("RobotMap.driveEye");
		RobotMap.driveDee = SmartDashboard.getDouble("RobotMap.driveDee");

		RobotMap.turnPea = SmartDashboard.getDouble("RobotMap.turnPea");
		RobotMap.turnEye = SmartDashboard.getDouble("RobotMap.turnEye");
		RobotMap.turnDee = SmartDashboard.getDouble("RobotMap.turnDee");
	}

	public void initDefaultCommand()
	{
	}

	public void stopDrive()
	{
		drive.arcadeDrive(0, 0);
	}
}