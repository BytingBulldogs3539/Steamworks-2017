package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Raspberry
{
	public enum camera
	{
		GEAR(0), BOILER(1);

		private int value;

		private camera(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	};

	private static NetworkTable table;

	private double offset = 0;
	private double angle = 0;
	private double distance = 0;
	private int camera = 0;

	public Raspberry()
	{
		table = NetworkTable.getTable("Vision");
		table.putNumber("Angle", 0);
		table.putNumber("Offset", 0);
		table.putNumber("Distance", 0);
		table.putNumber("camera", 0);

	}

	public void Init()
	{
		table = NetworkTable.getTable("Vision");
	}

	public void Print()
	{
		System.out.println("Raspberry: " + this.Read());

	}

	@SuppressWarnings("deprecation")
	public double getDistance()
	{
		return table.getNumber("Distance");
	}

	@SuppressWarnings("deprecation")
	public double getAngle()
	{
		// System.out.println("Distance:"+table.getNumber("Distance"));
		return table.getNumber("Angle") - 4.5;
	}

	@SuppressWarnings("deprecation")
	public double Read()
	{
		return table.getNumber("Offset");
	}

	public double getneededHoodAngle()
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

	public double getneededShooterRPM()
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

	public void UpdateCamera(int cameranumber)
	{
		table.putNumber("camera", cameranumber);
	}
}