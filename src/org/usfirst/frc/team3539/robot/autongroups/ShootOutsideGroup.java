package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOutsideGroup extends CommandGroup
{

	public ShootOutsideGroup()
	{
		addSequential(new GearOutsideGroup());

		if (RobotMap.onBlueSide)
		{
			addSequential(new AutonTurn(120));
		}
		else
		{
			addSequential(new AutonTurn(-120));
		}

		if (RobotMap.isVisionTracking)
		{
			addSequential(new JoeyShoot(7));
		}
		else
		{
			// Hard code
		}
	}
}
