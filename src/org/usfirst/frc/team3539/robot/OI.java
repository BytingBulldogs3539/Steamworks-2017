package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.autoncommands.AutoAim;
import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.robot.autoncommands.VisionTurn;
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
	public Joystick controller1 = new Joystick(RobotMap.controllerOnePort);
	public Joystick controller2 = new Joystick(RobotMap.controllerTwoPort);

	public JoystickButton onebuttonx = new JoystickButton(controller1, RobotMap.buttonx);
	public JoystickButton onebuttony = new JoystickButton(controller1, RobotMap.buttony);
	public JoystickButton onebuttona = new JoystickButton(controller1, RobotMap.buttona);
	public JoystickButton onebuttonb = new JoystickButton(controller1, RobotMap.buttonb);
	
	public JoystickButton twobuttonStart = new JoystickButton(controller2, RobotMap.buttonStart);
	
	
	public JoystickButton twobuttonx = new JoystickButton(controller2, RobotMap.buttonx);
	public JoystickButton twobuttony = new JoystickButton(controller2, RobotMap.buttony);
	public JoystickButton twobuttona = new JoystickButton(controller2, RobotMap.buttona);
	public JoystickButton twobuttonb = new JoystickButton(controller2, RobotMap.buttonb);
	public JoystickButton twotriggerl = new JoystickButton(controller2, RobotMap.LEFT_TRIGGER);
	public JoystickButton twotriggerr = new JoystickButton(controller2, RobotMap.RIGHT_TRIGGER);
	
	public JoystickButton buttonLS = new JoystickButton (controller2, RobotMap.buttonLS);
	public JoystickButton buttonRS = new JoystickButton (controller2, RobotMap.buttonRS);

	
	public JoystickButton twobumperl = new JoystickButton(controller2, RobotMap.bumperl);
	public JoystickButton twobumperr = new JoystickButton(controller2, RobotMap.bumperr);

	public TriggerButton intakeTrigger = new TriggerButton(2, controller2, new IntakeCommand());
	public TriggerButton shooterTrigger;
	public TriggerButton invertTrigger = new TriggerButton(3, controller1);
	
	public DpadButton visionButton;
	public DpadButton supermanButton;
	public DpadButton zeroHoodButton;
	

	public OI()
	{
		// Test - Not yet tested
		// Error - needs to be fixed
		// Update - works properly but is not yet finished
		// Done - works properly

		// STICKONE
		
		//onebuttony.whenPressed(new SetPointCommand(RobotMap.hoodTarget));
		
		//onebuttonx.whenPressed(new VisionTurn(0));

		// STICKTWO
		twobuttona.whenPressed(new GearCommand()); // Done
		twobuttonb.whenPressed(new HoodCommand()); // Done
		twobuttonx.whenPressed(new TriggerModifierCommand()); // Done
		twobuttony.whenPressed(new ClimbCommand()); //Done
		
		
		twobumperr.whenPressed(new JoeyShoot(false, twobumperr, 550, 250, -3350));
		twobumperl.whenPressed(new JoeyShoot(false, twobumperl, 700, 250, -3625));
		shooterTrigger = new TriggerButton(3, controller2);
		shooterTrigger.setCommand(new JoeyShoot(false, shooterTrigger, 350, 250, -3050));
		
		supermanButton = new DpadButton ("left", controller2);
		supermanButton.setCommand(new JoeyShoot(supermanButton));
		
		zeroHoodButton = new DpadButton("right", controller2);
		zeroHoodButton.setCommand(new ZeroHoodCommand());
		
		visionButton = new DpadButton("down", new AutoAim(), controller2);
		

		//twobumperr.whenPressed(new ShooterCommand(Velocity of shooter, hood angle));
		//twobumperl.whenPressed(new ShooterCommand(Velocity of shooter, hood angle));
		
		//Start HoodManual
		buttonLS.whenPressed(new HoodManual());
		//buttonRS.cancelWhenPressed();
		
	}
	
	public void Update()
	{
		intakeTrigger.checkValue();
		shooterTrigger.checkValue();
		visionButton.checkValue();
		SmartDashboard.putBoolean("triggerModified", RobotMap.triggerModified);
	}

	public void SmartInit()
	{
		SmartDashboard.putBoolean("triggerModified", RobotMap.triggerModified);
	}
}