package org.usfirst.frc.team3539.robot.autongroups;

import autoncommands.AutonDrive;
import autoncommands.AutonGearOpen;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearRightGroup extends CommandGroup
{

	public GearRightGroup()
	{
		addSequential(new AutonDrive(105));
		addSequential(new AutonTurn(45));
		addSequential(new AutonDrive(40));
		addSequential(new AutonGearOpen());
	}
}
