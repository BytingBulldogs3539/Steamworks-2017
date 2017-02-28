package org.usfirst.frc.team3539.robot.auton;

import org.usfirst.frc.team3539.robot.commands.AutonDrive;
import org.usfirst.frc.team3539.robot.commands.AutonTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootInsideGroup extends CommandGroup
{

    public ShootInsideGroup()
    {
    	addSequential(new AutonDrive(70));
    	addSequential(new AutonTurn(90));
    	addSequential(new AutonDrive(27));
    	addSequential(new AutonTurn(45));
    	//(whatever tf it is to make it shoot)
    	
    }
}
