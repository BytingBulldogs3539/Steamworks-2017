package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedShootMiddle extends CommandGroup
{

	public RedShootMiddle()
	{
		addSequential(new GearMiddleGroup());

		addSequential(new AutonTurn(100));
		
		addSequential(new JoeyShoot(7));
	}
}
