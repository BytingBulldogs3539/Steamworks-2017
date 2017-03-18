package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoAim;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DirtyDanTheMiddleManBlue extends CommandGroup
{

	public DirtyDanTheMiddleManBlue()
	{
		addSequential(new GearMiddleGroup());

		addSequential(new AutonDrive(-40, 1));

		addSequential(new AutonTurn(75));
		
		addSequential(new AutoAim());

		addSequential(new JoeyShoot(7));
	}
}
