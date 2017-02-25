package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.auton.ForwardGearPlaceGroup;
import org.usfirst.frc.team3539.robot.auton.TurnLeftGearPlaceGroup;
import org.usfirst.frc.team3539.robot.auton.TurnRightGearPlaceGroup;
import org.usfirst.frc.team3539.robot.commands.AutoAim;
import org.usfirst.frc.team3539.robot.commands.AutonDrive;
import org.usfirst.frc.team3539.robot.commands.AutonTurn;
import org.usfirst.frc.team3539.robot.commands.ClimbCommand;
import org.usfirst.frc.team3539.robot.commands.DriveCommand;
import org.usfirst.frc.team3539.robot.commands.VoidCommand;
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
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final GearManipulator manipulator = new GearManipulator();

	public static Compressor c;
	public static OI oi;
	public static UsbCamera camera;
	public static Raspberry raspberry;

	Command autonMode, teleopMode;
	SendableChooser<Command> autonChooser, teleopChooser, selectCommands;

	public void robotInit()
	{
		c = new Compressor(RobotMap.compressor);

		oi = new OI();

		raspberry = new Raspberry();

		SmartInit();

		camera = CameraServer.getInstance().startAutomaticCapture();
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
		Update();

		autonMode = (Command) autonChooser.getSelected();
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
		SmartDashboard.putData(new AutoAim());
		System.out.println("teleopInit");
		if(autonMode != null)
			autonMode.cancel();
		driveTrain.gyroReset();
		raspberry.Init();
	}

	// This function is called periodically during operator control
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		Update();
		raspberry.Print();
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
		autonChooser.addObject("Auton Turn 180", new AutonTurn(180));
		autonChooser.addObject("Auton Turn 90", new AutonTurn(90));
		autonChooser.addObject("TurnRightGearPlace", new TurnRightGearPlaceGroup());
		autonChooser.addObject("ForwardGearPlace", new ForwardGearPlaceGroup());
		autonChooser.addObject("TurnLeftGearPlace", new TurnLeftGearPlaceGroup());
		autonChooser.addObject("Tunning Drive", new AutonDrive(400));

		SmartDashboard.putData("Tele mode", teleopChooser);
		teleopChooser.addDefault("Vision, Default", new VoidCommand()); //Switch with teleop commands
		teleopChooser.addObject("No Vision", new VoidCommand());

		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(manipulator);
		SmartDashboard.putData(driveTrain);

		selectCommands = new SendableChooser<Command>();
		selectCommands.addObject("Climb", new ClimbCommand());

		SmartDashboard.putData("Command Select", selectCommands);

		SmartDashboard.putData(Scheduler.getInstance());
	}
}