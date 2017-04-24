package org.usfirst.frc.team3539.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class TriggerButton extends Button
{
	private int triggerAxis;
	private Joystick controller;
	private Command command;
	public boolean triggerValue;

	public TriggerButton(int axis, Joystick joystick)
	{
		triggerAxis = axis;
		controller = joystick;
		triggerValue = false;
		this.command = null;
	}

	public TriggerButton(int axis, Joystick joystick, Command command)
	{
		triggerAxis = axis;
		controller = joystick;
		triggerValue = false;
		this.command = command;
	}

	@Override
	public boolean get()
	{
		return checkValue();
	}
	
	public void setCommand(Command command)
	{
		this.command = command;
	}

	public boolean checkValue()
	{
		if(controller.getRawAxis(triggerAxis) > .75 && triggerValue == false)
		{
			triggerValue = true;
			if(command != null)
			{
				Scheduler.getInstance().add(command);
			}
		}
		else if(controller.getRawAxis(triggerAxis) < .1 && triggerValue == true)
		{
			triggerValue = false;
		}
		return triggerValue;
	}
}
