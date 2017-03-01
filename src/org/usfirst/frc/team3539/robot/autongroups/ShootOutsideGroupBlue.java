package org.usfirst.frc.team3539.robot.autongroups;

import autoncommands.AutonDrive;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOutsideGroupBlue extends CommandGroup {

    public ShootOutsideGroupBlue()
    {
    	addSequential(new AutonDrive(70));
    	addSequential(new AutonTurn(-90));
    	addSequential(new AutonDrive(124));
    	addSequential(new AutonTurn(-45));
    	//(whatever tf it is to make it shoot)
    }
}
