package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.HoodReset;
import org.usfirst.frc.team3539.robot.autoncommands.SetGearCamera;
import org.usfirst.frc.team3539.robot.autoncommands.SetShootCamera;
import org.usfirst.frc.team3539.robot.autonscurves.SuperDriveAuton;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedChickenHopper extends CommandGroup
{
	public RedChickenHopper() 
	{
		addParallel(new SetGearCamera());
		addParallel(new SetShootCamera());

		addParallel(new HoodReset(3));

		addSequential(new SuperDriveAuton(100, -.6, 50, false, 4, RobotMap.whiteLineDistance - 40));
		addParallel(new AutonGearOpen());

		addSequential(new SuperDriveAuton(-125, -.4, 55, false, 2, 0));

		addParallel(new AutonGearClose());

		addSequential(new AutonDrive(-60, 2.5, true));

		addSequential(new SuperDriveAuton(60, .5, 50, false, 2, 20));

		addSequential(new AutoWait(.25));// wait to pull good distance averages

		addSequential(new AutonTurn(0, 2));// vision track turn

		addSequential(new JoeyShoot(7, 400));// shoot
	}
}
