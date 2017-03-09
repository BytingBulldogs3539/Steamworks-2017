package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import autoncommands.AutonShoot;

/**
 * Do the GearRightGroup auton followed by a shoot
 * 
 * @author Cameron
 *
 */
public class GearRightShoot extends GearRightGroup
{
	
	public GearRightShoot()
	{
		super();
		
		BulldogLogger.getInstance().logInfo("Starting GearRightShoot");
				
    	BulldogLogger.getInstance().logInfo("   Shooting");
    	addSequential(new AutonShoot());
    	BulldogLogger.getInstance().logInfo("   Done Shooting");
    	
    	BulldogLogger.getInstance().logInfo("Finished GearRightShoot");
	}
}
