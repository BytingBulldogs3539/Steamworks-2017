package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.commands.DriveCommand;

import com.ctre.CANTalon;
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

	private CANTalon driveCan;

	private RobotDrive drive;
	private DoubleSolenoid manipulatorSol;
	private boolean manipulatorStatus;
	private ADXRS450_Gyro gyro;

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
		// rbMotor.changeControlMode(TalonControlMode.PercentVbus);
		// lbMotor.changeControlMode(TalonControlMode.PercentVbus);

		rbMotor.changeControlMode(TalonControlMode.Follower);
		lbMotor.changeControlMode(TalonControlMode.Follower);

		rbMotor.set(rfMotor.getDeviceID());
		lbMotor.set(lfMotor.getDeviceID());

		lfMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		rfMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		// rbMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		// lbMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);

		lfMotor.configNominalOutputVoltage(0.0f, -0.0f);
		lfMotor.configPeakOutputVoltage(12.0f, -12.0f);

		rfMotor.configNominalOutputVoltage(0.0f, -0.0f);
		rfMotor.configPeakOutputVoltage(12.0f, -12.0f);

		lfMotor.configMaxOutputVoltage(12);
		rfMotor.configMaxOutputVoltage(12);
		rfMotor.setCurrentLimit(40);
		lfMotor.setCurrentLimit(40);

		lfMotor.EnableCurrentLimit(true);
		rfMotor.EnableCurrentLimit(true);

		lfMotor.enableBrakeMode(true);
		rfMotor.enableBrakeMode(true);

		drive = new RobotDrive(lfMotor, rfMotor);
		// drive = new RobotDrive(lfMotor, lbMotor, rfMotor, rbMotor);
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
	}

	public void driveLinear(double speed)
	{
		drive.tankDrive(speed, speed);
	}

	public void turnLinear(double speed)
	{
		drive.tankDrive(-speed, speed);
	
				
	}

	public double getBalancedEncoderPosition()
	{
		return (lfMotor.getEncPosition() - rfMotor.getEncPosition()) / 2;
	}

	public void zeroEncoders()
	{
		// lfMotor.setEncPosition(0);
		// rfMotor.setEncPosition(0);
		// rbMotor.setEncPosition(0);
		// lbMotor.setEncPosition(0);

		lfMotor.setPosition(0);
		rfMotor.setPosition(0);
		System.out.println("Zero DriveTrain");
		// rbMotor.setPosition(0);
		// lbMotor.setPosition(0);
	}

	public void disablePIDControl()
	{
		lfMotor.disableControl();
		rfMotor.disableControl();
		// lbMotor.disableControl();
		// rbMotor.disableControl();
	}

	public void talonControlVBus()
	{
		lfMotor.changeControlMode(TalonControlMode.PercentVbus);
		rfMotor.changeControlMode(TalonControlMode.PercentVbus);
		// lbMotor.changeControlMode(TalonControlMode.PercentVbus);
		// rbMotor.changeControlMode(TalonControlMode.PercentVbus);
	}

	public void driveArcade(double leftStick, double rightStick)
	{
		drive.arcadeDrive(leftStick, rightStick);
		//System.out.println(rightStick);
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

	@Deprecated
	public double inchToEnc(double inch)
	{
		return inch * (4096 / (Math.PI * RobotMap.wheelDiameter));
	}

	public double inchToEnc2(double inch)
	{
		double output = inch * (4096 / (Math.PI * RobotMap.wheelDiameter) * RobotMap.driveMultiplier);
		
		System.out.println("Target Encoders for Drive: " + output);
				
		return output;
	}

	public double encToInch(double enc)
	{
		return enc * ((Math.PI * RobotMap.wheelDiameter) / (4096 * RobotMap.driveMultiplier));
	}

	public void gyroReset()
	{
		gyro.reset();
		System.out.println("Gyro Zeroed");
		System.out.println(gyro.getAngle()+" Gyro");
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
		}
		else
		{
			SmartDashboard.putString("Drive Gear", "Low");
		}

		SmartDashboard.putDouble("Gyro Velocity", gyro.getRate());

		SmartDashboard.putDouble("Gryo Angle", getGyroAngle());
		
		 SmartDashboard.putDouble("--- Right Front", // from here
		 rfMotor.getEncPosition());
		 SmartDashboard.putDouble("--- Left Front", lfMotor.getEncPosition());
		 SmartDashboard.putDouble("--- Right Back",
		 rbMotor.getOutputCurrent());
		 SmartDashboard.putDouble("--- Left Back",
		 lbMotor.getOutputCurrent()); //to here

		RobotMap.drivePea = SmartDashboard.getDouble("RobotMap.drivePea");
		RobotMap.driveEye = SmartDashboard.getDouble("RobotMap.driveEye");
		RobotMap.driveDee = SmartDashboard.getDouble("RobotMap.driveDee");

		RobotMap.turnPea = SmartDashboard.getDouble("RobotMap.turnPea");
		RobotMap.turnEye = SmartDashboard.getDouble("RobotMap.turnEye");
		RobotMap.turnDee = SmartDashboard.getDouble("RobotMap.turnDee");

		RobotMap.deadband = SmartDashboard.getDouble("Drive Deadband");

		
	}

	@Override
	@SuppressWarnings("deprecation")
	public void SmartInit()
	{
		SmartDashboard.putString("Drive Gear", "--");

		SmartDashboard.putDouble("Gyro Velocity", 0);

		SmartDashboard.putDouble("Gryo Angle", 0);
		
		 SmartDashboard.putDouble("--- Right Front",
		 rfMotor.getOutputCurrent());
		 SmartDashboard.putDouble("--- Left Front",
		 lfMotor.getOutputCurrent());
		 SmartDashboard.putDouble("--- Right Back",
		 rbMotor.getOutputCurrent());
		 SmartDashboard.putDouble("--- Left Back",
		 lbMotor.getOutputCurrent());

		SmartDashboard.putDouble("RobotMap.drivePea", RobotMap.drivePea);
		SmartDashboard.putDouble("RobotMap.driveEye", RobotMap.driveEye);
		SmartDashboard.putDouble("RobotMap.driveDee", RobotMap.driveDee);

		SmartDashboard.putDouble("RobotMap.turnPea", RobotMap.turnPea);
		SmartDashboard.putDouble("RobotMap.turnEye", RobotMap.turnEye);
		SmartDashboard.putDouble("RobotMap.turnDee", RobotMap.turnDee);
		SmartDashboard.putDouble("Drive Deadband", RobotMap.deadband);
	}

	public void defaultSetter()
	{

	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new DriveCommand());
	}

	public void stopTrain()
	{
		drive.arcadeDrive(0, 0);
	}
}