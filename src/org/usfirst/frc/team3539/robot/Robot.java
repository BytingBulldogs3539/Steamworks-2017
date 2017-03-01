package org.usfirst.frc.team3539.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3539.robot.commands.*;
import org.usfirst.frc.team3539.robot.subsystems.*;
import org.usfirst.frc.team3539.robot.utilities.*;
import org.usfirst.frc.team3539.robot.auton.*;

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
	//public static UsbCamera camera;
	public static Raspberry raspberry;

	Command autonMode, teleopMode;
	SendableChooser<Command> autonChooser, teleopChooser, selectCommands, positionChooser, teamChooser, typeChooser;

	public void robotInit()
	{
		c = new Compressor(RobotMap.compressor);

		oi = new OI();

		raspberry = new Raspberry();

		SmartInit();

		//camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setResolution(480, 360);
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
		teamChooser = new SendableChooser<Command>();
		positionChooser = new SendableChooser<Command>();
		typeChooser = new SendableChooser<Command>();

		SmartDashboard.putData("Auton Team", teamChooser);
		teamChooser.addDefault("No Team, Default", new VoidCommand());
		teamChooser.addObject("Red Team", new SetTeamCommand(2));
		teamChooser.addObject("Blue Team", new SetTeamCommand(1));
		SmartDashboard.putData("Auton Position", positionChooser);
		teamChooser.addDefault("No Position, Default", new VoidCommand());
		teamChooser.addObject("Position 1", new SetPositionCommand(1));
		teamChooser.addObject("Position 2", new SetPositionCommand(2));
		teamChooser.addObject("Position 3", new SetPositionCommand(3));
		SmartDashboard.putData("Auton Type", typeChooser);
		teamChooser.addDefault("No Type, Default", new VoidCommand());
		teamChooser.addDefault("No Position, Default", new VoidCommand());
		teamChooser.addObject("Type 1", new SetTypeCommand(1));
		teamChooser.addObject("Type 2", new SetTypeCommand(2));
		teamChooser.addObject("Type 3", new SetTypeCommand(3));
		
		SmartDashboard.putData("Auto mode", autonChooser);
		autonChooser.addDefault("No Auton, Default", new VoidCommand());
		autonChooser.addObject("Drive Forward", new AutonDrive(20000));
		autonChooser.addObject("Auton Turn 180", new AutonTurn(180));
		autonChooser.addObject("Auton Turn 90", new AutonTurn(90));
		autonChooser.addObject("TurnRightGearPlace", new TurnRightGearPlaceGroup());
		autonChooser.addObject("ForwardGearPlace", new ForwardGearPlaceGroup());
		autonChooser.addObject("TurnLeftGearPlace", new TurnLeftGearPlaceGroup());
		autonChooser.addObject("Tunning Drive", new AutonDrive(400));
		autonChooser.addObject("Auton Move 10,000 ticks", new AutonDriveTicks(10000));
		autonChooser.addObject("Auton Move 30,000 ticks", new AutonDriveTicks(30000));
		autonChooser.addObject("Auton Move 40,000 ticks", new AutonDriveTicks(40000));

		SmartDashboard.putData("Tele mode", teleopChooser);
		teleopChooser.addDefault("Vision, Default", new VoidCommand()); //Switch with teleop commands
		teleopChooser.addObject("No Vision", new VoidCommand());
		
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(manipulator);
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(new TestMaxVel());

		selectCommands = new SendableChooser<Command>();
		selectCommands.addObject("Climb", new ClimbCommand());

		SmartDashboard.putData("Command Select", selectCommands);

		SmartDashboard.putData(Scheduler.getInstance());
	}
}