package org.usfirst.frc.team3539.robot.CurvedAutons;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.HoodReset;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftCurvePeg extends CommandGroup {

    public LeftCurvePeg() {
    	double curve = -.65;
    	addParallel(new HoodReset(3));
    	
    	addSequential(new AutonDrive(30));
    	addSequential(new SuperDriveAuton(85, curve, 55, true, 6));
    	Robot.raspberry.setCamera(RobotMap.shooterCamera);
    	
    	addSequential(new AutonGearOpen());
    	
    	addSequential(new SuperDriveAuton(-40, curve, 0, true, 2));
    	addParallel(new AutonGearClose());

		addSequential(new JoeyShoot(7));
    }
}
