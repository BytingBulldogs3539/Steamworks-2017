package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.autoncommands.AutonTurn;
import org.usfirst.frc.team3539.autoncommands.VisionTurn;
import org.usfirst.frc.team3539.robot.commands.ABShood;
import org.usfirst.frc.team3539.robot.commands.ClimbCommand;
import org.usfirst.frc.team3539.robot.commands.FireCommand;
import org.usfirst.frc.team3539.robot.commands.GearCommand;
import org.usfirst.frc.team3539.robot.commands.HoodCommand;
import org.usfirst.frc.team3539.robot.commands.IntakeCommand;
import org.usfirst.frc.team3539.robot.commands.TriggerModifierCommand;
import org.usfirst.frc.team3539.robot.utilities.TriggerButton;
import org.usfirst.frc.team3539.robot.utilities.DpadButton;

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
	
	public JoystickButton twobuttonStart = new JoystickButton(controller2, RobotMap.buttonStart);
	
	
	public JoystickButton twobuttonx = new JoystickButton(controller2, RobotMap.buttonx);
	public JoystickButton twobuttony = new JoystickButton(controller2, RobotMap.buttony);
	public JoystickButton twobuttona = new JoystickButton(controller2, RobotMap.buttona);
	public JoystickButton twobuttonb = new JoystickButton(controller2, RobotMap.buttonb);
	public JoystickButton twotriggerl = new JoystickButton(controller2, RobotMap.LEFT_TRIGGER);
	public JoystickButton twotriggerr = new JoystickButton(controller2, RobotMap.RIGHT_TRIGGER);
	
	public JoystickButton twobumperl = new JoystickButton(controller2, RobotMap.bumperl);
	public JoystickButton twobumperr = new JoystickButton(controller2, RobotMap.bumperr);

	public TriggerButton intakeTrigger = new TriggerButton(2, controller2, new IntakeCommand());
	public TriggerButton shooterTrigger = new TriggerButton(3, controller2, new FireCommand());//not real values   // new ShooterCommand(2000, 10)
	public TriggerButton invertTrigger = new TriggerButton(3, controller1);
	
	public DpadButton visionButton = new DpadButton("down", new VisionTurn(0), controller2);
	
	

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
		//twobuttonb.whenPressed(new VisionTurn(0)); // Test
		twobuttonx.whenPressed(new TriggerModifierCommand()); // Done
		twobuttony.whenPressed(new ClimbCommand()); //Done
		twobuttonStart.whenPressed(new ABShood());
		

		//twobumperr.whenPressed(new ShooterCommand(Velocity of shooter, hood angle));
		//twobumperl.whenPressed(new ShooterCommand(Velocity of shooter, hood angle));
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