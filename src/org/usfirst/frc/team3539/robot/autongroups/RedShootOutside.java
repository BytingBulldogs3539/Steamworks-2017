package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.SetShootCamera;
import org.usfirst.frc.team3539.robot.autonscurves.SuperDriveAuton;
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

		addSequential(new SetShootCamera());

		addSequential(new SuperDriveAuton(-80, -.77, 110, false, 3,-100));
		
		addSequential(new AutonGearClose());
		
		//addSequential(new AutonDrive(-30, 2));


		//addSequential(new AutonTurn(-RobotMap.outsideShootTurn, 4));

		addSequential(new AutoWait(.75));

		addSequential(new AutonTurn(0, 2));

		//addSequential(new AutoWait(.5));
		
		addSequential(new JoeyShoot(7));
	}
}
