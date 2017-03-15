package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootOutsideGroup extends CommandGroup
{

    public ShootOutsideGroup()
    {

        if (RobotMap.onBlueSide)
        {
            addSequential(new GearLeftGroup());
            addSequential(new AutonTurn(120));
        }
        else
        {
            //addSequential(new GearRightGroup());
           // addSequential(new AutonTurn(-120));
        }
        
        addSequential(new JoeyShoot(7));
    }
}
