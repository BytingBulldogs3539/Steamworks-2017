package org.usfirst.frc.team3539.robot.autonscurves;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.HoodReset;
import org.usfirst.frc.team3539.robot.autoncommands.SetShootCamera;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;




public class BlueCurveHopper extends CommandGroup {

    public BlueCurveHopper() {
    	addParallel(new HoodReset(3));
    	addParallel(new SetShootCamera());
     	
    	addSequential(new SuperDriveAuton(80, .3, 0, false, 3));

    	addSequential(new SuperDriveAuton(-20, -.3, 0, true, 2));

		addSequential(new JoeyShoot(12));
    }
}