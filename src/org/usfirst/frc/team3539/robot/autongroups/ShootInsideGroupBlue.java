package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
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
    	
<<<<<<< HEAD
    	BulldogLogger.getInstance().logInfo("  Starting 1st turn");
    	
    	if(RobotMap.onBlueSide) addSequential(new AutonTurn(90));
    	else addSequential(new AutonTurn(-90));
    	
    	BulldogLogger.getInstance().logInfo("    Ending 1st turn");
=======
    	addSequential(new AutonTurn(45));
>>>>>>> origin/master
    	
    	addSequential(new AutonDrive(30));
    	
<<<<<<< HEAD
    	BulldogLogger.getInstance().logInfo("  Starting 2nd turn");
    	
    	if(RobotMap.onBlueSide) addSequential(new AutonTurn(45));
    	else addSequential(new AutonTurn(-45));
    	
    	BulldogLogger.getInstance().logInfo("    Ending 2nd turn");
=======
    	addSequential(new AutonDrive(-30));
>>>>>>> origin/master
    	
    	
    	//(whatever tf it is to make it shoot)
    	
    }
}
