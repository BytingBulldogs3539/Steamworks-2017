package org.usfirst.frc.team3539.robot.autongroups;

import autoncommands.AutonDrive;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootMiddleGroup extends CommandGroup {

    public ShootMiddleGroup()
    {
        addSequential(new AutonDrive(-44, .7));
        addSequential(new AutonTurn(95)); // estimate, aim at tower
        
        // shoot
    	addSequential(new AutonDrive(70, .7));
    	addSequential(new AutonTurn(90));
    	addSequential(new AutonDrive(97, .7));
    	addSequential(new AutonTurn(45));
    	//(whatever tf it is to make it shoot)
    }
}
