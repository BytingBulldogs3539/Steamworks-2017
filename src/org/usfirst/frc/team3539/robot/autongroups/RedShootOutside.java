package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.SetShootCamera;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedShootOutside extends CommandGroup
{

	public RedShootOutside()
	{
		addSequential(new GearRightGroup());

		addSequential(new SetShootCamera());
	
		addSequential(new AutonTurn(-130, 4));

		addSequential(new AutonTurn(0,1));

		addSequential(new JoeyShoot(7));
	}
}
