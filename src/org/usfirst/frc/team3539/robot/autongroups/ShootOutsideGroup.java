package org.usfirst.frc.team3539.robot.autongroups;


import org.usfirst.frc.team3539.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOutsideGroup extends CommandGroup {

    public ShootOutsideGroup()
    {
        // Log the various steps of this auton
        BulldogLogger.getInstance().logInfo("Starting ShootOutsideGroup auton");
        
//      BulldogLogger.getInstance().logInfo("  Starting 1st drive forward");
//    	addSequential(new AutonDrive(105));
//      BulldogLogger.getInstance().logInfo("    Ending 1st drive forward");
        
//      BulldogLogger.getInstance().logInfo("  Starting 1st turn");
//		addSequential(new AutonTurn(45));
//      BulldogLogger.getInstance().logInfo("    Ending 1st turn");

//      BulldogLogger.getInstance().logInfo("  Starting 2nd drive forward");
//		addSequential(new AutonDrive(40));
//      BulldogLogger.getInstance().logInfo("    Ending 2nd drive forward");
        
        // Don't care to log the AutoToggleGearCommand
//		addSequential(new AutonToggleGearCommand());
		
        BulldogLogger.getInstance().logInfo("  Starting 1st drive backward");
        addSequential(new AutonDrive(-40, .7));
        BulldogLogger.getInstance().logInfo("    Ending 1st drive backward");
        
        BulldogLogger.getInstance().logInfo("  Starting 2nd turn");
		addSequential(new AutonTurn(-45, .6));
		BulldogLogger.getInstance().logInfo("    Ending 2nd turn");
		
		BulldogLogger.getInstance().logInfo("  Starting 3rd drive forward");
		addSequential(new AutonDrive(115, .7));
		BulldogLogger.getInstance().logInfo("    Ending 3rd drive forward");
		
		BulldogLogger.getInstance().logInfo("  Starting 3rd turn");
		addSequential(new AutonTurn(45, .6));
		BulldogLogger.getInstance().logInfo("    Ending 3rd turn");
		
		BulldogLogger.getInstance().logInfo("  Starting 4th drive forward");
		addSequential(new AutonDrive(100, .7)); // estimate
		BulldogLogger.getInstance().logInfo("    Ending 4th drive forward");
		
		BulldogLogger.getInstance().logInfo("  Starting 4th turn");
		addSequential(new AutonTurn(45, .6));
		BulldogLogger.getInstance().logInfo("    Ending 4th turn");
		
		BulldogLogger.getInstance().logInfo("  Starting 5th drive forward");
		addSequential(new AutonDrive(140, .7));
		BulldogLogger.getInstance().logInfo("    Ending 5th drive forward");
		
		BulldogLogger.getInstance().logInfo("  Starting 5th turn");
		addSequential(new AutonTurn(80, .6)); // also estimate, aim at tower
		BulldogLogger.getInstance().logInfo("    Ending 5th turn");
		
		// shoot
		BulldogLogger.getInstance().logInfo("  Starting 6th drive forward");
		addSequential(new AutonDrive(70, .7));
		BulldogLogger.getInstance().logInfo("    Ending 6th drive forward");
		
		BulldogLogger.getInstance().logInfo("  Starting 6th turn");
    	addSequential(new AutonTurn(90, .6));
    	BulldogLogger.getInstance().logInfo("    Ending 6th turn");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 7th drive forward");
    	addSequential(new AutonDrive(124, .7));
    	BulldogLogger.getInstance().logInfo("    Ending 7th drive forward");
    	
    	BulldogLogger.getInstance().logInfo("  Starting 7th turn");
    	addSequential(new AutonTurn(45, .6));
    	BulldogLogger.getInstance().logInfo("    Ending 7th turn");
    	
    	//(whatever tf it is to make it shoot)
    	BulldogLogger.getInstance().logInfo("  Ending ShootOutsideGroup auton");
    }
}
