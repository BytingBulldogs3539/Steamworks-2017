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
public class RedShootInside extends CommandGroup {

	public RedShootInside() {

		addSequential(new GearLeftGroup());
		addParallel(new SetShootCamera());

		addSequential(new AutonDrive(-80, .5));

		addSequential(new AutonTurn(20, 1));

		addSequential(new AutonGearClose());

		// addSequential(new AutoWait(RobotMap.visionWait));

		// addSequential(new AutonTurn(15,1));

		addSequential(new AutonTurn(0, 2));

		// addSequential(new AutoWait(.5));

		addSequential(new JoeyShoot(5));

		addSequential(new AutoWait(.5));

		addSequential(new AutonTurn(40, 2));

		// addSequential(new AutonDrive(150,3));

		addSequential(new SuperDriveAuton(200, -.6, 30, false, 3, 90));
	}
}
