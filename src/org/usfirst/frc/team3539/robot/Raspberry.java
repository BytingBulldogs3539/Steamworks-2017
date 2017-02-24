package org.usfirst.frc.team3539.robot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class Raspberry
{	
	static NetworkTable table;
	public Raspberry ()
	{

	}
	public static  void Read()
	{
		table = NetworkTable.getTable("Test");
		System.out.println(table.getNumber("Testing"));
	}
}