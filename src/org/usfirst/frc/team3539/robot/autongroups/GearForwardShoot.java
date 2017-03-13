package org.usfirst.frc.team3539.robot.autongroups;


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
		
	}
}
