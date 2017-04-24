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

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class GearLeftGroup extends CommandGroup
{
	public GearLeftGroup()
	{
		addParallel(new SetGearCamera());
		
		addParallel(new HoodReset(3));
		
		//addParallel(new SetGearCamera());
		//addParallel(new SetShootCamera());

		//addParallel(new HoodReset(3));
		
		addSequential(new SuperDriveAuton(100, -.6, 50, false, 4, 15));
		
		//addSequential(new AutonDrive(RobotMap.whiteLineDistance+5, 3));

		addSequential(new AutoWait(.7));

		//addSequential(new AutonTurn(-RobotMap.sidePegTurn));
		
		
		addSequential(new AutonDrive(RobotMap.sidePegDistance+8,true));

		//addSequential(new AutoWait(1));
		
		addParallel(new SetShootCamera());
		
		addSequential(new AutonGearOpen());

		//addSequential(new AutoWait(1));

		//addSequential(new AutonDrive(-20));

		//addSequential(new AutonGearClose());
	}
}
