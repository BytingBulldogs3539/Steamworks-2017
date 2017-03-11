package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Raspberry
{
	private static NetworkTable table;

	private double offset = 0;
	private double angle = 0;
	private double distance = 0;
	public int camera = 0;
	
	public Raspberry()
	{
		table = NetworkTable.getTable("Vision");
		table.putNumber("Angle", 0);
		table.putNumber("Offset", 0);
		table.putNumber("Distance", 0);
		table.putNumber("camera", 1);

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
		//System.out.println("Distance:"+table.getNumber("Distance"));
		return table.getNumber("Angle");
	}
	
	@SuppressWarnings("deprecation")
	public double Read()
	{
		return table.getNumber("Offset");
	}
	public void UpdateCamera(int cameranumber)
	{
	    camera = cameranumber;
	    table.putNumber("camera",camera);
	}
}