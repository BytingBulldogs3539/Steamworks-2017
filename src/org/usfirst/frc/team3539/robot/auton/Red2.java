package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Red2 extends CommandGroup {

    public Red2(int type)
    {
    	addSequential(new ForwardGearPlaceGroup());
    }
}
