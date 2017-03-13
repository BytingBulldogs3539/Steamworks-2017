package org.usfirst.frc.team3539.robot.autongroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Do the GearRightGroup auton followed by a shoot
 * 
 * @author Cameron
 *
 */
public class GearRightShoot extends CommandGroup
{
	
	public GearRightShoot()
	{
	    addSequential(new GearRightGroup());
	}
}
