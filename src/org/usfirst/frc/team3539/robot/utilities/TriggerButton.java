package org.usfirst.frc.team3539.robot.utilities;

import org.usfirst.frc.team3539.robot.commands.IntakeCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class TriggerButton
{
	private int triggerAxis;
	private Joystick controller;
	private Command command;
	public boolean triggerValue;
	
	public TriggerButton(int axis, Joystick joystick, Command command)
	{
		triggerAxis = axis;
		controller = joystick;
		triggerValue = false;
		this.command = command;
	}
	public boolean checkValue()
	{
		if(controller.getRawAxis(triggerAxis) > .75 && triggerValue == false)
		{
			triggerValue = true;
			System.out.println("triggered");
			Scheduler.getInstance().add(command);
		}
		else if(controller.getRawAxis(triggerAxis) < .1 && triggerValue == true)
		{
			triggerValue = false;
			System.out.println("untriggered");
		}
		return triggerValue;
	}
}
