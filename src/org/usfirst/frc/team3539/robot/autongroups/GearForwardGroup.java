package org.usfirst.frc.team3539.robot.autongroups;


import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import autoncommands.AutoWait;
import autoncommands.AutonDrive;
import autoncommands.AutonGearClose;
import autoncommands.AutonGearOpen;
import autoncommands.AutonHoodClose;
import autoncommands.AutonHoodOpen;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearForwardGroup extends CommandGroup
{
	public GearForwardGroup()
	{
	    // Log the various steps of this auton
	    BulldogLogger.getInstance().logInfo("Starting GearForwardGroup");
	    
	    BulldogLogger.getInstance().logInfo("  Starting 1st Drive Forward");
		addSequential(new AutonDrive(50, 1));
		BulldogLogger.getInstance().logInfo("    Ending 1st Drive Forward");
		
		BulldogLogger.getInstance().logInfo("  Starting 2nd Drive forward");
		addSequential(new AutonDrive(20, .4));
		BulldogLogger.getInstance().logInfo("    Ending 2nd Drive forward");
		
		// Don't care to log the GearOpen and HoodOpen steps
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());
		
		BulldogLogger.getInstance().logInfo("  Starting AutoWait");
		addSequential(new AutoWait(2));
		BulldogLogger.getInstance().logInfo("    Ending AutoWait");
		
		BulldogLogger.getInstance().logInfo("  Starting 1st drive backward");
		addSequential(new AutonDrive(-20, 1));
		BulldogLogger.getInstance().logInfo("    Ending 1st drive backward");
		
		// Don't care to log the GearClose and HoodClose steps
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());

        BulldogLogger.getInstance().logInfo("  Ending GearForwardGroup");	
	}
}
