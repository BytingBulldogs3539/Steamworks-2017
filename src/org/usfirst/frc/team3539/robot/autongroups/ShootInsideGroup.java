package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootInsideGroup extends CommandGroup
{

    public ShootInsideGroup()
    {
        if (RobotMap.onBlueSide)
        {
            addSequential(new GearRightGroup());
        }
        else
        {
            addSequential(new GearLeftGroup());
        }

        if(RobotMap.isVisionTracking)
        {
        	//addSequential(new JoeyShoot(false, Robot.raspberry.getneededHoodAngle(), 250.0,
            //    Robot.raspberry.getneededShooterRPM(), 5.0));
            addSequential(new JoeyShoot(7));
        }
        else
        {
        	//Hard code
        }
    	
    }
}
