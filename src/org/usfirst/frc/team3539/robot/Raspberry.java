package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Raspberry
{
	private static NetworkTable table;

	private double offset = 0;
	private double angle = 0;
	private double distance = 0;
	
	public Raspberry()
	{
		table = NetworkTable.getTable("Vision");
		table.putNumber("Offset", 0);
	}

	public void Init()
	{
		table = NetworkTable.getTable("Vision");
		table.putNumber("Offset", 0);
	}

	@SuppressWarnings("deprecation")
	public void Print()
	{
		System.out.println("Raspberry: " + this.Read());
	}
	
	@SuppressWarnings("deprecation")
	public double Read()
	{
		return table.getNumber("Offset");
	}
}