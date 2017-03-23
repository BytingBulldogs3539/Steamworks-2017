package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueHopper extends CommandGroup
{

	public BlueHopper()
	{
		addSequential(new AutonDrive(80, 5));
		addSequential(new AutonTurn(91, 2));
		addSequential(new AutonDrive(-40, 3));
		addSequential(new AutoWait(3));
		addSequential(new AutonDrive(10, 2));
		addSequential(new AutonTurn(-75, 2));

		// addSequential(new AutoAim());
		addSequential(new JoeyShoot(false, 432.2746, 200, -3179.6704, 10));
	}
}
