package org.usfirst.frc.team3539.robot.calibration;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;
import org.usfirst.frc.team3539.robot.commands.VisionAlineCommand;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveCalibrate extends CommandGroup
{

	public DriveCalibrate()
	{
		addSequential(new AutonDrive(100));
		addSequential(new AutoWait(2));
		
		addSequential(new AutonDrive(-100));
		addSequential(new AutoWait(2));
		
		addSequential(new AutonTurn(25));
		addSequential(new AutoWait(2));
		
		addSequential(new AutonTurn(-25));
	}
}
