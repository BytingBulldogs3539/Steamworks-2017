package org.usfirst.frc.team3539.robot.autongroups;


import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Perform GearForwardGroup and then shoot
 * 
 * @author Cameron
 *
 */
public class GearForwardShoot extends CommandGroup
{
	public GearForwardShoot()
	{
		addSequential(new GearForwardGroup());
		if(RobotMap.isVisionTracking)
		{
			//Vision track
		}
		else
		{
			//Hard code
		}
		
	}
}
