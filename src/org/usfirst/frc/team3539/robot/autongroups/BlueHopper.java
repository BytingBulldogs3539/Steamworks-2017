package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.HoodReset;
import org.usfirst.frc.team3539.robot.autoncommands.SetShootCamera;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueHopper extends CommandGroup
{

	public BlueHopper()
	{
		addParallel(new HoodReset(3));
		
		addSequential(new SetShootCamera());
		
		addSequential(new AutonDrive(RobotMap.farHopperDistance, 5));
		
		addSequential(new AutonTurn(RobotMap.hopperTurn));
		
		addSequential(new AutonDrive(-65, 2.5));
		
		addSequential(new AutonDrive(30, 1));
		
		addSequential(new AutonTurn(-RobotMap.hopperShootTurn, 1));

		addSequential(new AutoWait(RobotMap.visionWait));

		addSequential(new AutonTurn(0, 1));
		
		addSequential(new JoeyShoot(10, 300));
	}
}
