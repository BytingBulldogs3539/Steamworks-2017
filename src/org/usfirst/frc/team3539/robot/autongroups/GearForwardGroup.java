package org.usfirst.frc.team3539.robot.autongroups;


import autoncommands.AutonDrive;
import autoncommands.AutonGearOpen;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearForwardGroup extends CommandGroup
{
	public GearForwardGroup()
	{
		addSequential(new AutonDrive(104));
		addSequential(new AutonGearOpen());
	}
}
