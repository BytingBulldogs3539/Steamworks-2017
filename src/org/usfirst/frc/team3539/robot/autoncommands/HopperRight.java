package org.usfirst.frc.team3539.robot.autoncommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperRight extends CommandGroup {

    public HopperRight() {
        addSequential(new AutonDriveOld(-60));
        addSequential(new AutonTurn(90));
        addSequential(new AutonDriveOld(-40));
        addSequential(new AutoWait(3));
        addSequential(new AutonDriveOld(40));
        addSequential(new AutonTurn(45));
    }
}
