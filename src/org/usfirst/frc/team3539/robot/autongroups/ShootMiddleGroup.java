//package org.usfirst.frc.team3539.robot.autongroups;
//
//import org.usfirst.frc.team3539.robot.RobotMap;
//import org.usfirst.frc.team3539.robot.autoncommands.AutonDrive;
//import org.usfirst.frc.team3539.robot.autoncommands.AutonTurn;
//import org.usfirst.frc.team3539.robot.utilities.BulldogLogger;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
//public class ShootMiddleGroup extends CommandGroup {
//
//    public ShootMiddleGroup()
//    {
//        // Log the various steps of this auton
//        BulldogLogger.getInstance().logInfo("Starting ShootMiddleGroup auton");
//        
//        BulldogLogger.getInstance().logInfo("  Starting 1st drive forward");
//        addSequential(new AutonDrive(70));//-44?
//        BulldogLogger.getInstance().logInfo("    Ending 1st drive forward");
//        
//        BulldogLogger.getInstance().logInfo("  Starting 1st turn");
//<<<<<<< HEAD
//        
//        if(RobotMap.onBlueSide) addSequential(new AutonTurn(-95));
//        else addSequential(new AutonTurn(95)); // estimate, aim at tower
//        
//=======
//        addSequential(new AutonTurn(90)); // estimate, aim at tower
//>>>>>>> origin/master
//        BulldogLogger.getInstance().logInfo("    Ending 1st turn");
//        
//        // shoot
//        BulldogLogger.getInstance().logInfo("  Starting 2nd drive forward");
//    	addSequential(new AutonDrive(70));
//    	BulldogLogger.getInstance().logInfo("    Ending 2nd drive forward");
//    	
//    	BulldogLogger.getInstance().logInfo("  Starting 2nd turn");
//    	
//    	if(RobotMap.onBlueSide) addSequential(new AutonTurn(-90));
//    	else addSequential(new AutonTurn(90));
//    	
//    	BulldogLogger.getInstance().logInfo("    Ending 2nd turn");
//    	
//    	BulldogLogger.getInstance().logInfo("  Starting 3rd drive forward");
//    	addSequential(new AutonDrive(97));
//    	BulldogLogger.getInstance().logInfo("    Ending 3rd drive forward");
//    	
//    	BulldogLogger.getInstance().logInfo("  Starting 3rd turn");
//    	
//    	if(RobotMap.onBlueSide) addSequential(new AutonTurn(-45));
//    	else addSequential(new AutonTurn(45));
//    	
//    	BulldogLogger.getInstance().logInfo("    Ending 3rd turn");
//    	
//    	//(whatever tf it is to make it shoot)
//    	BulldogLogger.getInstance().logInfo("  Ending ShootMiddleGroup auton");
//    }
//}
