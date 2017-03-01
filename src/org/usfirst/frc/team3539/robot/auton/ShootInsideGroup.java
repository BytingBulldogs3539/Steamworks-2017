package org.usfirst.frc.team3539.robot.auton;

import org.usfirst.frc.team3539.robot.commands.AutonDrive;
<<<<<<< HEAD
import org.usfirst.frc.team3539.robot.commands.AutonToggleGearCommand;
=======
>>>>>>> origin/master
import org.usfirst.frc.team3539.robot.commands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
<<<<<<< HEAD
public class ShootInsideGroup extends CommandGroup {

    public ShootInsideGroup()
    {
//    	addSequential(new AutonDrive(105));
//		addSequential(new AutonTurn(-45));
//		addSequential(new AutonDrive(40));
//		addSequential(new AutonToggleGearCommand());
    	
    	addSequential(new AutonDrive(-80));
    	addSequential(new AutonTurn(190)); // CLOSE estimate, aim at tower
    	// shoot
=======
public class ShootInsideGroup extends CommandGroup
{

    public ShootInsideGroup()
    {
    	addSequential(new AutonDrive(70));
    	addSequential(new AutonTurn(90));
    	addSequential(new AutonDrive(27));
    	addSequential(new AutonTurn(45));
    	//(whatever tf it is to make it shoot)
    	
>>>>>>> origin/master
    }
}
