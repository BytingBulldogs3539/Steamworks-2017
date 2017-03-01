package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueInside extends CommandGroup {

    public BlueInside(int type)
    {
       addSequential(new TurnRightGearPlaceGroup());
    }
}