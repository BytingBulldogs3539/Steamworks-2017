package org.usfirst.frc.team3539.robot.autongroups;


import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3539.robot.utilities.*;

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
		
		// Add a step to wait for a fraction of a second before I open the gear
		BulldogSleeper.sleep(250);
		
		// Don't care to log the GearOpen and HoodOpen steps
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());
		
		BulldogSleeper.sleep(2000)
;
		BulldogLogger.getInstance().logInfo("  Starting 1st drive backward");
		addSequential(new AutonDrive(-20, 1));
		BulldogLogger.getInstance().logInfo("    Ending 1st drive backward");
		
		// Don't care to log the GearClose and HoodClose steps
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());

        BulldogLogger.getInstance().logInfo("  Ending GearForwardGroup");	
	}
}
