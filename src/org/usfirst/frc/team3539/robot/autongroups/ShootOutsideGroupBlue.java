package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOutsideGroupBlue extends CommandGroup {

    public ShootOutsideGroupBlue()
    {
        // Log the various steps of this auton
        BulldogLogger.getInstance().logInfo("Starting ShootOutsideGroupBlue auton");
        
        BulldogLogger.getInstance().logInfo("  Starting 1st drive forward");
    	addSequential(new AutonDrive(70));
    	BulldogLogger.getInstance().logInfo("    Ending 1st drive forward");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 1st turn");
    	addSequential(new AutonTurn(-90));
    	BulldogLogger.getInstance().logInfo("    Ending 1st turn");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 2nd drive forward");
    	addSequential(new AutonDrive(124));
    	BulldogLogger.getInstance().logInfo("    Ending 2nd drive forward");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 2nd turn");
    	addSequential(new AutonTurn(-45));
    	BulldogLogger.getInstance().logInfo("    Ending 2nd turn");
    	
    	//(whatever tf it is to make it shoot)
    	
    	BulldogLogger.getInstance().logInfo("  Ending ShootOutsideGroupBlue auton");
    }
}
