package org.usfirst.frc.team3539.robot.autongroups;


import org.usfirst.frc.team3539.robot.RobotMap;

import autoncommands.AutoWait;
import autoncommands.AutonDrive;
import autoncommands.AutonGearClose;
import autoncommands.AutonGearOpen;
import autoncommands.AutonHoodClose;
import autoncommands.AutonHoodOpen;
import autoncommands.AutonTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearForwardGroup extends CommandGroup
{
	public GearForwardGroup()
	{
		addSequential(new AutonDrive(50, 1));
		addSequential(new AutonDrive(20, .4));
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());
		addSequential(new AutoWait(2));
		addSequential(new AutonDrive(-20, 1));
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());
	}
}
