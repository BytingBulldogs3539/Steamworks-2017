package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.commands.TankDriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("unused")
public class DriveTrain extends BulldogSystem
{
	private CANTalon lfMotor;
	private CANTalon lbMotor;
	private CANTalon rfMotor;
	private CANTalon rbMotor;
	
	private CANTalon driveCan;
	
	private RobotDrive drive;
	
	private DoubleSolenoid manipulatorSol;
	
	private boolean manipulatorStatus;
	
	private ADXRS450_Gyro gyro;

	public DriveTrain()
	{
		super("DriveTrain");
		// setSetpoint() - Sets where the PID controller should move the system
		// enable() - Enables the PID controller.

		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

		lfMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		lbMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		
		drive = new RobotDrive(lfMotor, lbMotor, rfMotor, rbMotor);
		driveCan = new CANTalon(RobotMap.pcm);

		manipulatorSol = new DoubleSolenoid(RobotMap.pcm, RobotMap.driveSolOn, RobotMap.driveSolOff);
		
		manipulatorStatus = false;
		
		manipulatorSol.set(DoubleSolenoid.Value.kOff);
	}

	public void driveXticks(double ticks)
	{
		// enable();
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

		if (manipulatorStatus == true)
		{
			manipulatorSol.set(DoubleSolenoid.Value.kForward);
		}
		if (manipulatorStatus == false)
		{
			manipulatorSol.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public void driveForward(double power, double ticks)
	{
		lfMotor.setEncPosition(0);
		rfMotor.setEncPosition(0);
		if (lfMotor.getEncPosition() <= ticks && rfMotor.getEncPosition() >= -ticks)
		{
			lfMotor.set(power);
			lbMotor.set(power);
			rfMotor.set(-power);
			rbMotor.set(-power);
		} else // This entire method does not take PID into account
		{
			lfMotor.set(0);
			lbMotor.set(0);
			rfMotor.set(0);
			rbMotor.set(0);
		}
	}

	public void turn(double power, double ticks, String direction)
	{
		lfMotor.setEncPosition(0);
		rfMotor.setEncPosition(0);
		if (direction == "r")
		{
			if (lfMotor.getEncPosition() >= ticks && rfMotor.getEncPosition() >= ticks)
			{
				lfMotor.set(power);
				lbMotor.set(power);
				rfMotor.set(power);
				rbMotor.set(power);
			}
		}
		else if (direction == "l")
		{
			if (lfMotor.getEncPosition() <= -ticks && rfMotor.getEncPosition() <= -ticks)
			{
				lfMotor.set(-power);
				lbMotor.set(-power);
				rfMotor.set(-power);
				rbMotor.set(-power);
			}
		}
	}
	
	public void gyroReset()
	{
		gyro.reset();
		System.out.println("gyro was reset");
	}
	
	public double gyroRelative()
	{
		double relativeAngle = gyro.getAngle() / 360.0;
		
		return relativeAngle * 360.0;
	}
	
	@SuppressWarnings("deprecation")
	public void Update()
	{
		
		SmartDashboard.putString("Drive Gear", "--");
		if (manipulatorStatus == true)
		{
			SmartDashboard.putString("Drive Gear", "High");
		}
		else
		{
			SmartDashboard.putString("Drive Gear", "Low");
		}
		
		SmartDashboard.putDouble("GyroVelocity", gyro.getRate());
		
		SmartDashboard.putDouble("GryoAngle", gyroRelative());
	}
	
	private int getAverageTicks()
	{
		return 0;
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new TankDriveCommand());
	}
	
	protected double returnPIDInput()
	{
		// Return your input value for the PID loop
		return getAverageTicks();
	}

	protected void usePIDOutput(double output)
	{
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	}
}