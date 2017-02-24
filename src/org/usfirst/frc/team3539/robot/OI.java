package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.commands.ClimbCommand;
import org.usfirst.frc.team3539.robot.commands.GearCommand;
import org.usfirst.frc.team3539.robot.commands.HoodCommand;
import org.usfirst.frc.team3539.robot.commands.IntakeCommand;
import org.usfirst.frc.team3539.robot.commands.ShooterCommand;
import org.usfirst.frc.team3539.robot.commands.TransmissionCommand;
import org.usfirst.frc.team3539.robot.commands.TriggerModifierCommand;
import org.usfirst.frc.team3539.robot.utilities.TriggerButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	public Joystick controller1 = new Joystick(RobotMap.controllerOnePort);
	public Joystick controller2 = new Joystick(RobotMap.controllerTwoPort);

	public JoystickButton onebuttonx = new JoystickButton(controller1, RobotMap.buttonx);
	public JoystickButton onebuttony = new JoystickButton(controller1, RobotMap.buttony);
	public JoystickButton onebuttona = new JoystickButton(controller1, RobotMap.buttona);
	public JoystickButton onebuttonb = new JoystickButton(controller1, RobotMap.buttonb);

	public JoystickButton twobuttonx = new JoystickButton(controller2, RobotMap.buttonx);
	public JoystickButton twobuttony = new JoystickButton(controller2, RobotMap.buttony);
	public JoystickButton twobuttona = new JoystickButton(controller2, RobotMap.buttona);
	public JoystickButton twobuttonb = new JoystickButton(controller2, RobotMap.buttonb);
	public JoystickButton twotriggerl = new JoystickButton(controller2, RobotMap.LEFT_TRIGGER);
	public JoystickButton twotriggerr = new JoystickButton(controller2, RobotMap.RIGHT_TRIGGER);

	public JoystickButton twobumperl = new JoystickButton(controller2, RobotMap.bumperl);
	public JoystickButton twobumperr = new JoystickButton(controller2, RobotMap.bumperr);

	public TriggerButton intakeTrigger = new TriggerButton(2, controller2, new IntakeCommand());
	public TriggerButton shooterTrigger = new TriggerButton(3, controller2, new ShooterCommand(1980, 10));//not real values
	public TriggerButton invertTrigger = new TriggerButton(3, controller1);

	public OI()
	{
		// Test - Not yet tested
		// Error - needs to be fixed
		// Update - works properly but is not yet finished
		// Done - works properly

		// STICKONE
		//onebuttona.whenPressed(new TransmissionCommand()); // Done

		// STICKTWO
		twobuttona.whenPressed(new GearCommand()); // Done
		twobuttonb.whenPressed(new HoodCommand()); // Done

		twobuttonx.whenPressed(new TriggerModifierCommand()); // Test
		twobuttony.whenPressed(new ClimbCommand()); // Done

		//twobumperr.whenPressed(new ShooterCommand(Velocity of shooter, hood angle));
		//twobumperl.whenPressed(new ShooterCommand(Velocity of shooter, hood angle));
	}
	
	private boolean latch = false;
	public boolean isButtonPressed() // I'm sorry for this method. Hopefully it at least works
	{
		{
			latch = false;
			return false;
		}
		{
			latch = true;
			return true;
		}
		{
			latch = true;
			return false;
		}
		return false;
	}
	public void Update()
	{
		intakeTrigger.checkValue();
		shooterTrigger.checkValue();
		SmartDashboard.putBoolean("triggerModified", RobotMap.triggerModified);
	}

	public void SmartInit()
	{
		SmartDashboard.putBoolean("triggerModified", RobotMap.triggerModified);
	}
}