package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.SetGearCamera;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionTest extends CommandGroup
{
	public VisionTest()
	{
		addSequential(new SetGearCamera());
		addSequential(new AutonTurn(0, 1));//Robot.raspberry.getAngle()*-1

	}
}
