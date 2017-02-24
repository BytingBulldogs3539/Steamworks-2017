package org.usfirst.frc.team3539.robot.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *  keep off my lawn -d^2
 */
public class Blue1 extends CommandGroup {

    public Blue1(String action)
    {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	if(action.toLowerCase().contains("cross")) // pseudo wrestling
    	{
    		addSequential(new squidward());
    	}
    	if(action.toLowerCase().contains("gear"))
    	{
    		addSequential(new turnt);
    		addSequential(new squidward);
    		addSequential(new gearvomit);
    	}
    	if(action.toLowerCase().contains("shoot"))
    	{
    		addSequential(new back);
    		addSequential(new turnt);
    		addSequential(new squidward);
    		addSequential(new turnt);
    		addSequential(new squidward);
    		addSequential(new turnt);
    		addSequential(new shooty);
    	}
    	
    }
}
