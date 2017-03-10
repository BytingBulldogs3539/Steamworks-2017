package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FarGearLeft extends CommandGroup
{

	public FarGearLeft()
	{
	    // Log the various steps of this auton
        BulldogLogger.getInstance().logInfo("Starting FarGearLeft");
	    
        BulldogLogger.getInstance().logInfo("  Starting GearLeftGroup");
		addSequential(new GearLeftGroup());
		BulldogLogger.getInstance().logInfo("    Ending GearLeftGroup");
		
		BulldogLogger.getInstance().logInfo("  Starting 1st turn");
		addSequential(new AutonTurn(50));
		BulldogLogger.getInstance().logInfo("    Ending 1st turn");
		
		BulldogLogger.getInstance().logInfo("  Starting 1st Drive Forward");
		addSequential(new AutonDrive(100)); // orig. speedcap = .8
		BulldogLogger.getInstance().logInfo("    Ending 1st Drive Forward");
		
		BulldogLogger.getInstance().logInfo("  Ending FarGearLeft");
	}
}
