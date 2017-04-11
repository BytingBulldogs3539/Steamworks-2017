package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.CurvedAutons.SuperDriveAuton;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.SetShootCamera;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueShootInside extends CommandGroup
{

	public BlueShootInside()
	{
		addSequential(new GearRightGroup());

		//addSequential(new SuperDriveAuton(-40, .77, -40, false, 3,-100));
		
		addSequential(new AutonDrive(-20,1));
		
		addSequential(new AutonTurn(-20, 1));

		addSequential(new AutonGearClose());
		
		addSequential(new SetShootCamera());

		//addSequential(new AutoWait(RobotMap.visionWait));
		
		addSequential(new AutonTurn(0, 1));
		
		//addSequential(new AutoWait(.5));
		
		addSequential(new JoeyShoot(7));
	}
}
