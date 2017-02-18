package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.commands.AgitatorCommand;
import org.usfirst.frc.team3539.robot.commands.ClimbCommand;
import org.usfirst.frc.team3539.robot.commands.GearCommand;
import org.usfirst.frc.team3539.robot.commands.HoodCommand;
import org.usfirst.frc.team3539.robot.commands.TransmissionCommand;
import org.usfirst.frc.team3539.robot.commands.Unjam;
import org.usfirst.frc.team3539.robot.commands.UnjamIntakeCommand;
import org.usfirst.frc.team3539.robot.commands.VisionTrackCommand;
import org.usfirst.frc.team3539.robot.utilities.TriggerButton;
import org.usfirst.frc.team3539.robot.commands.IntakeCommand;
import org.usfirst.frc.team3539.robot.commands.LockCommand;
import org.usfirst.frc.team3539.robot.commands.SetPointCommand;
import org.usfirst.frc.team3539.robot.commands.ShooterCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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

	// public TriggerButton unjamIntakeTrigger = new JoystickButton(controller2,
	// );
	public TriggerButton intakeTrigger = new TriggerButton(2, controller2, new IntakeCommand());
	public TriggerButton shooterTrigger = new TriggerButton(3, controller2, new ShooterCommand());
	public TriggerButton invertTrigger = new TriggerButton(3, controller1);

	public OI() {
		// Test - Not yet tested
		// Error - needs to be fixed
		// Update - works properly but is not yet finished
		// Done - works properly

		// TRIGGERS

		// onebuttona.whenPressed(new AgitatorCommand());// Test
		// twotriggerr.whenActive(new IntakeCommand());// Test

		// STICKONE
		onebuttonx.whenPressed(new TransmissionCommand()); // Error

		// STICKTWO
		twobuttona.whenPressed(new GearCommand()); // Done
		twobuttonb.whenPressed(new HoodCommand()); // Done
		twobuttonx.whenPressed(new SetPointCommand()); // Error
		twobuttony.whenPressed(new VisionTrackCommand()); // Error

		twobumperr.whileHeld(new ClimbCommand()); // Done
		twotriggerl.whenPressed(new Unjam()); // Done
		twobumperl.whenPressed(new UnjamIntakeCommand()); // Done

		onebuttony.whenPressed(new ShooterCommand()); // Update

	}

	public void Update() {
		intakeTrigger.checkValue();
		shooterTrigger.checkValue();
	}
}