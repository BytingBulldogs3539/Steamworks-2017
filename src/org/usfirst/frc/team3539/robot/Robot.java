package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.commands.*;
import org.usfirst.frc.team3539.robot.subsystems.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 **/

public class Robot extends IterativeRobot
{
	//SUBSYSTEMS
	public static final TankDrive tankDriveTrain = new TankDrive();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final GearManipulator manipulator = new GearManipulator();
	
	public static Compressor c;
	public static OI oi;

	Command autoMode, teleopMode;
	SendableChooser<Command> autoChooser, teleopChooser, selectCommands;

	public void robotInit()
	{
		c = new Compressor(RobotMap.compressor);

		oi = new OI();
		System.out.println("after OI constructor");
		autoChooser = new SendableChooser<Command>();
		teleopChooser = new SendableChooser<Command>();

		SmartDashboard.putData("Auto mode", autoChooser);
		autoChooser.addDefault("No Auton, Default", new VoidCommand());
		autoChooser.addObject("No Auton", new VoidCommand());

		SmartDashboard.putData("Tele mode", teleopChooser);
		teleopChooser.addDefault("Vision, Default", new VoidCommand()); //Switch with teleop commands
		teleopChooser.addObject("No Vision", new VoidCommand());


		SmartDashboard.putData(intake);
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(manipulator);
		SmartDashboard.putData(tankDriveTrain);
		
		SmartDashboard.putNumber("Shooter Speed", (RobotMap.shootSpeed * -1));
		
		
		selectCommands = new SendableChooser<Command>();
		selectCommands.addObject("Climb", new ClimbCommand());
		selectCommands.addObject("Climb", new LockCommand());
		
		
		SmartDashboard.putData("Command Select", selectCommands);
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(480, 360);
		
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 **/
	public void disabledInit()
	{

	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void autonomousInit()
	{
		System.out.println("autonomousInit");
		//autoMode = (Command) autoChooser.getSelected(); //Auton disabled
		if(autoMode != null)
			autoMode.start();
		tankDriveTrain.gyroReset();
	}

	// This function is called periodically during autonomous

	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		update();
	}

	public void teleopInit()
	{
		System.out.println("teleopInit");
		if(autoMode != null)
			autoMode.cancel();
		tankDriveTrain.gyroReset();
	}

	// This function is called periodically during operator control
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		update();
	}

	// This function is called periodically during test mode
	public void testPeriodic()
	{
		update();
		LiveWindow.run();
	}

	public void update()
	{
		oi.Update();
		intake.Update();
		shooter.Update();
		manipulator.Update();
		tankDriveTrain.Update();
	}
}