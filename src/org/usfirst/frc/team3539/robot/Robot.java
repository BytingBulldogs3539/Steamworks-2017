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
	//SUBSYSTEMS
	public static final HoodSubsystem hoodSubsystem = new HoodSubsystem();
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final GearManipulator manipulator = new GearManipulator();

	public static Raspberry raspberry;

	public static Compressor c;
	public static OI oi;
	//public static UsbCamera camera;

	Command autonMode, teleopMode;
	SendableChooser<Command> autonChooser, teleopChooser;

	public void robotInit()
	{
		c = new Compressor(RobotMap.compressor);

		oi = new OI();

		raspberry = new Raspberry();

		SmartInit();
		Update();

		//camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setResolution(480, 360);
		
		BulldogLogger.getInstance().logInfo("Starting robotInit");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 **/
	public void disabledInit()
	{
	    BulldogLogger.getInstance().finishLogging();

	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void autonomousInit()
	{
	    BulldogLogger.getInstance().logInfo("autonomousInit");
		System.out.println("autonomousInit");
		Update();

		autonMode = (Command) autonChooser.getSelected();
		
		
		if(autonMode != null)
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
		c.stop(); //debug
	}

	public void teleopInit()
	{
		System.out.println("teleopInit");
		if(autonMode != null)
			autonMode.cancel();
		raspberry.Init();
	}

	// This function is called periodically during operator control
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		Update();
        c.start(); //debug
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
		teleopChooser = new SendableChooser<Command>();
		
		SmartDashboard.putData("Auto mode", autonChooser);
		autonChooser.addDefault("No Auton, Default", new VoidCommand());
		autonChooser.addObject("Auton Turn 180", new AutonTurn(180));
		autonChooser.addObject("Auton Turn 90", new AutonTurn(90));
		autonChooser.addObject("GearRightGroup", new GearRightGroup());
		autonChooser.addObject("GearForwardGroup", new GearForwardGroup());
		autonChooser.addObject("GearLeftGroup", new GearLeftGroup());
		autonChooser.addObject("ReverseTest", new ReverseTest());
		autonChooser.addObject("FarGearLeft", new FarGearLeft());
		autonChooser.addObject("HopperGearLeft", new HopperGearLeft());
		autonChooser.addObject("PlaceGearShootRight", new ShootInsideGroup());
		autonChooser.addObject("GearLeftGroupVision", new GearLeftGroupVision());
		autonChooser.addObject("HopperRight", new HopperRight());

		SmartDashboard.putData("Tele mode", teleopChooser);
		teleopChooser.addDefault("Vision, Default", new VoidCommand()); //Switch with teleop commands
		teleopChooser.addObject("No Vision", new VoidCommand());
	

		SmartDashboard.putData(new AutonDriveWithVision(70));
		
		
		SmartDashboard.putData(new AutoAim());
		
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(manipulator);
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(new TestMaxVel());
		SmartDashboard.putData(new ZeroHoodCommand());
		SmartDashboard.putData(new ShooterRight());
		SmartDashboard.putData(new AutoAim());

		SmartDashboard.putData(new DriveCalibrate());
		SmartDashboard.putData(new TurnCalibrate());
		SmartDashboard.putData(Scheduler.getInstance());
	}
}