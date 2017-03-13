package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class Raspberry
{
	public enum camera
	{
		GEAR(0),
		BOILER(1);
		
	   private int value;
	   private camera(int value) {
	      this.value = value;
	   }
	   public int getValue() {
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
		//System.out.println("Distance:"+table.getNumber("Distance"));
		return table.getNumber("Angle");
	}
	
	@SuppressWarnings("deprecation")
	public double Read()
	{
		return table.getNumber("Offset");
	}
	public double getneededHoodAngle()
	{
		return 5.4143*92-65.841;
		//return 5.4143*table.getNumber("Distance")-65.841;
	}
	public double getneededShooterRPM()
	{
		return -(9.1812*92+2335);

		//return 9.1812*table.getNumber("Distance")+2335;
	}
	
	public void UpdateCamera(int cameranumber)
	{
	    table.putNumber("camera",cameranumber);
	}
}