package org.usfirst.frc.team3539.robot.autongroups;


import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOutsideGroup extends CommandGroup {

    public ShootOutsideGroup()
    {
        // Log the various steps of this auton
        
//    	addSequential(new AutonDrive(105));
        
//		addSequential(new AutonTurn(45));

//		addSequential(new AutonDrive(40));
        
        // Don't care to log the AutoToggleGearCommand
//		addSequential(new AutonToggleGearCommand());
		
        addSequential(new AutonDrive(-40 ));
        
<<<<<<< HEAD
        BulldogLogger.getInstance().logInfo("  Starting 2nd turn");
        
		if(RobotMap.onBlueSide) addSequential(new AutonTurn(45 ));
		else addSequential(new AutonTurn(-45 ));
		
		BulldogLogger.getInstance().logInfo("    Ending 2nd turn");
=======
		addSequential(new AutonTurn(-45 ));
>>>>>>> origin/master
		
		addSequential(new AutonDrive(115 ));
		
<<<<<<< HEAD
		BulldogLogger.getInstance().logInfo("  Starting 3rd turn");
		
		if(RobotMap.onBlueSide) addSequential(new AutonTurn(-45 ));
		else addSequential(new AutonTurn(45 ));
		
		BulldogLogger.getInstance().logInfo("    Ending 3rd turn");
=======
		addSequential(new AutonTurn(45 ));
>>>>>>> origin/master
		
		addSequential(new AutonDrive(100 )); // estimate
		
<<<<<<< HEAD
		BulldogLogger.getInstance().logInfo("  Starting 4th turn");
		
		if(RobotMap.onBlueSide) addSequential(new AutonTurn(-45 ));
		else addSequential(new AutonTurn(45 ));
		
		BulldogLogger.getInstance().logInfo("    Ending 4th turn");
=======
		addSequential(new AutonTurn(45 ));
>>>>>>> origin/master
		
		addSequential(new AutonDrive(140 ));
		
<<<<<<< HEAD
		BulldogLogger.getInstance().logInfo("  Starting 5th turn");
		
		if(RobotMap.onBlueSide) addSequential(new AutonTurn(-80 ));
		else addSequential(new AutonTurn(80 )); // also estimate, aim at tower
		
		BulldogLogger.getInstance().logInfo("    Ending 5th turn");
=======
		addSequential(new AutonTurn(80 )); // also estimate, aim at tower
>>>>>>> origin/master
		
		// shoot
		addSequential(new AutonDrive(70 ));
		
<<<<<<< HEAD
		BulldogLogger.getInstance().logInfo("  Starting 6th turn");
    	
		if(RobotMap.onBlueSide) addSequential(new AutonTurn(-90 ));
		else addSequential(new AutonTurn(90 ));
    	
    	BulldogLogger.getInstance().logInfo("    Ending 6th turn");
=======
    	addSequential(new AutonTurn(90 ));
>>>>>>> origin/master
    	
    	addSequential(new AutonDrive(124 ));
    	
<<<<<<< HEAD
    	BulldogLogger.getInstance().logInfo("  Starting 7th turn");
    	
    	if(RobotMap.onBlueSide) addSequential(new AutonTurn(-45 ));
    	else addSequential(new AutonTurn(45 ));
    	
    	BulldogLogger.getInstance().logInfo("    Ending 7th turn");
=======
    	addSequential(new AutonTurn(45 ));
>>>>>>> origin/master
    	
    	//(whatever tf it is to make it shoot)
    }
}
