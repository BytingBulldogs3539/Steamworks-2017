package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

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
		addSequential(new AutonTurn(-60, .75));
		BulldogLogger.getInstance().logInfo("    Ending 1st turn");
		
		BulldogLogger.getInstance().logInfo("  Starting 2nd Drive Forward");
		addSequential(new AutonDrive(30, .7));
		BulldogLogger.getInstance().logInfo("    Ending 2nd Drive Forward");

		// Don't care to log the GearOpen and HoodOpen
		// Wait for a fraction of second before you open
		
		
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());

        addSequential(new AutoWait(2));

		
		BulldogLogger.getInstance().logInfo("  Starting 1st Drive backward");
		addSequential(new AutonDrive(-30, .6));
		BulldogLogger.getInstance().logInfo("    Ending 1st Drive backward");
		
		// Don't care to log the GearClose and HoodClose
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());

        BulldogLogger.getInstance().logInfo("Ending GearLeftGroup auton");	
	}
}
