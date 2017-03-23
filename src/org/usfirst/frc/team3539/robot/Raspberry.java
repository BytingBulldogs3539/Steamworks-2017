package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.subsystems.BulldogSystem;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Raspberry extends BulldogSystem
{
	private static NetworkTable table;

	private double offset;
	private double angle;
	private double distance;
	private int camera;

	public Raspberry()
	{
		super("Raspberry");

		offset = 0;
		angle = 0;
		distance = 0;
		camera = RobotMap.gearCamera;

		table = NetworkTable.getTable("Vision");
		table.putNumber("Angle", angle);
		table.putNumber("Offset", offset);
		table.putNumber("Distance", distance);
		table.putNumber("camera", camera);
		
		table = NetworkTable.getTable("Vision");
	}

	public void getCameronTable()
	{
		table = NetworkTable.getTable("Vision");
	}

	@SuppressWarnings("deprecation")
	public double getDistance()
	{
		return table.getNumber("Distance");
	}

	@SuppressWarnings("deprecation")
	public double getTurnAngle()
	{
		// System.out.println("Distance:"+table.getNumber("Distance"));
		return table.getNumber("Angle"); //comp: - 4.5
	}

	public double getHoodAngle()
	{
		double distance = table.getNumber("Distance");

		// return 5.4143*92-65.841;
		if (distance < 160)
		{
			return 5.4143 * distance - 65;
		}
		else
		{
			return 5.4143 * 150 - 65;
		}
	}

	public double getShooterRPM()
	{
		double distance = table.getNumber("Distance");

		// return -(9.1812*92+2335);

		if (distance < 160)
		{
			return -((9.1812 + .4) * distance + 2335 + 120);
		}
		else
		{
			return -((9.1812 + .4) * 150 + 2335 + 120);
		}

	}

	public void setCamera(int cameranumber)
	{
		table.putNumber("camera", cameranumber);
	}

	@Override
	public void initDefaultCommand()
	{
	}

	@SuppressWarnings("deprecation")
	@Override
	public void Update()
	{
		SmartDashboard.putDouble("Raspberry PI", Robot.raspberry.getTurnAngle());
		SmartDashboard.putDouble("PI Distance", Robot.raspberry.getDistance());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void SmartInit()
	{
		SmartDashboard.putDouble("Raspberry PI", Robot.raspberry.getTurnAngle());
		SmartDashboard.putDouble("PI Distance", Robot.raspberry.getDistance());
	}
}