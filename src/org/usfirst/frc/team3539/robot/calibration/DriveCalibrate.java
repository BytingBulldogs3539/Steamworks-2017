package org.usfirst.frc.team3539.robot.calibration;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveCalibrate extends CommandGroup
{

	public DriveCalibrate()
	{
		addSequential(new AutonDriveOld(100));
		addSequential(new AutoWait(2));
		
		addSequential(new AutonDriveOld(-100));
		addSequential(new AutoWait(2));
		
		addSequential(new AutonDriveOld(25));
		addSequential(new AutoWait(2));
		
		addSequential(new AutonDriveOld(-25));
	}
}
