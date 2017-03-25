package org.usfirst.frc.team3539.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap// test
{
	// CONTROLLER PORT
	public static final int controllerOnePort = 1;
	public static final int controllerTwoPort = 0;

	// CONTROLLER AXIS
	public static final int X_AxisL = 0;
	public static final int Y_AxisL = 1;

	public static final int X_AxisR = 4;
	public static final int Y_AxisR = 5;

	public static final int RIGHT_TRIGGER = 3;
	public static final int LEFT_TRIGGER = 2;

	// BUTTON/
	public static final int buttona = 1;
	public static final int buttonb = 2;
	public static final int buttonx = 3;
	public static final int buttony = 4;
	public static final int buttonStart = 8;
	public static final int buttonLS = 9;
	public static final int buttonRS = 10;

	// BUMPER
	public static final int bumperl = 5;
	public static final int bumperr = 6;

	// TRIGGER MODIFIER
	public static boolean triggerModified = false;

	// DRIVE
	public static final int lfMotorTalon = 6;
	public static final int lbMotorTalon = 7;
	public static final int rfMotorTalon = 4;
	public static final int rbMotorTalon = 3;
	public static final int driveSolOn = 0;
	public static final int driveSolOff = 1;

	// PID

	/*
	 * Practice bot PID 3/14/2017. 6:30pm
	 */
	public static double driveMultiplier = .665;  //.89
	
	public static double drivePea = .00053;//.00015;
 	public static double driveEye = .0000000005;//0;
 	public static double driveDee = .0002;//.000009;
	
	//0.000955; // 0.000955
//0.000000355; // 0.0000000165
//0.000455; // 0.0000094

	public static double turnPea = .1;
	public static double turnEye = .00005; // 5.0E-5
	public static double turnDee = .2;

	public static double vturnPea = .2;
	public static double vturnEye = .03;
	public static double vturnDee = .001;

	public static double shootPea = .26;
	public static double shootEye = 0;
	public static double shootDee = 18.0;
	public static double shootEff = .023; // .062635

	public static double hoodPea = .001;
	public static double hoodEye = 0;
	public static double hoodDee = .001;

	public static double agitatorPea = .002;
	public static double agitatorEye = 0;
	public static double agitatorDee = 5.0;
	public static double agitatorEff = .35;

	/*
	 * COMP BOT PID 3/21/2017 6:40PM 
	 * 
	 * 
	public static double driveMultiplier = .7346;
	 * 
	 * public static double drivePea = 0.000955;
	 * public static double driveEye = 0.0000000165; public static double
	 * driveDee = 0.0000094;
	 * 
	 * public static double turnPea = .1; public static double turnEye = .00005;
	 * //5.0E-5 public static double turnDee = .2;
	 * 
	 * public static double vturnPea= .2; public static double vturnEye = .03;
	 * public static double vturnDee = .001;
	 * 
	 * public static double shootPea = .26; public static double shootEye = 0;
	 * public static double shootDee = 18.0; public static double shootEff =
	 * .022; //.062635
	 * 
	 * public static double hoodPea = .001; public static double hoodEye = 0;
	 * public static double hoodDee = .001;
	 * 
	 * public static double agitatorPea = .002; public static double agitatorEye
	 * = 0; public static double agitatorDee = 5.0; public static double
	 * agitatorEff = .35;
	 */

	// AIR
	public static final int compressor = 10;

	// AGITATOR
	public static final int agitatorTalon = 8;
	public static double agitatorSpeed = -.8;
	public static double unjamAgitatorSpeed = .8;
	public static double agitatorRpm = 300; // was 600 (Did not work)

	// INTAKE
	public static final int intakeMotorTalon = 5;
	public static double climbSpeed = 1;
	public static double intakeSpeed = 1;
	public static double unjamIntakeSpeed = -1;

	// LOCK (in intake)
	public static final int lockSolOn = 6;
	public static final int lockSolOff = 7;

	// SHOOTER
	public static final int shooterOneMotorTalon = 1;
	public static final int shooterTwoMotorTalon = 2;
	public static final int shooterServoTalon = 9;
	public static double shooterRpm = -3500;
	public static double hoodTarget = 300;

	// GEAR
	public static final int hoodSolOn = 2;
	public static final int hoodSolOff = 3;
	public static final int gearSolOn = 4;
	public static final int gearSolOff = 5;

	// PCM
	public static final int pcm = 10;

	// LIGHT
	public static int ballCount;

	// CONVERSION
	public static final double wheelDiameter = 4;

	// CAMERA
	public static final int gearCamera = 0;
	public static final int shooterCamera = 1;
	public static double Distance1 = 90;
	public static double Distance2 = 204;
	public static double RPM1 = 2860;
	public static double RPM2 = 4300;
	public static double Hood1 = 467;
	public static double Hood2 = 993;
	
	
}