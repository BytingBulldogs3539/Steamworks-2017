package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.auton.BlueOutside;
import org.usfirst.frc.team3539.robot.auton.BlueMiddle;
import org.usfirst.frc.team3539.robot.auton.BlueInside;
import org.usfirst.frc.team3539.robot.auton.RedOutside;
import org.usfirst.frc.team3539.robot.auton.RedMiddle;
import org.usfirst.frc.team3539.robot.auton.RedInside;

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
    	if(color == 1 && pos == 1) new BlueOutside(type);
    	if(color == 1 && pos == 2) new BlueMiddle(type);
    	if(color == 1 && pos == 3) new BlueInside(type);
    	if(color == 2 && pos == 1) new RedOutside(type);
    	if(color == 2 && pos == 2) new RedMiddle(type);
    	if(color == 2 && pos == 3) new RedInside(type);
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
