package org.usfirst.frc.team3539.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap//test
{
	//CONTROLLER PORT
	public static final int controllerOnePort = 0;
	public static final int controllerTwoPort = 1;

	//CONTROLLER AXIS
	public static final int X_AxisL = 0;
	public static final int Y_AxisL = 1;

	public static final int X_AxisR = 4;
	public static final int Y_AxisR = 5;

	public static final int RIGHT_TRIGGER = 3;
	public static final int LEFT_TRIGGER = 2;

	//BUTTON
	public static final int buttona = 1;
	public static final int buttonb = 2;
	public static final int buttonx = 3;
	public static final int buttony = 4;

	//BUMPER
	public static final int bumperl = 5;
	public static final int bumperr = 6;

	//TRIGGER MODIFIER
	public static boolean triggerModified = false;

	//DRIVE
	public static final int lfMotorTalon = 6;
	public static final int lbMotorTalon = 7;
	public static final int rfMotorTalon = 4;
	public static final int rbMotorTalon = 3;
	public static final int driveSolOn = 0;
	public static final int driveSolOff = 1;

	//PID
	public static double drivePea = .0001;
	public static double driveEye = 0;
	public static double driveDee = 0;

	public static double turnPea = .0003;
	public static double turnEye = .000001;
	public static double turnDee = .0001;

	public static double shootPea = 0;
	public static double shootEye = 0;
	public static double shootDee = 0;
	public static double shootEff = 0;

	//AIR
	public static final int compressor = 10;

	//AGITATOR
	public static final int agitatorTalon = 8;
	public static double agitatorSpeed = -.6;
	public static double unjamAgitatorSpeed = .6;

	//INTAKE
	public static final int intakeMotorTalon = 5;
	public static double climbSpeed = 1;
	public static double intakeSpeed = 1;
	public static double unjamIntakeSpeed = -1;

	//LOCK (in intake)
	public static final int lockSolOn = 6;
	public static final int lockSolOff = 7;

	//SHOOTER
	public static final int shooterTwoMotorTalon = 1;
	public static final int shooterOneMotorTalon = 2;
	public static double shootSpeed = .7;
	public static final double shooterSpeedModifier = 1.0;
	public static final int shooterServoTalon = 9;
	public static double shooterRpm = 0;
	public static double selectSpeed = 0;

	//GEAR
	public static final int hoodSolOn = 2;
	public static final int hoodSolOff = 3;
	public static final int gearSolOn = 4;
	public static final int gearSolOff = 5;

	//PCM
	public static final int pcm = 10;

	//LIGHT
	public static int ballCount;
}