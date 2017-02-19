package org.usfirst.frc.team3539.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.DigitalInput;
import org.usfirst.frc.team3539.robot.*;

public class LightSensor extends Subsystem
{

	public static void init(Boolean lightSensor_in)
	{
		Robot.light = lightSensor_in;

	}

	public void ballCount()
	{
		
		RobotMap.ballcount +=1;
	}
	
	public Boolean getlight()
	{
		return Robot.light;
	}

	protected void initDefaultCommand()
	{
		
	}
}