package org.usfirst.frc.team3539.robot.autoncommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperRight extends CommandGroup {

    public HopperRight() {
        addSequential(new AutonDrive(100));
        addSequential(new AutonTurn(90));
        addSequential(new AutonDrive(40));
        addSequential(new AutoWait(3));
        addSequential(new AutonDrive(-40));
        addSequential(new AutonTurn(45));
    }
}
