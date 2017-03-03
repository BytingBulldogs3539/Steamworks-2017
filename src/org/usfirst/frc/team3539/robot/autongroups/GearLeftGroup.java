package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;

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
		addSequential(new AutonDrive(94)); // A little further 80
		addSequential(new AutonTurn(-60)); //-60
		addSequential(new AutonDrive(55)); //50
		addSequential(new AutonGearOpen());
	}
}
