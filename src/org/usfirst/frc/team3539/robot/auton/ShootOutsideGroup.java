package org.usfirst.frc.team3539.robot.auton;

import org.usfirst.frc.team3539.robot.commands.AutonDrive;
import org.usfirst.frc.team3539.robot.commands.AutonToggleGearCommand;
import org.usfirst.frc.team3539.robot.commands.AutonTurn;

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
		addSequential(new AutonDrive(-40));
		addSequential(new AutonTurn(-45));
		addSequential(new AutonDrive(115));
		addSequential(new AutonTurn(45));
		addSequential(new AutonDrive(100)); // estimate
		addSequential(new AutonTurn(45));
		addSequential(new AutonDrive(140));
		addSequential(new AutonTurn(80)); // also estimate, aim at tower
		// shoot
    }
}
