package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3539.robot.CurvedAutons.LeftCurveHopper;
import org.usfirst.frc.team3539.robot.CurvedAutons.LeftCurvePeg;
import org.usfirst.frc.team3539.robot.CurvedAutons.RightCurveHopper;
import org.usfirst.frc.team3539.robot.CurvedAutons.RightCurvePeg;
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

	Command autonMode;
	SendableChooser<Command> autonChooser;

	public void robotInit()
	{
		c = new Compressor(RobotMap.compressor);

		oi = new OI();
		
		raspberry = new Raspberry();
		
		raspberry.setCamera(RobotMap.gearCamera);
		
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

		raspberry.setCamera(RobotMap.gearCamera);

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
		raspberry.setCamera(RobotMap.shooterCamera);
		Robot.manipulator.holderClose();
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
		raspberry.Update();
	}

	public void SmartInit()
	{
		oi.SmartInit();
		intake.SmartInit();
		shooter.SmartInit();
		manipulator.SmartInit();
		driveTrain.SmartInit();
		hoodSubsystem.SmartInit();
		raspberry.SmartInit();
		
		SmartDashboard.putDouble("GearDistanceFudge", .85);
		
		
		SmartDashboard.putData(new LeftCurvePeg());
		SmartDashboard.putData(new RightCurvePeg());
		SmartDashboard.putData(new LeftCurveHopper());
		
		SmartDashboard.putData(new VisionGearMiddle());
		SmartDashboard.putData(new VisionTest());
		SmartDashboard.putData(new TurnCalibrate());

		autonChooser = new SendableChooser<Command>();

		SmartDashboard.putData("Auton mode", autonChooser);
		autonChooser.addDefault("No Auton, Default", new VoidCommand());
		autonChooser.addObject("Auton Turn 180", new AutonTurn(180));

		// Calibrate\/
		autonChooser.addObject("Drive Calibrate", new DriveCalibrate());
		autonChooser.addObject("Turn Calibrate", new TurnCalibrate());

		// Gear\/
		//autonChooser.addObject("GearMiddleGroup", new GearMiddleGroup());
		//autonChooser.addObject("GearLeftGroup", new GearLeftGroup());
		//autonChooser.addObject("GearRightGroup", new GearRightGroup());

		// Red\/

		autonChooser.addObject("RedShootMiddle", new RedShootMiddle());
		autonChooser.addObject("RedHopper", new LeftCurveHopper());
		autonChooser.addObject("RedShootOutside", new RedShootOutside());
		autonChooser.addObject("RedShootInside", new RedShootInside());

		// Blue\/

		autonChooser.addObject("BlueShootMiddle", new BlueShootMiddle());
		autonChooser.addObject("BlueHopper", new BlueHopper());
		autonChooser.addObject("BlueShootOutside", new BlueShootOutside());
		autonChooser.addObject("BlueShootInside", new BlueShootInside());

		// test
		autonChooser.addObject("VisionGearMiddle", new VisionGearMiddle());
		autonChooser.addObject("VisionTuning", new JoeyShoot(7));
		autonChooser.addObject("NoneForward", new NoneForward());

		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(manipulator);
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(new TestMaxVel());
		SmartDashboard.putData(new ZeroHoodCommand());

		SmartDashboard.putData(new DriveCalibrate());
		SmartDashboard.putData(new TurnCalibrate());
		SmartDashboard.putData(Scheduler.getInstance());
	}
}