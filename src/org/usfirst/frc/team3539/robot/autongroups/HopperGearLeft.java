package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import autoncommands.AutoWait;
import autoncommands.AutonDrive;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperGearLeft extends CommandGroup
{

	public HopperGearLeft()
	{
	    // Log the various steps of this auton
	    BulldogLogger.getInstance().logInfo("Starting HopperGearLeft");
	    
	    BulldogLogger.getInstance().logInfo("  Starting GearLeftGroup");
		addSequential(new GearLeftGroup());
        BulldogLogger.getInstance().logInfo("    Ending GearLeftGroup");
        
        BulldogLogger.getInstance().logInfo("  Starting AutoWait");        
		addSequential(new AutoWait(1));
		BulldogLogger.getInstance().logInfo("    Ending AutoWait");
		
		BulldogLogger.getInstance().logInfo("  Starting 1st turn");
		addSequential(new AutonTurn(-40, .7));
		BulldogLogger.getInstance().logInfo("    Ending 1st turn");
		
		BulldogLogger.getInstance().logInfo("  Starting 1st Drive forward");
		addSequential(new AutonDrive(-40, .7));
		BulldogLogger.getInstance().logInfo("    Ending 1st Drive forward");
		
		BulldogLogger.getInstance().logInfo("  Ending HopperGearLeft");

	}
}
