package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootInsideGroupBlue extends CommandGroup
{

    public ShootInsideGroupBlue()
    {
        // Log the various steps of this auton
    	addSequential(new AutonDrive(70));
    	
    	addSequential(new AutonTurn(45));
    	
    	addSequential(new AutonDrive(30));
    	
    	addSequential(new AutonDrive(-30));
    	
    	
    	//(whatever tf it is to make it shoot)
    	
    }
}
