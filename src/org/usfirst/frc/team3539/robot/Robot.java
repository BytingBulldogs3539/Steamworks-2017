package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.commands.*;
import org.usfirst.frc.team3539.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3539.robot.subsystems.GearManipulator;
import org.usfirst.frc.team3539.robot.subsystems.Intake;
import org.usfirst.frc.team3539.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	//public static final TankDrive tankDriveTrain = new TankDrive();
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final GearManipulator manipulator = new GearManipulator();
	
	public static Compressor c;
	public static OI oi;

	public static final LightCommand light = new LightCommand();

	Command autonMode, teleopMode;
	SendableChooser<Command> autonChooser, teleopChooser, selectCommands;

	public void robotInit()
	{
		c = new Compressor(RobotMap.compressor);

		oi = new OI();

		SmartInit();

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

		autonMode = (Command) autonChooser.getSelected(); //Command to disable auton
		if(autonMode != null)
			autonMode.start();
		driveTrain.gyroReset();
	}

	// This function is called periodically during autonomous

	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		Update();
	}

	public void teleopInit()
	{
		System.out.println("teleopInit");
		if(autonMode != null)
			autonMode.cancel();
		driveTrain.gyroReset();
		Scheduler.getInstance().add(new DriveCommand());
	}

	// This function is called periodically during operator control
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		Update();
	}

	// This function is called periodically during test mode
	public void testPeriodic()
	{
		Update();
		LiveWindow.run();
	}

	public void Update()
	{

		oi.Update();
		intake.Update();
		shooter.Update();
		manipulator.Update();
		driveTrain.Update();
	}

	public void SmartInit()
	{
		oi.SmartInit();
		intake.SmartInit();
		shooter.SmartInit();
		manipulator.SmartInit();
		driveTrain.SmartInit();

		autonChooser = new SendableChooser<Command>();
		teleopChooser = new SendableChooser<Command>();
		
		SmartDashboard.putData("Auto mode", autonChooser);
		autonChooser.addDefault("No Auton, Default", new VoidCommand());
		autonChooser.addObject("Drive Forward", new AutonDrive(20000));
		autonChooser.addObject("Linear", new AutonDrive(20000));

		SmartDashboard.putData("Tele mode", teleopChooser);
		teleopChooser.addDefault("Vision, Default", new VoidCommand()); //Switch with teleop commands
		teleopChooser.addObject("No Vision", new VoidCommand());

		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(manipulator);
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putDouble("Intake Speed", RobotMap.intakeMotorTalon);
		
		
		SmartDashboard.putData(new AutonDrive(20000));

		selectCommands = new SendableChooser<Command>();
		selectCommands.addObject("Climb", new ClimbCommand());
		selectCommands.addObject("Climb", new LockCommand());

		SmartDashboard.putData("Command Select", selectCommands);

		SmartDashboard.putData(Scheduler.getInstance());
	}
}