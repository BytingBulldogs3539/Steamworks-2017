package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3539.robot.autoncommands.*;
import org.usfirst.frc.team3539.robot.autongroups.*;
import org.usfirst.frc.team3539.robot.calibration.*;
import org.usfirst.frc.team3539.robot.commands.*;
import org.usfirst.frc.team3539.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 **/

public class Robot extends IterativeRobot
{
	// SUBSYSTEMS
	public static final HoodSubsystem hoodSubsystem = new HoodSubsystem();
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final GearManipulator manipulator = new GearManipulator();

	public static Raspberry raspberry;

	public static Compressor c;
	public static OI oi;
	// public static UsbCamera camera;

	Command autonMode, allianceMode, visionMode;
	SendableChooser<Command> autonChooser, allianceChooser, visionChooser;

	public void robotInit()
	{
		c = new Compressor(RobotMap.compressor);

		oi = new OI();

		raspberry = new Raspberry();

		SmartInit();
		Update();

		// camera = CameraServer.getInstance().startAutomaticCapture();
		// camera.setResolution(480, 360);

		// BulldogLogger.getInstance().logInfo("Starting robotInit");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 **/
	public void disabledInit()
	{
		// BulldogLogger.getInstance().finishLogging();
		Scheduler.getInstance().run();

	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void autonomousInit()
	{
		// BulldogLogger.getInstance().logInfo("autonomousInit");
		System.out.println("autonomousInit");
		Update();

		allianceMode = (Command) allianceChooser.getSelected();
		visionMode = (Command) visionChooser.getSelected();
		autonMode = (Command) autonChooser.getSelected();

		if (autonMode != null)
		{
			System.out.println("Here");

			autonMode.start();
		}

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

		if (autonMode != null)
			autonMode.cancel();

		if (allianceMode != null)
			allianceMode.cancel();

		if (visionMode != null)
			visionMode.cancel();

		Robot.manipulator.holderClose();

		raspberry.Init();
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
		hoodSubsystem.Update();
	}

	public void SmartInit()
	{
		oi.SmartInit();
		intake.SmartInit();
		shooter.SmartInit();
		manipulator.SmartInit();
		driveTrain.SmartInit();
		hoodSubsystem.SmartInit();

		autonChooser = new SendableChooser<Command>();
		allianceChooser = new SendableChooser<Command>();
		visionChooser = new SendableChooser<Command>();

		SmartDashboard.putData("Alliance", allianceChooser);
		allianceChooser.addDefault("Red Team, Default", new AllianceSwitcherCommand(false));
		allianceChooser.addObject("Blue Team", new AllianceSwitcherCommand(true));

		SmartDashboard.putData("Vison", visionChooser);
		visionChooser.addDefault("No Vision, Default", new VisionSwitcherCommand(false));
		visionChooser.addObject("Vision", new VisionSwitcherCommand(true));

		SmartDashboard.putData("Alliance", allianceChooser);
		allianceChooser.addDefault("Red Team, Default", new AllianceSwitcherCommand(false));
		allianceChooser.addObject("Blue Team", new AllianceSwitcherCommand(true));

		if (allianceMode != null)
		{
			System.out.println("Alliance Mode Ran");

			allianceMode.start();
		}

		if (visionMode != null)
		{
			System.out.println("Vision Mode Ran");

			visionMode.start();
		}

		SmartDashboard.putData("Auto mode", autonChooser);
		autonChooser.addDefault("No Auton, Default", new VoidCommand());
		autonChooser.addObject("Auton Turn 180", new AutonTurn(180));
		autonChooser.addObject("GearOutsideGroup", new GearOutsideGroup());
		autonChooser.addObject("GearMiddleGroup", new GearMiddleGroup());
		autonChooser.addObject("GearInsideGroup", new GearInsideGroup());
		autonChooser.addObject("HopperBoiler", new HopperBoiler());
		autonChooser.addObject("ShootInsideGroup", new ShootInsideGroup());
		autonChooser.addObject("ShootMiddleGroup", new ShootMiddleGroup());
		autonChooser.addObject("ShootOutsideGroup", new ShootOutsideGroup());
		autonChooser.addObject("DirtyLeftGroup", new GearLeftGroup());
		autonChooser.addObject("DirtyRightGroup", new GearRightGroup());
		autonChooser.addObject("DirtyDanTheMiddleManRed", new DirtyDanTheMiddleManRed());
		autonChooser.addObject("DirtyDanTheMiddleManBlue", new DirtyDanTheMiddleManBlue());
		autonChooser.addObject("test Vision Peg", new AutonDrive(90,2));
		autonChooser.addObject("HopperBlue", new HopperBlue());
		autonChooser.addObject("HopperRed", new HopperRed());
		// autonChooser.addObject("VisionGearLeftGroup", new
		// VisionGearLeftGroup());
		autonChooser.addObject("HopperRed", new HopperRed());
		autonChooser.addObject("HopperBlue", new HopperBlue());
		autonChooser.addObject("NoneForward", new NoneForward());

		SmartDashboard.putData(new AutoAim());

		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(manipulator);
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(new TestMaxVel());
		SmartDashboard.putData(new ZeroHoodCommand());
		SmartDashboard.putData(new AutoAim());

		SmartDashboard.putData(new DriveCalibrate());
		SmartDashboard.putData(new TurnCalibrate());
		SmartDashboard.putData(Scheduler.getInstance());
	}
}