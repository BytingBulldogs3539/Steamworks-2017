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

import org.usfirst.frc.team3539.robot.utilities.BulldogSleeper;

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
		// Wait for a fraction of second before you open
		BulldogSleeper.sleep(250);
		
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());

		BulldogSleeper.sleep(1000);
		
		BulldogLogger.getInstance().logInfo("  Starting 1st Drive backward");
		addSequential(new AutonDrive(-30, .6));
		BulldogLogger.getInstance().logInfo("    Ending 1st Drive backward");
		
		// Don't care to log the GearClose and HoodClose
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());

        BulldogLogger.getInstance().logInfo("Ending GearLeftGroup auton");	
	}
}
