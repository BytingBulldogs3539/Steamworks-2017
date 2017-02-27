package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Red3 extends CommandGroup {

    public Red3(int type)
    {
    	addSequential(new TurnRightGearPlaceGroup());
    }
}
