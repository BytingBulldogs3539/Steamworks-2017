package org.usfirst.frc.team3539.robot.autonscurves;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.HoodReset;
import org.usfirst.frc.team3539.robot.autoncommands.SetShootCamera;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedCurveHopper extends CommandGroup {

    public RedCurveHopper() {
    	addParallel(new HoodReset(3));
    	
     	addParallel(new SetShootCamera());
     	
    	//addSequential(new SuperDriveAuton(145, -.6, 15, false, 3, 85));

    	addSequential(new SuperDriveAuton(135, .7, 9, false, 3, 0));

    	addSequential(new SuperDriveAuton(110, -.6, 30, false,3, 60)); // 67 b4 test

    	addSequential(new AutonTurn(-15,.5));
    
    	addSequential(new AutonTurn(0,2));
    	
    	addSequential(new AutoWait(.5));
    	
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
