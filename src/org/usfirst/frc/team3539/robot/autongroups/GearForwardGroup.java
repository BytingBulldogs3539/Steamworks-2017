package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveGentle;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3539.robot.utilities.*;

/**
 *
 */
public class GearForwardGroup extends CommandGroup
{
	public GearForwardGroup()
	{

		addSequential(new AutonDrive(50)); // orig. speedcap = 1

		addSequential(new AutonDriveGentle(20, .4)); // orig. speedcap = .4

		
		addSequential(new AutonGearOpen());

		addSequential(new AutoWait(1));
		
		addSequential(new AutonDriveGentle(-20, .5)); // orig. speedcap = 1

		addSequential(new AutonGearClose());
	}
}
