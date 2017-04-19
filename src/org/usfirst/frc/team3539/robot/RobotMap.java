package org.usfirst.frc.team3539.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
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

	// AIR
	public static final int compressor = 10;

	// AGITATOR
	public static final int agitatorTalon = 8;
	public static double agitatorSpeed = -.8;
	public static double unjamAgitatorSpeed = .8;
	public static double agitatorRpm = 300;

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

	// CONVERSION
	public static final double wheelDiameter = 4;

	//////////////////////// PRACTICE BOT/////////////////////

	
	// PRACTICE CAMERA
	public static final int gearCamera = 0;
	public static final int shooterCamera = 1;
	
//	public static double Distance1 = 90;
//	public static double Distance2 = 204;
//	public static double RPM1 = 2755;
//	public static double RPM2 = 4140;
//	public static double Hood1 = 470;
//	public static double Hood2 = 993;
	
	public static double Distance1 = 104;
	public static double Distance2 = 204;
	public static double RPM1 = 3100;
	public static double RPM2 = 3925;
	public static double Hood1 = 680;//640
	public static double Hood2 = 1140;//1100
	
	public static double shooterCameraOffset = 3;
	public static double gearCameraOffset = 0;
	
	// PRACTICE PID
	public static double driveMultiplier = 1; // .665
	public static double drivePea = .000035;//00053;// .0005;
	public static double driveEye = 0;//.0000000005;// 0;	
	public static double driveDee = .00005;// .000009;

	public static double turnPea = .09;
	public static double turnEye = .00000005; // 5.0E-5
	public static double turnDee = .1;

	public static double vturnPea = .2;
	public static double vturnEye = .03;
	public static double vturnDee = .001;

	public static double shootPea = .26;
	public static double shootEye = 0;
	public static double shootDee = 18.0;
	public static double shootEff = .022; // .062635

	public static double hoodPea = .001;
	public static double hoodEye = 0;
	public static double hoodDee = .001;

	public static double agitatorPea = .002;
	public static double agitatorEye = 0;
	public static double agitatorDee = 5.0;
	public static double agitatorEff = .35;
	
	//PRACTICE AUTON
	public static double whiteLineDistance = 87-11;
	public static double sidePegTurn = 60;
	public static double sidePegDistance = 45;
	public static double outsideShootTurn = 130;
	public static double middleShootTurn = 80;
	public static double insideShootTurn = 0;
	public static double visionWait = .5;
	public static double hopperTurn = 91;
	public static double hopperShootTurn = 45;
	public static double farHopperDistance = whiteLineDistance + 57;
	public static double deadband = .45;
	
	
	

	//////////////////////// COMP BOT/////////////////////

	/*
	
	//COMP CAMERA
	public static final int gearCamera = 0;
	public static final int shooterCamera = 1;
	public static double Distance1 = 104;
	public static double Distance2 = 204;
	public static double RPM1 = 3100;
	public static double RPM2 = 3925;
	public static double Hood1 = 680;//640
	public static double Hood2 = 1140;//1100
	public static double shooterCameraOffset = -2.5;

	//COMP PID
	
	
	//public static double driveMultiplier = .8780;
	public static double driveMultiplier = 1; // .665
	public static double drivePea = .000035;//00053;// .0005;
	public static double driveEye = 0;//.0000000005;// 0;	
	public static double driveDee = .00005;// .000009;

	public static double turnPea = .09;
	public static double turnEye = .00000005;// 5.0E-8
	public static double turnDee = .1;

	public static double vturnPea = .2;
	public static double vturnEye = .03;
	public static double vturnDee = .001;

	public static double shootPea = .26;
	public static double shootEye = 0;
	public static double shootDee = 18.0;
	public static double shootEff = .022; // .062635

	public static double hoodPea = .001;
	public static double hoodEye = 0;
	public static double hoodDee = .001;

	public static double agitatorPea = .002;
	public static double agitatorEye = 0;
	public static double agitatorDee = 5.0;
	public static double agitatorEff = .35;
	
	//COMP AUTON
	public static double whiteLineDistance = 76;
	public static double sidePegTurn = 60;
	public static double sidePegDistance = 45+3+3;
	public static double outsideShootTurn = 130;
	public static double middleShootTurn = 70;
	public static double insideShootTurn = 0;
	public static double visionWait = .5;
	public static double hopperTurn = 91;
	
	public static double hopperShootTurn = 60;
	public static double farHopperDistance = 76 + 48;
	public static double deadband = .45;
	public static double gearCameraOffset = 0;
	*/
}
	