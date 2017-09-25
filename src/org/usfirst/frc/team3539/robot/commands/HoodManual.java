
package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.utilities.BulldogCommand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoodManual extends BulldogCommand
{
    public HoodManual()
    {
		super("HoodManual");

    }

    protected void initialize()
    {
        System.out.println("HoodManualInit");
    }

    protected void execute()
    {
        if (Robot.oi.controller2.getRawAxis(RobotMap.Y_AxisL) > 0)
        {
            Robot.hoodSubsystem.setHoodpower(-.1);
        }
        if (Robot.oi.controller2.getRawAxis(RobotMap.Y_AxisL) < 0)
        {
            Robot.hoodSubsystem.setHoodpower(.1);
        }
        if (Robot.oi.controller2.getRawAxis(RobotMap.Y_AxisL) == 0)
        {
            Robot.hoodSubsystem.setHoodpower(0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return !Robot.oi.twobuttonLS.get();
    }

    protected void end()
    {
    	super.end("hoodSubsystem");
        Robot.hoodSubsystem.setHoodpower(0);
    }

    protected void interrupted()
    {
        end();
    }
}