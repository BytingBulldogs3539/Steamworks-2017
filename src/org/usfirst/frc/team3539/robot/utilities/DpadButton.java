package org.usfirst.frc.team3539.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class DpadButton extends Button
{
	double angle;
	double neededAngle;
	Command command;
	private Joystick controller;
	private boolean isPressed;
	
	public DpadButton(String direction ,Command mycommand, Joystick mycontroller)
	{
		isPressed = false;
		controller = mycontroller;
		command = mycommand;
		angle = controller.getPOV();
	    neededAngle = 1;
	
	    if (direction.toLowerCase() == "up")         
	    { 
	    	neededAngle = 0; 
	    }
		else if (direction.toLowerCase() == "right")
		{ 
			neededAngle = 90;
		}
		else if (direction.toLowerCase() == "down")  
		{
			neededAngle = 180; 
		}
		else if (direction.toLowerCase() == "left") 
		{ 
			neededAngle = 270;
		}
	}
	
	public DpadButton(String direction, Joystick mycontroller)
    {
	    command = null;
        isPressed = false;
        controller = mycontroller;
        angle = controller.getPOV();
        neededAngle = 1;
    
        if (direction.toLowerCase() == "up")         
        { 
            neededAngle = 0; 
        }
        else if (direction.toLowerCase() == "right")
        { 
            neededAngle = 90;
        }
        else if (direction.toLowerCase() == "down")  
        {
            neededAngle = 180; 
        }
        else if (direction.toLowerCase() == "left") 
        { 
            neededAngle = 270;
        }
    }
	
	public void setCommand(Command command)
	{
	    this.command = command;
	}
	
	public boolean get()
	{
		return checkValue();
	}
	
	public boolean checkValue()
	{
		angle = controller.getPOV();
	
		if (angle == neededAngle && isPressed == false)
		{
			if (command != null)
				Scheduler.getInstance().add(command);
			isPressed = true;	
		}
		else
		{
			if (isPressed == true) 
			{ 
			    isPressed = false; 
			}
		}
		
		return isPressed;
	}
}
