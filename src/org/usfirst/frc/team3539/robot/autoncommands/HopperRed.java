package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperRed extends CommandGroup {

    public HopperRed() {
        addSequential(new AutonDrive(60));
        addSequential(new AutonTurn(90));
        addSequential(new AutonDriveOld(-40));
        addSequential(new AutoWait(3));
        addSequential(new AutonDrive(40));
        addSequential(new AutonTurn(-60));
        
		addSequential(new AutoAim());
		addSequential(new JoeyShoot(10));
    }
}
