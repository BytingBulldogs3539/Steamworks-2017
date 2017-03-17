package org.usfirst.frc.team3539.robot.autongroups;


import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Perform GearLeftGroup and then pull back and shoot
 * 
 * @author Cameron
 *
 */
public class SuperGearLeftGroup extends CommandGroup
{
	public SuperGearLeftGroup()
	{
	    addSequential(new GearInsideGroup());
	    if(RobotMap.isVisionTracking)
	    {
	    	addSequential(new JoeyShoot(true, Robot.raspberry.getneededHoodAngle(), 250.0,
                Robot.raspberry.getneededShooterRPM(), 5.0));
	    }
	    else
	    {
	    	//Hard code
	    }
	}

}