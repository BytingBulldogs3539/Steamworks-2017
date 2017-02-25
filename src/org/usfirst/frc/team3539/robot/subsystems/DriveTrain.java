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

	public void talonControlVBus()
	{
		lfMotor.changeControlMode(TalonControlMode.PercentVbus);
		rfMotor.changeControlMode(TalonControlMode.PercentVbus);
		lbMotor.changeControlMode(TalonControlMode.PercentVbus);
		rbMotor.changeControlMode(TalonControlMode.PercentVbus);
	}

	public void driveArcade(double leftStick, double rightStick)
	{
		if(Robot.oi.invertTrigger.checkValue())
		{
			drive.arcadeDrive(-leftStick, rightStick);
		}
		else
		{
			drive.arcadeDrive(leftStick, rightStick);
		}
	}

	public void changeGears()
	{
		manipulatorStatus = !manipulatorStatus;

		if(manipulatorStatus == true)
		{
			manipulatorSol.set(DoubleSolenoid.Value.kForward);
		}
		if(manipulatorStatus == false)
		{
			manipulatorSol.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	// ticks per inch = 162.974661726
	public double inchToEnc(double inch)
	{
		return inch * 162.974661726;
	}
	
	// inches per tick = 0.00613592315 
	public double encToInch(double enc)
	{
		return enc * 0.00613592315;
	}

	public void gyroReset()
	{
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

		if(manipulatorStatus == true)
		{
			SmartDashboard.putString("Drive Gear", "High");
		}
		else
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

		SmartDashboard.putDouble("Left Front Motor", lfMotor.getBusVoltage());
		SmartDashboard.putDouble("Left Back Motor", lbMotor.getBusVoltage());
		SmartDashboard.putDouble("Right Back Motor", rfMotor.getBusVoltage());
		SmartDashboard.putDouble("Right Front Motor", rbMotor.getBusVoltage());

		RobotMap.turnPea = SmartDashboard.getDouble("RobotMap.turnPea");
		RobotMap.turnEye = SmartDashboard.getDouble("RobotMap.turnEye");
		RobotMap.turnDee = SmartDashboard.getDouble("RobotMap.turnDee");

	}

	@Override
	@SuppressWarnings("deprecation")
	public void SmartInit()
	{
		SmartDashboard.putString("Drive Gear", "--");

		SmartDashboard.putDouble("Gyro Velocity", 0);

		SmartDashboard.putDouble("Gyro Absolute Angle", 0);
		SmartDashboard.putDouble("Gryo Relative Angle", 0);

		SmartDashboard.putDouble("Left Encoder Value", 0);
		SmartDashboard.putDouble("Right Encoder Value", 0);

		SmartDashboard.putDouble("Left Encoder Value", 0);
		SmartDashboard.putDouble("Right Encoder Value", 0);

		SmartDashboard.putDouble("RobotMap.drivePea", RobotMap.drivePea);
		SmartDashboard.putDouble("RobotMap.driveEye", RobotMap.driveEye);
		SmartDashboard.putDouble("RobotMap.driveDee", RobotMap.driveDee);

		SmartDashboard.putDouble("Left Front Motor", 0);
		SmartDashboard.putDouble("Left Back Motor", 0);
		SmartDashboard.putDouble("Right Back Motor", 0);
		SmartDashboard.putDouble("Right Front Motor", 0);

		SmartDashboard.putDouble("RobotMap.turnPea", RobotMap.turnPea);
		SmartDashboard.putDouble("RobotMap.turnEye", RobotMap.turnEye);
		SmartDashboard.putDouble("RobotMap.turnDee", RobotMap.turnDee);
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