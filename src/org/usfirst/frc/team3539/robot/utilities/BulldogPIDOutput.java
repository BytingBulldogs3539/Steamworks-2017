package org.usfirst.frc.team3539.robot.utilities;

import edu.wpi.first.wpilibj.PIDOutput;

public class BulldogPIDOutput implements PIDOutput
{
	private double pidValue = 0.0;
	
	public void pidWrite(double output)
	{
		pidValue = output;
	}
	
	public void Reset()
	{
		pidValue = 0;
	}
	
	public double Get()
	{
		return pidValue;
	}
	
}
