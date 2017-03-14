package org.usfirst.frc.team3539.robot.autongroups;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.autoncommands.AutoWait;
import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShooterRight extends CommandGroup
{

    public ShooterRight()
    {
        // Log the various steps of this auto
        addSequential(new AutonDrive(95));
        
        if(RobotMap.onBlueSide) addSequential(new AutonTurn(90));
        else addSequential(new AutonTurn(-90));
        
        addSequential(new AutonDrive(-45));

        addSequential(new AutoWait(3));

        addSequential(new AutonDrive(20));
        
        if(RobotMap.onBlueSide) addSequential(new AutonTurn(-65));
        else addSequential(new AutonTurn(65));
        

        addSequential(new JoeyShoot(false, Robot.raspberry.getneededHoodAngle(), 250.0,
                Robot.raspberry.getneededShooterRPM(), 5.0));

    }
}
