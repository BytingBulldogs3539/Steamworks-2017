package org.usfirst.frc.team3539.robot.CurvedAutons;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.HoodReset;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightCurvePeg extends CommandGroup {

    public RightCurvePeg() {
    	double curve = .75;
    	addParallel(new HoodReset(3));
    	
    	addSequential(new SuperDriveAuton(125, curve, 0, true, 5));
    	Robot.raspberry.setCamera(RobotMap.shooterCamera);
    	
    	addSequential(new AutonGearOpen());
    	
    	curve = .9;
    	addSequential(new SuperDriveAuton(-50, -curve, 0, true, 2));
    	addParallel(new AutonGearClose());

		addSequential(new JoeyShoot(7));
    }
}
