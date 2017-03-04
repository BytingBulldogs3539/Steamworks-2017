package org.usfirst.frc.team3539.robot.autongroups;


import autoncommands.AutonDrive;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOutsideGroup extends CommandGroup {

    public ShootOutsideGroup()
    {
//    	addSequential(new AutonDrive(105));
//		addSequential(new AutonTurn(45));
//		addSequential(new AutonDrive(40));
//		addSequential(new AutonToggleGearCommand());
		addSequential(new AutonDrive(-40, .7));
		addSequential(new AutonTurn(-45));
		addSequential(new AutonDrive(115, .7));
		addSequential(new AutonTurn(45));
		addSequential(new AutonDrive(100, .7)); // estimate
		addSequential(new AutonTurn(45));
		addSequential(new AutonDrive(140, .7));
		addSequential(new AutonTurn(80)); // also estimate, aim at tower
		// shoot
    	addSequential(new AutonDrive(70, .7));
    	addSequential(new AutonTurn(90));
    	addSequential(new AutonDrive(124, .7));
    	addSequential(new AutonTurn(45));
    	//(whatever tf it is to make it shoot)
    }
}
