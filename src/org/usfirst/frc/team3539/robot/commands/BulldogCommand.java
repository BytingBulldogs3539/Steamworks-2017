package org.usfirst.frc.team3539.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

// Standard Java libraries
import java.text.SimpleDateFormat;

/**
 * Base class for all Commands for Team 3539
 * 
 * @author Programming team
 *
 */
public abstract class BulldogCommand extends Command
{
	public BulldogCommand(String name)
	{
		super(name);
	}
	
	/**
	 * Initialize the command
	 * 
	 * @param name - Name of the command being initialized
	 */
	protected void initialize(String name)
	{
		super.initialize();
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		System.out.println("Starting " + name + " at [" + timeStamp + "]");
	}
	
	/**
	 * Clean up
	 * 
	 * @param name - Name of the Command that is ended
	 */
	protected void end(String name)
	{
		super.end();
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		System.out.println("  Ending " + name + " at [" + timeStamp + "]");
	}
}
