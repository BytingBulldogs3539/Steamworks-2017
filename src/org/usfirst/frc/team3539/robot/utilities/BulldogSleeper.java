package org.usfirst.frc.team3539.robot.utilities;

/**
 * Simple class to wait - hides the try catch block because it looks ugly
 * 
 * @author Kevin
 *
 */
public class BulldogSleeper
{
	public static void sleep(long howLong)
	{
		try
		{
			Thread.sleep(howLong);
		}
		catch (InterruptedException e) { }
	}

}
