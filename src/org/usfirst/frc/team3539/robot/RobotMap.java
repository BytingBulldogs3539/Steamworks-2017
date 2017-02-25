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
	public static final int controllerOnePort = 1;
	public static final int controllerTwoPort = 0;

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
	public static double drivePea = .000054;
	public static double driveEye = .0000000165;
	public static double driveDee = .00000001;

	public static double turnPea = .000054;
	public static double turnEye = .0000000165;
	public static double turnDee = .00000001;

	public static double shootPea = .000054;
	public static double shootEye = .0000000165;
	public static double shootDee = .00000001;
	
	//AIR
	public static final int compressor = 10;

	//AGITATOR
	public static final int agitatorTalon = 8;
	public static double agitatorSpeed = -.8;
	public static double unjamAgitatorSpeed = .8;

	//INTAKE
	public static final int intakeMotorTalon = 5;
	public static double climbSpeed = 1;
	public static double intakeSpeed = 1;
	public static double unjamIntakeSpeed = -1;

	//LOCK (in intake)
	public static final int lockSolOn = 6;
	public static final int lockSolOff = 7;

	//SHOOTER
	public static final int shooterOneMotorTalon = 1;
	public static final int shooterTwoMotorTalon = 2;
	public static final double shooterSpeedModifier = 1.0;
	public static final int shooterServoTalon = 9;
	public static double shooterRpm = 1000;

	//GEAR
	public static final int hoodSolOn = 2;
	public static final int hoodSolOff = 3;
	public static final int gearSolOn = 4;
	public static final int gearSolOff = 5;

	//PCM
	public static final int pcm = 10;

	//LIGHT
	public static int ballCount;
	
	//Conversions 0.00306796157
	public static final double wheelDiameter = 4;
	public static final double wheelRadius = wheelDiameter / 2;
	public static final double wheelCircumference = 2 * Math.PI * wheelRadius;
	public static final double inchesPerTick = wheelCircumference / 4096;
	public static final double ticksPerInch = 4096 / wheelCircumference;
	public static final double robotLength = 34;
}