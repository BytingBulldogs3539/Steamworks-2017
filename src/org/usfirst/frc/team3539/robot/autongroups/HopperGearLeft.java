package org.usfirst.frc.team3539.robot.autongroups;

import autoncommands.AutoWait;
import autoncommands.AutonDrive;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperGearLeft extends CommandGroup
{

	public HopperGearLeft()
	{
		addSequential(new GearLeftGroup());
		addSequential(new AutoWait(1));
		addSequential(new AutonTurn(-40, .7));
		addSequential(new AutonDrive(-40, .7));
	}
}
