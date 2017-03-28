package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ZeroHoodCommand extends Command
{
    public ZeroHoodCommand()
    {
        requires(Robot.hoodSubsystem);
    }

    protected void initialize()
    {
        System.out.println("Zeroed Hood");
    }

    protected void execute()
    {
        Robot.hoodSubsystem.setHoodpower(.2);
    }

    protected boolean isFinished()
    {
        return !Robot.oi.zeroHoodButton.get();
    }

    protected void end()
    {
        Robot.hoodSubsystem.setHoodpower(0);
        Robot.hoodSubsystem.zeroHoodEncoders();
    }

    protected void interrupted()
    {
        end();
    }
}
