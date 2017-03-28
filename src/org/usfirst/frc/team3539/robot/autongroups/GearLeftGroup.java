package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearLeftGroup extends CommandGroup
{
	public GearLeftGroup()
	{
		addSequential(new AutonDrive(RobotMap.whiteLineDistance, 3));

		addSequential(new AutonTurn(-RobotMap.sidePegTurn));

		addSequential(new AutonDrive(RobotMap.sidePegDistance));

		addSequential(new AutoWait(1));

		addSequential(new AutonGearOpen());

		addSequential(new AutoWait(1));

		addSequential(new AutonDrive(-RobotMap.sidePegDistance));

		addSequential(new AutonGearClose());
	}
}
