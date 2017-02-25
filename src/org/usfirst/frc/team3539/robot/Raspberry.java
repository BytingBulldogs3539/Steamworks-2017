package org.usfirst.frc.team3539.robot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class Raspberry
{	
	private static NetworkTable table;
	public Raspberry ()
	{
		table = NetworkTable.getTable("SmartDashboard");
		table.putNumber("Test", 0);

	}

	public static void Init ()
	{
		table = NetworkTable.getTable("SmartDashboard");
		table.putNumber("Test", 0);

	}
	public static void Read()
	{
		System.out.println(table.getNumber("Test"));
	}
}