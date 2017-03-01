package org.usfirst.frc.team3539.robot.auton;

import org.usfirst.frc.team3539.robot.commands.AutonDrive;
import org.usfirst.frc.team3539.robot.commands.AutonToggleGearCommand;
import org.usfirst.frc.team3539.robot.commands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnLeftGearPlaceGroup extends CommandGroup
{
	public TurnLeftGearPlaceGroup()
	{
		addSequential(new AutonDrive(80)); // A little further
		addSequential(new AutonTurn(-60));
		addSequential(new AutonDrive(50));
		addSequential(new AutonToggleGearCommand());
	}
}
