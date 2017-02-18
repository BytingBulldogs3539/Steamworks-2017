package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.commands.TankDriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */

@SuppressWarnings("unused")
public class DriveTrain extends PIDSubsystem
{
	private CANTalon lfMotor;
	private CANTalon lbMotor;
	private CANTalon rfMotor;
	private CANTalon rbMotor;
	
	private CANTalon driveCan;
	
	private Encoder lEncoder;
	private Encoder rEncoder;
	
	private RobotDrive drive;
	
	private DoubleSolenoid sol;
	
	private boolean solieStatus;
	
	private ADXRS450_Gyro gyro;

	public DriveTrain()
	{
		super("DriverTrain", 0, 0, 0);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		// setSetpoint(0);

		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

		lfMotor = new CANTalon(RobotMap.lfMotorTalon);
		lbMotor = new CANTalon(RobotMap.lbMotorTalon);
		rfMotor = new CANTalon(RobotMap.rfMotorTalon);
		rbMotor = new CANTalon(RobotMap.rbMotorTalon);

		drive = new RobotDrive(lfMotor, lbMotor, rfMotor, rbMotor);
		driveCan = new CANTalon(RobotMap.pcm);

		sol = new DoubleSolenoid(RobotMap.pcm, RobotMap.driveSolOn, RobotMap.driveSolOff);
		solieStatus = false;
		sol.set(DoubleSolenoid.Value.kOff);
	}

	public void driveXticks(double ticks)
	{
		// enable();
	}

	public void driveTank(double leftStick, double rightStick)
	{
		if (Robot.oi.invertTrigger.checkValue())
		{
			drive.tankDrive(leftStick, rightStick);
		}
		else
		{
			drive.tankDrive(-leftStick, -rightStick);
		}
	}

	public void driveArcade(double leftStick, double rightStick)
	{
		if (Robot.oi.invertTrigger.checkValue())
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
		solieStatus = !solieStatus;

		if (solieStatus == true)
		{
			sol.set(DoubleSolenoid.Value.kForward);
		}
		if (solieStatus == false)
		{
			sol.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void driveForward(double power, double ticks)
	{
		lfMotor.setEncPosition(0);
		rfMotor.setEncPosition(0);
		if (lfMotor.getEncPosition() <= ticks && rfMotor.getEncPosition() >= -ticks) // If
																						// left
																						// motor
																						// is
																						// backward
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
		} // This entire method does not take PID into account
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

	public void initDefaultCommand()
	{
		setDefaultCommand(new TankDriveCommand());
	}

	protected double returnPIDInput()
	{
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return 0.0;
	}

	protected void usePIDOutput(double output)
	{
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	}
}
