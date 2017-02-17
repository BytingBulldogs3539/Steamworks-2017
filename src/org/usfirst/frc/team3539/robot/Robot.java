package org.usfirst.frc.team3539.robot;

import org.usfirst.frc.team3539.robot.commands.*;
import org.usfirst.frc.team3539.robot.commands.VoidCommand;
import org.usfirst.frc.team3539.robot.subsystems.GearManipulator;
import org.usfirst.frc.team3539.robot.subsystems.Intake;
import org.usfirst.frc.team3539.robot.subsystems.Shooter;
import org.usfirst.frc.team3539.robot.subsystems.ShooterAim;
import org.usfirst.frc.team3539.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
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
	
	//Encoders
	Encoder ShooterEncoder = new Encoder(0,1,false,Encoder.EncodingType.k4X);
	
	//SUBSYSTEMS
	public static final TankDrive tankDriveTrain = new TankDrive();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final GearManipulator manipulator = new GearManipulator();
	//public static final ShooterAim aim = new ShooterAim();

	public static Compressor c;
	public static OI oi;
	//public static RobotTemplate i2c;

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
		
		selectCommands = new SendableChooser<Command>();
		selectCommands.addObject("Climb", new ClimbCommand());
		selectCommands.addObject("Climb", new LockCommand());
		
		SmartDashboard.putData("Command Select", selectCommands);
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
		//autoMode = (Command) autoChooser.getSelected(); //Auton disabled
		if(autoMode != null)
			autoMode.start();
	}

	// This function is called periodically during autonomous

	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		update();
	}

	public void teleopInit()
	{
		if(autoMode != null)
			autoMode.cancel();
		
		System.out.println("teleopInit");
	}

	// This function is called periodically during operator control

	public void teleopPeriodic()
	{
		//int tblLength = 0;
		
		//ITable temp = Scheduler.getInstance().getTable();
		
		Scheduler.getInstance().run();
		//Arduino.Write();
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
		intake.Update();
		oi.Update();
		shooter.Update();
		
		//SmartDashboard.putNumber("Robot gyro heading", Robot.driveTain.get());

		//SmartDashboard.putNumber("Shooter Throttle", Robot.shooter.getCurrentPower());

		//SmartDashboard.putBoolean("lock toggle", RobotMap.intake.isLockLocked());

		//SmartDashboard.putString("Shooter Angle", Robot.shooter.getangle());
	}
}