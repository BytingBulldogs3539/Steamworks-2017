package org.usfirst.frc.team3539.robot.autongroups;

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
public class GearRightGroup extends CommandGroup
{

	public GearRightGroup()
	{
	    // Log the various steps of this auton
	    BulldogLogger.getInstance().logInfo("Starting GearRightGroup auton");
	    
	    BulldogLogger.getInstance().logInfo("  Start 1st Drive forward");
		addSequential(new AutonDrive(70, .7));
		BulldogLogger.getInstance().logInfo("    End 1st Drive Forward");
		
		BulldogLogger.getInstance().logInfo("  Start 1st turn");
		addSequential(new AutonTurn(50, .6));
		BulldogLogger.getInstance().logInfo("    End 1st turn");
		
		BulldogLogger.getInstance().logInfo("  Start 2nd Drive Forward");
		addSequential(new AutonDrive(30, .5));
		BulldogLogger.getInstance().logInfo("    End 2nd Drive Forward");

		// Don't care to log the GearOpen and HoodOpen
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());

		BulldogLogger.getInstance().logInfo("  Start AutoWait");
		addSequential(new AutoWait(1));
		BulldogLogger.getInstance().logInfo("    End AutoWait");
		
		BulldogLogger.getInstance().logInfo("  Start 1st Drive backward");
		addSequential(new AutonDrive(-20, .7));
		BulldogLogger.getInstance().logInfo("    End 1st Drive backward");

		// Don't care to log the GearClose and HoodClose
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());

        BulldogLogger.getInstance().logInfo("  Ending GearRightGroup auton");
	}
}
