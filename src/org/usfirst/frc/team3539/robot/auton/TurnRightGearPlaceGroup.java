package org.usfirst.frc.team3539.robot.auton;

import org.usfirst.frc.team3539.robot.commands.AutonDrive;
import org.usfirst.frc.team3539.robot.commands.AutonToggleGearCommand;
import org.usfirst.frc.team3539.robot.commands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnRightGearPlaceGroup extends CommandGroup
{

	public TurnRightGearPlaceGroup()
	{
		addSequential(new AutonDrive(5));
		addSequential(new AutonTurn(5));
		addSequential(new AutonDrive(5));
		addSequential(new AutonToggleGearCommand());
	}
}
