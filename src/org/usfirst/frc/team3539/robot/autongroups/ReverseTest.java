package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReverseTest extends CommandGroup
{
	public ReverseTest()
	{
		addSequential(new AutonDrive(-100, .7));
	}
}
