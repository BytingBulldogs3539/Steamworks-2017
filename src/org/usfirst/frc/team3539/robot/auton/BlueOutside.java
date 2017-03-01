package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueOutside extends CommandGroup {

    public BlueOutside(int type)
    {
       addSequential(new TurnLeftGearPlaceGroup());
    }
}
