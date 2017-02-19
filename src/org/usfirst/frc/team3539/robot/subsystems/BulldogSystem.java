package org.usfirst.frc.team3539.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class BulldogSystem extends Subsystem
{
	public BulldogSystem(String name)
	{
		super(name);
	}

	public abstract void Update();

	public abstract void SmartInit();

	public void initDefaultCommand()
	{
	}
}
