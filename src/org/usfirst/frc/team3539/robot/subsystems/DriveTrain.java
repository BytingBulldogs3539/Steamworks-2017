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
*@author Kenny T.
*/
@SuppressWarnings("unused")
public class DriveTrain extends BulldogSystem
{
	private CANTalon lfMotor, lbMotor, rfMotor, rbMotor;

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

		lfMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		rfMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);

		lbMotor.changeControlMode(TalonControlMode.Follower);
		rbMotor.changeControlMode(TalonControlMode.Follower);
		lbMotor.set(RobotMap.lfMotorTalon);
		rbMotor.set(RobotMap.rfMotorTalon);

		drive = new RobotDrive(lfMotor, lbMotor, rfMotor, rbMotor);
		drive.setSafetyEnabled(false);
		
		driveCan = new CANTalon(RobotMap.pcm);

		manipulatorSol = new DoubleSolenoid(RobotMap.pcm, RobotMap.driveSolOn, RobotMap.driveSolOff);

		manipulatorStatus = false;

		manipulatorSol.set(DoubleSolenoid.Value.kOff);

		lfMotor.setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee, RobotMap.driveEff, RobotMap.driveEyeZone,
				RobotMap.driveLoopRamp, RobotMap.driveProfile);
		rfMotor.setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee, RobotMap.driveEff, RobotMap.driveEyeZone,
				RobotMap.driveLoopRamp, RobotMap.driveProfile);

		//lfMotor.reverseOutput(true);
		//lfMotor.reverseSensor(true);
		
		resetEncoder();
		
		lfMotor.setAllowableClosedLoopErr(RobotMap.driveLoopError);
		rfMotor.setAllowableClosedLoopErr(RobotMap.driveLoopError);
		
		
		persistentTick = 0;
		distanceTraveled = 0;
		eGyro = 0;
	}
	
	public void resetEncoder()
	{
		lfMotor.setEncPosition(0);
		rfMotor.setEncPosition(0);
	}

	public void driveXTicks(double ticks)
	{
		persistentTick += ticks;
		lfMotor.set(ticks);
		//rfMotor.set
	}

	public void enableControl()
	{
		lfMotor.enableControl();
		rfMotor.enableControl();
	}

	public void disableControl()
	{
		lfMotor.disableControl();
		rfMotor.disableControl();
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
	}

	public void talonControlPosition()
	{
		lfMotor.changeControlMode(TalonControlMode.Position);
		rfMotor.changeControlMode(TalonControlMode.Position);
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

	public void gyroReset()
	{
		eGyro = 0;
		gyro.reset();
		System.out.println("gyro was reset");
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
		
		System.out.println("Talon pos: " + lfMotor.getPosition());
	}

	@Override
	@SuppressWarnings("deprecation")
	public void SmartInit()
	{
		resetEncoder();
		
		SmartDashboard.putString("Drive Gear", "--");

		SmartDashboard.putDouble("Left Encoder Value", 0);
		SmartDashboard.putDouble("Right Encoder Value", 0);

		SmartDashboard.putDouble("RobotMap.drivePea", RobotMap.drivePea);
		SmartDashboard.putDouble("RobotMap.driveEye", RobotMap.driveEye);
		SmartDashboard.putDouble("RobotMap.driveDee", RobotMap.driveDee);
		SmartDashboard.putDouble("RobotMap.driveEff", RobotMap.driveEff);
		SmartDashboard.putInt("RobotMap.driveEyeZone", RobotMap.driveEyeZone);
		SmartDashboard.putDouble("RobotMap.driveLoopRamp", RobotMap.driveLoopRamp);
		SmartDashboard.putInt("RobotMap.driveProfile", RobotMap.driveProfile);
		SmartDashboard.putInt("driveLoopError", RobotMap.driveLoopError);

		RobotMap.drivePea = SmartDashboard.getDouble("RobotMap.drivePea");
		RobotMap.driveEye = SmartDashboard.getDouble("RobotMap.driveEye");
		RobotMap.driveDee = SmartDashboard.getDouble("RobotMap.driveDee");
		RobotMap.driveEff = SmartDashboard.getDouble("RobotMap.driveEff");
		RobotMap.driveEyeZone = SmartDashboard.getInt("RobotMap.driveEyeZone");
		RobotMap.driveLoopRamp = SmartDashboard.getDouble("RobotMap.driveLoopRamp");
		RobotMap.driveProfile = SmartDashboard.getInt("RobotMap.driveProfile");
		RobotMap.driveLoopError = SmartDashboard.getInt("driveLoopError");
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new DriveCommand());
	}
}