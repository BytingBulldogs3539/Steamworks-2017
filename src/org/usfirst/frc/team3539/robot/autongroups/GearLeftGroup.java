package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveOld;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDriveGentle;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearClose;
import org.usfirst.frc.team3539.robot.autoncommands.AutonGearOpen;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearLeftGroup extends CommandGroup
{
    public GearLeftGroup()
    {
        addSequential(new AutonDriveOld(107)); //comp: 88

        if (RobotMap.onBlueSide)
            addSequential(new AutonTurn(60));
        else
            addSequential(new AutonTurn(-60)); // original

        addSequential(new AutonDriveGentle(30, .5));

        addSequential(new AutonGearOpen());

        addSequential(new AutoWait(1));

        addSequential(new AutonDriveOld(-30));

        addSequential(new AutonGearClose());
    }
}
