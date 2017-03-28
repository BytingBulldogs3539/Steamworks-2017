package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.*;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedHopper extends CommandGroup
{

	public RedHopper()
	{
		addSequential(new SetShootCamera());
		
		addSequential(new AutonDrive(RobotMap.farHopperDistance, 5));
		
		addSequential(new AutonTurn(-RobotMap.hopperTurn));
		
		addSequential(new AutonDrive(-65, 4));
		
		addSequential(new AutonDrive(40, 2));
		
		addSequential(new AutonTurn(RobotMap.hopperShootTurn, 1.5));

		addSequential(new AutoWait(RobotMap.visionWait));

		addSequential(new AutonTurn(0, 1));
		
		addSequential(new JoeyShoot(7));
	}
}
