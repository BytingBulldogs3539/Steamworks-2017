package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NoneForward extends CommandGroup
{
	public NoneForward()
	{
		addSequential(new AutonDrive(200));
	}
}
