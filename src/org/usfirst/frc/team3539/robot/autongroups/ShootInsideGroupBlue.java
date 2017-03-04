package org.usfirst.frc.team3539.robot.autongroups;

import autoncommands.AutonDrive;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootInsideGroupBlue extends CommandGroup
{

    public ShootInsideGroupBlue()
    {
    	addSequential(new AutonDrive(70, .7));
    	addSequential(new AutonTurn(-90, .6));
    	addSequential(new AutonDrive(27, .7));
    	addSequential(new AutonTurn(-45, .6));
    	//(whatever tf it is to make it shoot)
    	
    }
}
