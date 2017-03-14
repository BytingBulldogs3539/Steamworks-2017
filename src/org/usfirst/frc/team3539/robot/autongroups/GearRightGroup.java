package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonHoodOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearRightGroup extends CommandGroup
{    
	public GearRightGroup()
	{
		addSequential(new AutonDrive(70));
		
		if(RobotMap.onBlueSide)
		    addSequential(new AutonTurn(-60));
		else
		    addSequential(new AutonTurn(60));

		addSequential(new AutonDrive(30));
		
		addSequential(new AutonGearOpen());
		addSequential(new AutonHoodOpen());

		addSequential(new AutoWait(2));

		addSequential(new AutonDrive(-20));

		addSequential(new AutonGearClose());
		addSequential(new AutonHoodClose());
	}
}
