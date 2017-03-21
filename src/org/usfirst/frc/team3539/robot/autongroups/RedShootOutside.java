package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedShootOutside extends CommandGroup
{

	public RedShootOutside()
	{
		addSequential(new GearRightGroup());

		addSequential(new AutonTurn(-120));

		addSequential(new JoeyShoot(7));

	}
}
