package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Blue3 extends CommandGroup {

    public Blue3(int type)
    {
       addSequential(new TurnLeftGearPlaceGroup());
    }
}
