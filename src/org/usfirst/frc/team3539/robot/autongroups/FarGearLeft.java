package org.usfirst.frc.team3539.robot.autongroups;

import autoncommands.AutonDrive;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FarGearLeft extends CommandGroup
{

	public FarGearLeft()
	{
		addSequential(new GearLeftGroup());
		addSequential(new AutonTurn(50));
		addSequential(new AutonDrive(100, .8));
	}
}
