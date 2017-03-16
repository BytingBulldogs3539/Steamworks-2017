package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootMiddleGroup extends CommandGroup
{

    public ShootMiddleGroup()
    {
        addSequential(new GearForwardGroup());

        if (RobotMap.onBlueSide)
        {
            addSequential(new AutonTurn(100));
        }
        else
        {
            addSequential(new AutonTurn(-100));
        }

        if (RobotMap.isVisionTracking)
        {
            addSequential(new JoeyShoot(7));
        }
        else
        {
            // Hard code
        }
    }
}
