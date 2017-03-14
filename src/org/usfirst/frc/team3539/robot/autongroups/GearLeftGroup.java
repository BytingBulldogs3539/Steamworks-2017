package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveGentle;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class GearLeftGroup extends CommandGroup
{    
	public GearLeftGroup()
	{
		addSequential(new AutonDrive(107));
		
		if(RobotMap.onBlueSide) addSequential(new AutonTurn(60));
		else addSequential(new AutonTurn(-60)); // original
		
		addSequential(new AutonDriveGentle(30, .5));
		
		addSequential(new AutonGearOpen());

        addSequential(new AutoWait(1));

		addSequential(new AutonDrive(-30));
		
		addSequential(new AutonGearClose());
	}
}
