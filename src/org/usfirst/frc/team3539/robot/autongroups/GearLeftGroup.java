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
public class GearLeftGroup extends CommandGroup
{
	public GearLeftGroup()
	{
		addSequential(new AutonDrive(70, .7));
		addSequential(new AutonTurn(-50));
		addSequential(new AutonDrive(30, .5));
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());
		addSequential(new AutoWait(1));
		addSequential(new AutonDrive(-20, .7));
		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());
	}
}
