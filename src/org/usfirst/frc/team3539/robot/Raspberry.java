package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Raspberry
{
	private static NetworkTable table;

	public Raspberry()
	{
		table = NetworkTable.getTable("SmartDashboard");
		table.putNumber("Test", 0);
	}

	public void Init()
	{
		table = NetworkTable.getTable("SmartDashboard");
		table.putNumber("Test", 0);
	}

	@SuppressWarnings("deprecation")
	public void Print()
	{
		System.out.println("Raspberry: " + table.getNumber("Test"));
	}
<<<<<<< HEAD
	public static double Read()
=======

	@SuppressWarnings("deprecation")
	public double Read()
>>>>>>> origin/master
	{
		return table.getNumber("Test");
	}
}