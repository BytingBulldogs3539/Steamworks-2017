package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueShootOutside extends CommandGroup
{

	public BlueShootOutside()
	{
		addSequential(new GearLeftGroup());

		addSequential(new AutonTurn(120,3));

		addSequential(new JoeyShoot(7));
	}
}
