package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutoAim;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueShootMiddle extends CommandGroup
{

	public BlueShootMiddle()
	{
		addSequential(new GearMiddleGroup());

		addSequential(new AutonDrive(-40,3));

		addSequential(new AutonTurn(75,2));
		
		addSequential(new AutoAim());

		addSequential(new JoeyShoot(7));
	}
}
