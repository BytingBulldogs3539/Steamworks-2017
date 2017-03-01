package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedInside extends CommandGroup {

    public RedInside(int type)
    {
    	addSequential(new TurnLeftGearPlaceGroup());
    	addSequential(new ShootInsideGroup());
    }
}
