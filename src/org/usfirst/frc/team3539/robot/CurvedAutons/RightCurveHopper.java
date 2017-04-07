package org.usfirst.frc.team3539.robot.CurvedAutons;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.HoodReset;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;




public class RightCurveHopper extends CommandGroup {

    public RightCurveHopper() {
    	addParallel(new HoodReset(3));
     	Robot.raspberry.setCamera(RobotMap.shooterCamera);   
     	
    	addSequential(new SuperDriveAuton(80, .3, 90, false, 3));

    	addSequential(new SuperDriveAuton(-20, -.3, -90, true, 2));

		addSequential(new JoeyShoot(12,true));//true for pulse
    }
}