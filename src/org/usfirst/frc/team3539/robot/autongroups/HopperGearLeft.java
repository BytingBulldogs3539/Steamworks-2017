package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperGearLeft extends CommandGroup
{

	public HopperGearLeft()
	{
		addSequential(new GearLeftGroup());
               
		addSequential(new AutoWait(1));
		
		addSequential(new AutonDriveOld(-40));
		
		
		if(RobotMap.onBlueSide)
		    addSequential(new AutonTurn(-40));
		else
	        addSequential(new AutonTurn(40));
		
		addSequential(new AutonDriveOld(-50));

	}
}
