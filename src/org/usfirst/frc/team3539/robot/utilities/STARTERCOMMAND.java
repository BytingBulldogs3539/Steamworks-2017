package org.usfirst.frc.team3539.robot.utilities;

/**
 * @author Ivan III
 */

public class STARTERCOMMAND extends BulldogCommand
{

	public STARTERCOMMAND() // Constructor; Called when you deploy the code
	{
		// Insert the exact command name here ex.
		// "DriveCommand"
		super("STARTERCOMMAND");

		// requires(Robot.subsystem); //Put the subsystem the command uses here
	}

	protected void initialize()
	{
		super.initialize(); //"super" needs to be the first line

		// Put methods here to be ran once
		// (if STARTERCOMMAND is called)

		// Robot.subsystem.method
	}

	protected void execute()
	{
		super.execute();

		// methods here will run every 20ms while the isFinished() is false
	}

	protected boolean isFinished()
	{
		// Return true when the command should
		// terminate

		// if it only returns false it will eventually be
		// interupted by another command

		// if(commandShouldEnd)
		// return true;
		// else
		return false;
	}

	protected void end()
	{
		super.end();
		// put methods here to be run when the command is interupted or when the
		// isFinished() returns true
	}

	protected void interrupted()
	{
		super.interrupted();
		// called when another command in the same subsystem is initialized
		// two commands of the same subsystem cannot run at the same time
		
		end();
	}
}
