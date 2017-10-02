
package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.SpinUp;
import org.usfirst.frc.team3539.robot.commands.ClimbCommand;
import org.usfirst.frc.team3539.robot.commands.FireCommand;
import org.usfirst.frc.team3539.robot.commands.GearCommand;
import org.usfirst.frc.team3539.robot.commands.HoodCommand;
import org.usfirst.frc.team3539.robot.commands.HoodManual;
import org.usfirst.frc.team3539.robot.commands.IntakeCommand;
import org.usfirst.frc.team3539.robot.commands.JoeyShoot;
import org.usfirst.frc.team3539.robot.commands.TriggerModifierCommand;
import org.usfirst.frc.team3539.robot.commands.ZeroHoodCommand;
import org.usfirst.frc.team3539.robot.utilities.TriggerButton;
import org.usfirst.frc.team3539.robot.utilities.XboxController;
import org.usfirst.frc.team3539.robot.utilities.DpadButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	public XboxController CON = new XboxController(RobotMap.controllerOnePort);
	public Joystick controller1 = new Joystick(RobotMap.controllerOnePort);
	public Joystick controller2 = new Joystick(RobotMap.controllerTwoPort);

	public JoystickButton onebuttonx = new JoystickButton(controller1, RobotMap.buttonx);
	public JoystickButton onebuttony = new JoystickButton(controller1, RobotMap.buttony);
	public JoystickButton onebuttona = new JoystickButton(controller1, RobotMap.buttona);
	public JoystickButton onebuttonb = new JoystickButton(controller1, RobotMap.buttonb);
	
	public JoystickButton onebumperl = new JoystickButton(controller1, RobotMap.bumperl);
	public JoystickButton onebumperr = new JoystickButton(controller1, RobotMap.bumperr);
	public JoystickButton start = new JoystickButton(controller1,RobotMap.buttonRS);
	
	public JoystickButton twobuttonStart = new JoystickButton(controller2, RobotMap.buttonStart);

	public JoystickButton twobuttonx = new JoystickButton(controller2, RobotMap.buttonx);
	public JoystickButton twobuttony = new JoystickButton(controller2, RobotMap.buttony);
	public JoystickButton twobuttona = new JoystickButton(controller2, RobotMap.buttona);
	public JoystickButton twobuttonb = new JoystickButton(controller2, RobotMap.buttonb);
	public JoystickButton twotriggerl = new JoystickButton(controller2, RobotMap.LEFT_TRIGGER);
	public JoystickButton twotriggerr = new JoystickButton(controller2, RobotMap.RIGHT_TRIGGER);

	public JoystickButton twobuttonLS = new JoystickButton(controller2, RobotMap.buttonLS);
	public JoystickButton twobuttonRS = new JoystickButton(controller2, RobotMap.buttonRS);

	public JoystickButton twobumperl = new JoystickButton(controller2, RobotMap.bumperl);
	public JoystickButton twobumperr = new JoystickButton(controller2, RobotMap.bumperr);

	public TriggerButton intakeTrigger = new TriggerButton(2, controller2, new IntakeCommand()); // CHANGE
																									// TO
																									// CONTROLLER2
																									// FOR
																									// COMP
	public TriggerButton shooterTrigger;
	// public TriggerButton invertTrigger = new TriggerButton(3, controller1);

	public DpadButton visionButton;
	public DpadButton supermanButton;
	public DpadButton zeroHoodButton;
	public DpadButton visionGearButton;

	public OI()
	{
		// Test - Not yet tested
		// Error - needs to be fixed
		// Update - works properly but is not yet finished
		// Done - works properly

		// STICKONE
		// STICKTWO
		start.whenPressed(new ZeroHoodCommand());
		twobuttona.whenPressed(new GearCommand()); // Done
		twobuttonb.whenPressed(new HoodCommand()); // Done
		twobuttonx.whenPressed(new TriggerModifierCommand()); // Done
		twobuttony.whenPressed(new ClimbCommand()); // Done

		twobuttonLS.whenPressed(new HoodManual());

		twobumperr.whenPressed(new JoeyShoot(false, twobumperr, 550, 400, -3350));
		twobumperl.whenPressed(new JoeyShoot(false, twobumperl, 1024, 400, -5000));
		shooterTrigger = new TriggerButton(3, controller2);
		shooterTrigger.setCommand(new JoeyShoot(false, shooterTrigger, 350, 400, -3050));

		onebuttonx.whenPressed(new JoeyShoot(false, onebuttonx, 420, 320, -2800));
		onebuttony.whenPressed(new JoeyShoot(false, onebuttony, 300, 400, -2700));
		
		
		

		
		supermanButton = new DpadButton("up", controller2);
		supermanButton.setCommand(new JoeyShoot(supermanButton));

		zeroHoodButton = new DpadButton("right", new ZeroHoodCommand(), controller2);

		visionButton = new DpadButton("down", controller2); // add constructor
															// for button
		visionButton.setCommand(new AutonTurn(true, visionButton));

		visionGearButton = new DpadButton("left", new AutonTurn(0, 1), controller2);
		// VisionCalabrate = new DpadButton("left", new JoeyShoot(false,
		// VisionCalabrate, RobotMap.hoodTarget, 150, RobotMap.shooterRpm),
		// controller2);

	}

	public void Update()
	{
		intakeTrigger.checkValue();
		shooterTrigger.checkValue();
		visionButton.checkValue();
		supermanButton.checkValue();
		zeroHoodButton.checkValue();
		visionGearButton.checkValue();

		SmartDashboard.putBoolean("triggerModified", RobotMap.triggerModified);
	}

	public void SmartInit()
	{
		SmartDashboard.putBoolean("triggerModified", RobotMap.triggerModified);
	}
}