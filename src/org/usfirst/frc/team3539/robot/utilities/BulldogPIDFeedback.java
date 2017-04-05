package org.usfirst.frc.team3539.robot.utilities;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class BulldogPIDFeedback implements PIDSource
{
	private boolean visionFeedback = false;
	public void setPIDSourceType(PIDSourceType pidSource)
	{
	}

	public PIDSourceType getPIDSourceType()
	{
		return PIDSourceType.kDisplacement;
	}
	
	public void setVisionFeedback(boolean value)
	{
		visionFeedback = value;
	}
	
	public double pidGet()
	{
		if(visionFeedback)
			return -Robot.raspberry.getTurnAngle();				
		else
			return Robot.driveTrain.getGyroAngle();
	}
}
