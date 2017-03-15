package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
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
		
		addSequential(new AutonDrive(-40, 1));
		
		
		if(RobotMap.onBlueSide)
		    addSequential(new AutonTurn(-40));
		else
	        addSequential(new AutonTurn(40));
		
		addSequential(new AutonDrive(-50, 1));

	}
}
