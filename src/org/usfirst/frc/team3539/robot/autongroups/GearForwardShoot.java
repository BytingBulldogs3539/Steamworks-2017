package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import autoncommands.AutonShoot;

/**
 * Perform GearForwardGroup and then shoot
 * 
 * @author Cameron
 *
 */
public class GearForwardShoot extends GearForwardGroup
{
	public GearForwardShoot()
	{
		super();
		
		BulldogLogger.getInstance().logInfo("Starting GearForwardShoot");
		
		BulldogLogger.getInstance().logInfo("  Pull back");
		BulldogLogger.getInstance().logInfo("  Turn right");
		BulldogLogger.getInstance().logInfo("  Drive forward");
		
		BulldogLogger.getInstance().logInfo("  Shoot");
		addSequential(new AutonShoot());
		BulldogLogger.getInstance().logInfo("  Done shooting");
		
		BulldogLogger.getInstance().logInfo("Finished GearForwardShoot");
	}
}
