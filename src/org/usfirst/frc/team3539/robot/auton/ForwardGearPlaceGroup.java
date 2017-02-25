package org.usfirst.frc.team3539.robot.auton;

import org.usfirst.frc.team3539.robot.commands.AutonDrive;
import org.usfirst.frc.team3539.robot.commands.AutonToggleGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardGearPlaceGroup extends CommandGroup
{
	public ForwardGearPlaceGroup()
	{
		addSequential(new AutonDrive(5));
		addSequential(new AutonToggleGearCommand());
	}
}
