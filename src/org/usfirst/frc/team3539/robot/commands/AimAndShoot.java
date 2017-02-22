package org.usfirst.frc.team3539.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AimAndShoot extends CommandGroup {

    public AimAndShoot() {
    	//addSequential(new SetPointCommand(499));
    	addSequential(new ShooterCommand());
    }
}
