package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NoneForward extends CommandGroup
{
	public NoneForward()
	{
		addSequential(new AutonDriveOld(200));
	}
}
