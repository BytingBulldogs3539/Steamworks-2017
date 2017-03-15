package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveGentle;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearForwardGroup extends CommandGroup
{
	public GearForwardGroup()
	{

		addSequential(new AutonDriveOld(68)); // 85

		addSequential(new AutonDriveGentle(20, .5));

		addSequential(new AutonGearOpen());

		addSequential(new AutoWait(1));
		
		addSequential(new AutonDriveOld(-20));

		addSequential(new AutonGearClose());
	}
}
