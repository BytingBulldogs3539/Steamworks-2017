package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.autoncommands.AutonShoot;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

/**
 * Perform GearLeftGroup and then pull back and shoot
 * 
 * @author Cameron
 *
 */
public class GearLeftShoot extends GearLeftGroup
{
	public GearLeftShoot()
	{
		super();
		
		BulldogLogger.getInstance().logInfo("Starting GearLeftShoot");
		
		BulldogLogger.getInstance().logInfo("  Pull back");
		BulldogLogger.getInstance().logInfo("  Turn toward drive station");
		BulldogLogger.getInstance().logInfo("  Drive forward");
		BulldogLogger.getInstance().logInfo("  Turn toward boiler");
		BulldogLogger.getInstance().logInfo("  Drive forward????");
		BulldogLogger.getInstance().logInfo("  Shoot?");

    	BulldogLogger.getInstance().logInfo("   Shooting");
    	addSequential(new AutonShoot());
    	BulldogLogger.getInstance().logInfo("   Done Shooting");
    	
		BulldogLogger.getInstance().logInfo("Finished GearLeftShoot");
	}

}
