package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.auton.Blue1;
import org.usfirst.frc.team3539.robot.auton.Blue2;
import org.usfirst.frc.team3539.robot.auton.Blue3;
import org.usfirst.frc.team3539.robot.auton.Red1;
import org.usfirst.frc.team3539.robot.auton.Red2;
import org.usfirst.frc.team3539.robot.auton.Red3;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonMain extends Command
{
	public static int color;
	public static int pos;
	public static int type;
	
    public AutonMain(int color, int pos, int type)
    {
    	super("AutonMain");
    	this.color = color;
    	this.pos = pos;
    	this.type = type;
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() /**Don't forget the break statements*/
    {
    	if(color == 1 && pos == 1) new Blue1(type);
    	if(color == 1 && pos == 2) new Blue2(type);
    	if(color == 1 && pos == 3) new Blue3(type);
    	if(color == 2 && pos == 1) new Red1(type);
    	if(color == 2 && pos == 2) new Red2(type);
    	if(color == 2 && pos == 3) new Red3(type);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return true;
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
