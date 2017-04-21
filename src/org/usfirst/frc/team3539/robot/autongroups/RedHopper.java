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
		addParallel(new HoodReset(3));

		addParallel(new SetShootCamera());

		addSequential(new AutonDrive(121, 2.5)); // initial; (118, 2.5) before, looks like 120/123 works better

		//addParallel(new SpinUp (1.5, 1000));//ready fly wheel

		// addSequential(new AutonTurn(-35, .7));//hit hopper (.7 seconds)
		addSequential(new AlphaTurn(-1, 1.5));

		//addSequential(new AutoWait(1));// wait for fill

		addSequential(new AutonTurn(0, 1.5));// vision track turn

		addSequential(new AutoWait(.5));// wait to pull good distance averages

		addSequential(new JoeyShoot(10, 400));// shoot
	}
}
