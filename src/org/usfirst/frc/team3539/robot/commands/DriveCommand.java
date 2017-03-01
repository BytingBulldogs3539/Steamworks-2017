package org.usfirst.frc.team3539.robot.commands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;


/**
 *
 */
public class DriveCommand extends BulldogCommand
{
	private boolean latch = false;
	public DriveCommand()
	{
		super("DriveCommand");
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		System.out.println("DriveCommand was initialized");
		Robot.driveTrain.talonControlVBus();
	}

	protected void execute()
	{
		if(Robot.oi.onebuttona.get() && !latch == true){//not sure if the true is needed but leave until tested 
			latch = true;
			Robot.driveTrain.changeGears();
		}else{
		latch = false;
		}
		
		Robot.driveTrain.driveArcade(Robot.oi.controller1.getRawAxis(RobotMap.Y_AxisL),
				Robot.oi.controller1.getRawAxis(RobotMap.X_AxisR));
	}

	protected boolean isFinished()
	{
		return true;
	}

	protected void end()
	{
		System.out.println("DriveCommand was ended");
	}

	protected void interrupted()
	{
		end();
	}
}
