package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedShootInside extends CommandGroup
{

	public RedShootInside()
	{
		addSequential(new GearLeftGroup());

		addSequential(new JoeyShoot(7));
	}
}
