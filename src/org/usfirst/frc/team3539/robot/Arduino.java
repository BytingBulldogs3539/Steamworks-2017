package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.I2C;

public class Arduino
{
	static I2C arduino;
	byte[] data;
	static int address = 4;
	
	public Arduino ()
	{
		arduino = new I2C(I2C.Port.kMXP,address);
	}
	public static  void Write()
	{
		arduino.write(address, 100);
	}
}