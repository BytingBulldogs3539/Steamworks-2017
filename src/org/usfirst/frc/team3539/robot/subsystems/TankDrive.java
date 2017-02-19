package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.commands.DriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Encoder;

public class TankDrive extends BulldogSystem
{
	private CANTalon lfMotor;
	private CANTalon lbMotor;
	private CANTalon rfMotor;
	private CANTalon rbMotor;
	@SuppressWarnings("unused")
	private CANTalon driveCan;
	// private Encoder lEncoder;
	// private Encoder rEncoder;
	public RobotDrive drive;
	private DoubleSolenoid sol;
	private boolean solieStatus;
	private ADXRS450_Gyro gyro;

	public TankDrive()
	{
		super("TankDrive");

		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);// Needs to be above
														// anything using gyro

		lfMotor = new CANTalon(RobotMap.lfMotorTalon);
		lbMotor = new CANTalon(RobotMap.lbMotorTalon);
		rfMotor = new CANTalon(RobotMap.rfMotorTalon);
		rbMotor = new CANTalon(RobotMap.rbMotorTalon);
		// lEncoder = new Encoder(0, 1);
		// rEncoder = new Encoder(0, 1);

		drive = new RobotDrive(lfMotor, lbMotor, rfMotor, rbMotor);
		driveCan = new CANTalon(RobotMap.pcm);

		sol = new DoubleSolenoid(RobotMap.pcm, RobotMap.driveSolOn, RobotMap.driveSolOff);
		solieStatus = false;
		sol.set(DoubleSolenoid.Value.kOff);
	}

	public void driveTank(double leftStick, double rightStick)
	{
		if(Robot.oi.invertTrigger.checkValue())
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
		solieStatus = !solieStatus;

		if(solieStatus == true)
		{
			sol.set(DoubleSolenoid.Value.kForward);
		}
		if(solieStatus == false)
		{
			sol.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void driveForward(double power, double ticks)
	{
		lfMotor.setEncPosition(0);
		rfMotor.setEncPosition(0);
		if(lfMotor.getEncPosition() <= ticks && rfMotor.getEncPosition() >= -ticks)
		{
			lfMotor.set(power);
			lbMotor.set(power);
			rfMotor.set(-power);
			rbMotor.set(-power);
		}
		else // This entire method does not take PID into account
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
		if(direction == "r")
		{
			if(lfMotor.getEncPosition() >= ticks && rfMotor.getEncPosition() >= ticks)
			{
				lfMotor.set(power);
				lbMotor.set(power);
				rfMotor.set(power);
				rbMotor.set(power);
			}
		} // This entire method does not take PID into account
		else if(direction == "l")
		{
			if(lfMotor.getEncPosition() <= -ticks && rfMotor.getEncPosition() <= -ticks)
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
		setDefaultCommand(new DriveCommand());
	}

	@Override
	@SuppressWarnings("deprecation")
	public void Update()
	{
		if(solieStatus == true)
		{
			SmartDashboard.putString("Drive Gear", "High");
		}
		else
		{
			SmartDashboard.putString("Drive Gear", "Low");
		}
		SmartDashboard.putDouble("GyroVelocity", gyro.getRate());
		SmartDashboard.putDouble("GryoAngle", gyro.getAngle() % 360);
	}

	@Override
	public void SmartInit()
	{
		SmartDashboard.putString("Drive Gear", "--");
	}

	public void gyroReset()
	{
		gyro.reset();
		System.out.println("gyro reset");
	}
}
