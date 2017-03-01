package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedMiddle extends CommandGroup {

    public RedMiddle(int type)
    {
    	addSequential(new ForwardGearPlaceGroup());
    	addSequential(new ShootMiddleGroup());
    }
}
