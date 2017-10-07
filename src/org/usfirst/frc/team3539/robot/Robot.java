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
import org.usfirst.frc.team3539.robot.autonscurves.LeftCurvePeg;
import org.usfirst.frc.team3539.robot.autonscurves.RedCurveHopper;
import org.usfirst.frc.team3539.robot.autonscurves.RightCurvePeg;
import org.usfirst.frc.team3539.robot.commands.*;
import org.usfirst.frc.team3539.robot.subsystems.*;
import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;

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

		BulldogLogger.getInstance().logInfo("Starting robotInit");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 **/
	public void disabledInit()
	{
		//BulldogLogger.getInstance().finishLogging();

		Scheduler.getInstance().run();

	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
		Update();
	}

	public void autonomousInit()
	{
		BulldogLogger.getInstance().logInfo("Logging Start");
		Update();

		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.gyroReset();

		raspberry.setCamera(RobotMap.gearCamera);

		autonMode = (Command) autonChooser.getSelected();
		if (autonMode != null)
		{
			shooter.disableShooterPID();
			shooter.disableAgitatorPID();
			autonMode.start();
		}
		driveTrain.gyroReset();
		driveTrain.highGear();
	}

	// This function is called periodically during autonomous
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		Update();
	}

	public void teleopInit()
	{
		BulldogLogger.getInstance().logInfo("Logging Start");
		System.out.println("teleopInit");
		raspberry.setCamera(RobotMap.shooterCamera);
		Robot.manipulator.holderClose();
		driveTrain.highGear();
	}

	// This function is called periodically during operator control
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		BulldogLogger.getInstance()
				.logInfo("DriveTrain (Observe)  -  Left Front Motor: " + Robot.driveTrain.lfMotor.get()
						+ "  Left Back Motor: " + Robot.driveTrain.lbMotor.get() + "  --- Right Front Motor: "
						+ Robot.driveTrain.rfMotor.get() + "  Right Back Motor: " + Robot.driveTrain.rbMotor.get());
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
	

		SmartDashboard.putDouble("GearDistanceFudge", .95);
/*
		SmartDashboard.putData(new LeftCurvePeg());
		SmartDashboard.putData(new RightCurvePeg());
		SmartDashboard.putData(new RedCurveHopper());

		SmartDashboard.putData(new VisionGearMiddle());
		SmartDashboard.putData(new VisionTest());

		SmartDashboard.putData(new RedChickenHopper());
		SmartDashboard.putData(new BlueChickenHopper());
		*/

		autonChooser = new SendableChooser<Command>();

		SmartDashboard.putData("Auton mode", autonChooser);
		autonChooser.addDefault("No Auton, Default", new VoidCommand());
		autonChooser.addObject("Auton Turn 180", new AutonTurn(180));

		// Calibrate\/

		// Gear\/
		// autonChooser.addObject("GearMiddleGroup", new GearMiddleGroup());
		// autonChooser.addObject("GearLeftGroup", new GearLeftGroup());
		// autonChooser.addObject("GearRightGroup", new GearRightGroup());

		// Red\/

		autonChooser.addObject("RedShootMiddle", new RedShootMiddle());
		autonChooser.addObject("RedHopper", new RedHopper());
		autonChooser.addObject("RedShootOutside", new RedShootOutside());
		autonChooser.addObject("RedShootInside", new RedShootInside());
		autonChooser.addObject("RedChickenHopper", new RedChickenHopper());
		autonChooser.addObject("BlueChickenHopper", new BlueChickenHopper());

		// Blue\/

		autonChooser.addObject("BlueShootMiddle", new BlueShootMiddle());
		autonChooser.addObject("BlueShootOutside", new BlueShootOutside());
		autonChooser.addObject("BlueShootInside", new BlueShootInside());

		// test
		autonChooser.addObject("VisionGearMiddle", new VisionGearMiddle());
		autonChooser.addObject("VisionTuning", new JoeyShoot(7));
		autonChooser.addObject("NoneForward", new NoneForward());

		/*SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(manipulator);
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(new TestMaxVel());
		SmartDashboard.putData(new ZeroHoodCommand());
		SmartDashboard.putData(new JoeyShoot(false, 0, 350, -1000, 5));
		// boolean visionTurn, double hoodAngle, double agitatorRpm, double
		// shooterRpm, double seconds

		SmartDashboard.putData(Scheduler.getInstance());
		*/
	}
}

//class Logger implements Runnable
//{
//
//	public void run()
//	{
//		BulldogLogger.getInstance().startLogging(true, true, true);
//	}
//	
//}