package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearMiddleGroup extends CommandGroup
{
	public GearMiddleGroup()
	{
		addSequential(new AutonDrive(RobotMap.whiteLineDistance, 5));

		addSequential(new AutoWait(1));

		addSequential(new AutonGearOpen());

		addSequential(new AutoWait(1));

		addSequential(new AutonDrive(-40, 3));

		addSequential(new AutonGearClose());
	}
}
