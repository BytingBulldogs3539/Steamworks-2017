package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;
import org.usfirst.frc.team3539.robot.utilities.BulldogSleeper;

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
		addSequential(new AutonTurn(60, .75));
		BulldogLogger.getInstance().logInfo("    End 1st turn");
		
		BulldogLogger.getInstance().logInfo("  Start 2nd Drive Forward");
		addSequential(new AutonDrive(30, .5));
		BulldogLogger.getInstance().logInfo("    End 2nd Drive Forward");

		// Wait a little bit before I open the hood
		//BulldogSleeper.sleep(250);
		
		// Don't care to log the GearOpen and HoodOpen
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());

		addSequential(new AutoWait(2));
//		BulldogSleeper.sleep(2000);
				
		BulldogLogger.getInstance().logInfo("  Start 1st Drive backward");
		addSequential(new AutonDrive(-20, .7));
		BulldogLogger.getInstance().logInfo("    End 1st Drive backward");

		// Don't care to log the GearClose and HoodClose
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());

        BulldogLogger.getInstance().logInfo("  Ending GearRightGroup auton");
	}
}
