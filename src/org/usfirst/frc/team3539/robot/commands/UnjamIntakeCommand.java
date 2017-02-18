package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UnjamIntakeCommand extends Command {

    public UnjamIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.intake.lockOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	System.out.println("ran");
    	Robot.intake.setMotorPower(RobotMap.intakeUnjamSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!Robot.oi.twobumperl.get());
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.intake.setMotorPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
