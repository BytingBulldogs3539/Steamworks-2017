package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueShootInside extends CommandGroup
{

	public BlueShootInside()
	{
		addSequential(new GearRightGroup());

		addSequential(new JoeyShoot(7));
	}
}
