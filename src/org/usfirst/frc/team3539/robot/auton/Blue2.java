package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Blue2 extends CommandGroup {

    public Blue2(int type)
    {
       addSequential(new ForwardGearPlaceGroup());
    }
}
