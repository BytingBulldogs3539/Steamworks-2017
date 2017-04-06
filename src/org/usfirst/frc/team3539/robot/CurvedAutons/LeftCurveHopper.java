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
public class LeftCurveHopper extends CommandGroup {

    public LeftCurveHopper() {
    	addParallel(new HoodReset(3));
     	Robot.raspberry.setCamera(RobotMap.shooterCamera);   
     	
    	addSequential(new SuperDriveAuton(80, -.3, 0, false, 3));

    	addSequential(new SuperDriveAuton(20, -.3, 0, true, 2));

		addSequential(new JoeyShoot(12));
    }
}



//
//public class LeftCurveHopper extends CommandGroup {
//
//    public LeftCurveHopper() {
//    	addParallel(new HoodReset(3));
//     	Robot.raspberry.setCamera(RobotMap.shooterCamera);   
//     	
//    	addSequential(new SuperDriveAuton(80, -.3, 0, false, 3));
//
//    	addSequential(new SuperDriveAuton(20, -.3, 0, true, 2));
//
//		addSequential(new JoeyShoot(12));
//    }
//}
