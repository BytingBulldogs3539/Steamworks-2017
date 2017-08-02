package org.usfirst.frc.team3539.robot.utilities;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3539.robot.Robot;
// Standard Java libraries
import org.usfirst.frc.team3539.robot.utilities.*;

/**
 * Base class for all Commands for Team 3539
 * 
 * @author Programming team
 *
 */
public abstract class BulldogCommand extends Command
{
	String name;
	
	public BulldogCommand(String name)
	{
		super(name);
		this.name = name;
	}
	
	/**
	 * Initialize the command
	 * 
	 * @param name - Name of the command being initialized
	 */
	protected void initialize(String name)
	{
		super.initialize();
		Robot.bl.logCommand(this.name + " Initialized");
		//BulldogLogger.getInstance().logDebug("Initializing " + name);
	}
	
	/**
	 * Clean up
	 * 
	 * @param name - Name of the Command that is ended
	 */
	protected void end(String name)
	{
		super.end();
		Robot.bl.logCommand(this.name + " Ended");

		//BulldogLogger.getInstance().logDebug("Ending " + name);
	}
}
