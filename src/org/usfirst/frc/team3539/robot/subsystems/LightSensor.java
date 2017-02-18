package org.usfirst.frc.team3539.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */

public class LightSensor extends Subsystem {

	private static boolean light_sensor;
	private static DigitalInput lightsensor;
	 
	boolean init = true;
	
	
	public static void init(DigitalInput lightSensor_in) 
	{
	
		DigitalInput light_Sensor = lightSensor_in;
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static void update()
	{
		light_sensor = lightsensor.get();
	}
	public static boolean getlight()
	{
		return light_sensor;
	}
	
    public void initDefaultCommand() 
	{
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

