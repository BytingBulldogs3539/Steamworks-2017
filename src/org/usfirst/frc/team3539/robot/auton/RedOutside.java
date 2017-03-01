package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedOutside extends CommandGroup {

    public RedOutside(int type)
    {
    	addSequential(new TurnRightGearPlaceGroup());
    	addSequential(new ShootOutsideGroup());
    }
}