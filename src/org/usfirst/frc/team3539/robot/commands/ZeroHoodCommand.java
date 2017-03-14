package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroHoodCommand extends Command
{
    private int ticks;

    public ZeroHoodCommand()
    {
        requires(Robot.hoodSubsystem);
        this.ticks = 0;
    }

    protected void initialize()
    {
        System.out.println("Zeroed Hood");
    }

    protected void execute()
    {
        Robot.hoodSubsystem.setHoodpower(.2);
        ticks++;
    }

    protected boolean isFinished()
    {
        System.out.println("shooterHoodMotorCurrent: " + Robot.hoodSubsystem.shooterHoodMotor.getOutputCurrent());
        
        if (Robot.hoodSubsystem.shooterHoodMotor.getOutputCurrent() >= 10 || ticks > 1000)
        {
            return true;
        }
        return false;
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
