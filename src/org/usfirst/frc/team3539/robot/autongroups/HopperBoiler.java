package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperBoiler extends CommandGroup
{

    public HopperBoiler()
    {

        addSequential(new AutonDrive(-93, 1)); //negative bc we start backwards

        if (RobotMap.onBlueSide)
        {
            addSequential(new AutonTurn(-90));
        }
        else
        {
            addSequential(new AutonTurn(90));
        }
      
        addSequential(new AutonDrive(-60, 1));
        
        addSequential(new AutoWait(5));
        
        addSequential(new AutonDrive(20, 1));

        if (RobotMap.onBlueSide)
        {
            addSequential(new AutonTurn(-80));
        }
        else
        {
            addSequential(new AutonTurn(80));
        }
        
        if(RobotMap.isVisionTracking)
        {
            addSequential(new JoeyShoot(7));
        }
        else
        {
            //hard code value
        }
        
    }
}
