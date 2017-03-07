package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
 


/**
 *
 */
public class ABShood extends Command
{    
    public double setpoint;
    public ABShood(double point)
    {
        setpoint = point;
        
    }

    protected void initialize()
    {
        
    }

    protected void execute()
    {
        if ()
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
        super.end();
    }

    protected void interrupted()
    {
        end();
    }
}
