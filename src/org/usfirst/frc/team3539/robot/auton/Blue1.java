package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Blue1 extends CommandGroup {

    public Blue1(int type)
    {
       addSequential(new TurnRightGearPlaceGroup());
    }
}
