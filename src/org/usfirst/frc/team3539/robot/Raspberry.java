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
		if(table.getNumber("camera") == 0)
		{
			return (table.getNumber("Angle") + RobotMap.gearCameraOffset);	
		}else{
			return (table.getNumber("Angle") + RobotMap.shooterCameraOffset);
		}
	}

	public double getHoodAngle()
	{
		double slope = (RobotMap.Hood2- RobotMap.Hood1) / (RobotMap.Distance2-RobotMap.Distance1);
		double intercept = RobotMap.Hood1- (slope * RobotMap.Distance1);
		double distance = table.getNumber("Distance");

			return ((slope * distance) + intercept);// old value 5.4143 * distance - 20;
	}

	public double getShooterRPM()
	{
	//	double distance = table.getNumber("Distance");
		double slope = (RobotMap.RPM2-RobotMap.RPM1) / (RobotMap.Distance2-RobotMap.Distance1);
		double intercept = RobotMap.RPM1- (slope * RobotMap.Distance1);
		double distance = table.getNumber("Distance");

		// return -(9.1812*92+2335);

			return -((slope * distance) + intercept); //-((12.009) * distance + 1763);

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
		

		
		
		RobotMap.Distance1 = SmartDashboard.getDouble("Distance1");
		RobotMap.Distance2 = SmartDashboard.getDouble("Distance2");
		RobotMap.RPM1 = SmartDashboard.getDouble("RPM1");
		RobotMap.RPM2 = SmartDashboard.getDouble("RPM2");
		RobotMap.Hood1 = SmartDashboard.getDouble("Hood1");
		RobotMap.Hood2 = SmartDashboard.getDouble("Hood2");
		
		
		
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void SmartInit()
	{
		SmartDashboard.putDouble("Raspberry PI", Robot.raspberry.getTurnAngle());
		SmartDashboard.putDouble("PI Distance", Robot.raspberry.getDistance());
		
		SmartDashboard.putDouble("Distance1", RobotMap.Distance1);
		SmartDashboard.putDouble("Distance2", RobotMap.Distance2);
		SmartDashboard.putDouble("RPM1", RobotMap.RPM1);
		SmartDashboard.putDouble("RPM2", RobotMap.RPM2);
		SmartDashboard.putDouble("Hood1", RobotMap.Hood1);
		SmartDashboard.putDouble("Hood2", RobotMap.Hood2);
	}
}