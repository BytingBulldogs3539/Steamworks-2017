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
public class AutonSlamFest extends CommandGroup
{
	public AutonSlamFest()
	{	
		addParallel(new SetGearCamera());
		addParallel(new SetShootCamera());

		addParallel(new HoodReset(3));
		
		addSequential(new SuperDriveAuton(100, -.65, 45, false, 4, RobotMap.whiteLineDistance-20));
		addParallel(new AutonGearOpen());
		
		addSequential(new SuperDriveAuton(-125, -.40, 35, false, 5, 0));

		addParallel(new AutonGearClose());
		
	//	addSequential(new AutoWait(2));

		//addSequential(new AutonDrive(RobotMap.sidePegDistance, true));

		addSequential(new AutoWait(.5));

		//addSequential(new AutonGearOpen());
		
		//addSequential(new SuperDriveAuton(-120, -.40, 30, false, 5, 0));

//
		addSequential(new AutonDrive(-60,1.5));

		addSequential(new SuperDriveAuton(60, .5, 50, false, 4, 20));

		addSequential(new AutoWait(.4));// wait to pull good distance averages

		addSequential(new AutonTurn(0, 2));// vision track turn

		addSequential(new AutoWait(.4));// wait to pull good distance averages

		addSequential(new JoeyShoot(7, 400));// shoot



	}
}
