package org.usfirst.frc.team3539.robot.autongroups;

import autoncommands.AutonDrive;
import autoncommands.AutonGearOpen;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearLeftGroup extends CommandGroup
{
	public GearLeftGroup()
	{
		addSequential(new AutonDrive(80)); // A little further
		addSequential(new AutonTurn(-60));
		addSequential(new AutonDrive(50));
		addSequential(new AutonGearOpen());
	}
}
