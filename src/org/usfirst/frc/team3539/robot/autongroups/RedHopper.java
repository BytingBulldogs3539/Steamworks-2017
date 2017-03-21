package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.autoncommands.AutoAim;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedHopper extends CommandGroup
{

	public RedHopper()
	{
		addSequential(new AutonDrive(80, 1));
		addSequential(new AutonTurn(-91));
		addSequential(new AutonDrive(-40, 1));
		addSequential(new AutoWait(3));
		addSequential(new AutonDrive(10, 1));
		addSequential(new AutonTurn(80));

		//addSequential(new AutoAim());
		addSequential(new JoeyShoot(false,425.2746, 200, -3150.6704, 10));
	}
}
