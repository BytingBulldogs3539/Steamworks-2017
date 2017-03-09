package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootMiddleGroupBlue extends CommandGroup {

    public ShootMiddleGroupBlue()
    {
        // Log the various steps of this auton
        BulldogLogger.getInstance().logInfo("Starting ShootMiddleGroupBlue auton");
        
        BulldogLogger.getInstance().logInfo("  Starting 1st drive forward");
    	addSequential(new AutonDrive(70, .7));
    	BulldogLogger.getInstance().logInfo("    Ending 1st drive forward");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 1st turn");
    	addSequential(new AutonTurn(-90, .6));
    	BulldogLogger.getInstance().logInfo("    Ending 1st turn");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 2nd drive forward");
    	addSequential(new AutonDrive(97, .7));
    	BulldogLogger.getInstance().logInfo("    Ending 2nd drive forward");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 2nd turn");
    	addSequential(new AutonTurn(-45, .6));
    	BulldogLogger.getInstance().logInfo("    Ending 2nd turn");
    	
    	//(whatever tf it is to make it shoot)
    	
    	BulldogLogger.getInstance().logInfo("  Ending ShootMiddleGroupBlue auton");
    }
}
