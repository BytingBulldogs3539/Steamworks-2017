package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootInsideGroup extends CommandGroup
{

    public ShootInsideGroup()
    {
    	addSequential(new AutonDrive(70));
    	addSequential(new AutonTurn(-45));
    	addSequential(new AutonDrive(30));
    	
    	addSequential(new AutonDrive(-30));    	
    	
    	//(whatever it is to make it shoot)
    	
    }
}
