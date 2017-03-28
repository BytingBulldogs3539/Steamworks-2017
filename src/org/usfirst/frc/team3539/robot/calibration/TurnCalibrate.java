package org.usfirst.frc.team3539.robot.calibration;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnCalibrate extends CommandGroup
{

	public TurnCalibrate()
	{
		addSequential(new AutonTurn(90));
		addSequential(new AutoWait(1));
		
		addSequential(new AutonTurn(-90));
		addSequential(new AutoWait(1));

		addSequential(new AutonTurn(60));
		addSequential(new AutoWait(1));
		
		addSequential(new AutonTurn(-60));
		addSequential(new AutoWait(1));
		
		addSequential(new AutonTurn(25));
		addSequential(new AutoWait(1));
		
		addSequential(new AutonTurn(-25));
		addSequential(new AutoWait(1));
	}
}
