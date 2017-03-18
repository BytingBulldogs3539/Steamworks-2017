package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AllianceSwitcherCommand extends Command {

    private boolean onBlue;
    
    public AllianceSwitcherCommand(boolean onBlue)
    {
        this.onBlue = onBlue;
        RobotMap.onBlueSide = onBlue;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	System.out.println("onBlue: " + RobotMap.onBlueSide);
        RobotMap.onBlueSide = onBlue;
        System.out.println("onBlue: " + RobotMap.onBlueSide);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
