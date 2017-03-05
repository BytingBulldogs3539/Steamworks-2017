package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import autoncommands.AutonDrive;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootInsideGroup extends CommandGroup
{

    public ShootInsideGroup()
    {
        // Log the various steps of this auton
        BulldogLogger.getInstance().logInfo("Starting ShootInsideGroup auton");
        
        BulldogLogger.getInstance().logInfo("  Starting 1st Drive Forward");
    	addSequential(new AutonDrive(70, .7));
    	BulldogLogger.getInstance().logInfo("    Ending 1st Drive Forward");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 1st turn");
    	addSequential(new AutonTurn(90, .6));
    	BulldogLogger.getInstance().logInfo("    Ending 1st turn");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 2nd Drive Forward");
    	addSequential(new AutonDrive(27, .7));
    	BulldogLogger.getInstance().logInfo("    Ending 2nd Drive Forward");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 2nd turn");
    	addSequential(new AutonTurn(45, .6));
    	BulldogLogger.getInstance().logInfo("    Ending 2nd turn");
    	
    	BulldogLogger.getInstance().logInfo("  Ending ShootInsideGroup auton");
    	//(whatever tf it is to make it shoot)
    	
    }
}
