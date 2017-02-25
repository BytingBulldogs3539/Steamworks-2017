package org.usfirst.frc.team3539.robot.auton;

import org.usfirst.frc.team3539.robot.commands.AutonMain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTeamCommand extends Command {

	private int team;
    public SetTeamCommand(int team) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.team = team;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	AutonMain.color = team;
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
