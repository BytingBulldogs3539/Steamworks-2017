package org.usfirst.frc.team3539.robot.autongroups;


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
        
		addSequential(new AutonTurn(-45 ));
		
		addSequential(new AutonDrive(115 ));
		
		addSequential(new AutonTurn(45 ));
		
		addSequential(new AutonDrive(100 )); // estimate
		
		addSequential(new AutonTurn(45 ));
		
		addSequential(new AutonDrive(140 ));
		
		addSequential(new AutonTurn(80 )); // also estimate, aim at tower
		
		// shoot
		addSequential(new AutonDrive(70 ));
		
    	addSequential(new AutonTurn(90 ));
    	
    	addSequential(new AutonDrive(124 ));
    	
    	addSequential(new AutonTurn(45 ));
    	
    	//(whatever tf it is to make it shoot)
    }
}
