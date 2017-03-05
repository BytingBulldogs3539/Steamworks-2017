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
public class GearLeftGroup extends CommandGroup
{
	public GearLeftGroup()
	{
	    // Log the various steps of this auton
	    BulldogLogger.getInstance().logInfo("Starting GearLeftGroup auton");
	    
	    BulldogLogger.getInstance().logInfo("  Starting 1st Drive forward");
		addSequential(new AutonDrive(70, .7));
		BulldogLogger.getInstance().logInfo("    Ending 1st Drive forward");
		
		BulldogLogger.getInstance().logInfo("  Starting 1st turn");
		addSequential(new AutonTurn(-50, .6));
		BulldogLogger.getInstance().logInfo("    Ending 1st turn");
		
		BulldogLogger.getInstance().logInfo("  Starting 2nd Drive Forward");
		addSequential(new AutonDrive(30, .5));
		BulldogLogger.getInstance().logInfo("    Ending 2nd Drive Forward");

		// Don't care to log the GearOpen and HoodOpen
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());

		BulldogLogger.getInstance().logInfo("  Starting AutoWait");
		addSequential(new AutoWait(1));
		BulldogLogger.getInstance().logInfo("    Ending AutoWait");
		
		BulldogLogger.getInstance().logInfo("  Starting 1st Drive backward");
		addSequential(new AutonDrive(-30, .6));
		BulldogLogger.getInstance().logInfo("    Ending 1st Drive backward");
		
		// Don't care to log the GearClose and HoodClose
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());

        BulldogLogger.getInstance().logInfo("Ending GearLeftGroup auton");	
	}
}
