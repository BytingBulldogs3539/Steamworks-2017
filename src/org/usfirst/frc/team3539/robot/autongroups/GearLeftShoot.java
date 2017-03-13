package org.usfirst.frc.team3539.robot.autongroups;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Perform GearLeftGroup and then pull back and shoot
 * 
 * @author Cameron
 *
 */
public class GearLeftShoot extends CommandGroup
{
	public GearLeftShoot()
	{
	    addSequential(new GearLeftGroup());
		
	}

}
