//package org.usfirst.frc.team3539.robot.utilities;
//
//import java.text.SimpleDateFormat;
//import java.io.*;
//
///**
// * Logging class for all Team 3539 file logging
// * Somewhat similiar to Log4J
// * 
// * @author Kevin Hein
// * 
// * @version 1.0
// *
// */
//public class BulldogLogger 
//{
//    private static BulldogLogger _logger;
//    
//    // Constants
//    private static int DEBUG        = 0;
//    private static int INFO         = 1;
//    private static int WARN         = 2;
//    private static int ERROR        = 3;
//    
//    private static String BASE_FILE = "robotLog.";
//    private static String LOG_DIR   = "/home/lvuser/logs/";
//
//    // Instance variables
//    private int loggingLevel   = 0;
//    private boolean shouldILog = true;
//    private File logFile;
//    private PrintStream outStream;
//    
//    /**
//     * Private constructor (singleton Pattern)
//     * 
//     * @param enableLogging - If enableLogging is false - no file is created
//     *                        If enableLogging is true - a log file is created
//     */
//    private BulldogLogger(boolean enableLogging)
//    {
//        if ( enableLogging )
//        {
//            shouldILog = true;
//
//            // Create the log file
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
//            
//            String fileName = LOG_DIR + BASE_FILE + timeStamp + ".log";
//            
//            logFile = new File(fileName);
//            
//            try 
//            {
//                outStream = new PrintStream(new FileOutputStream(logFile));
//            } 
//            catch (FileNotFoundException e) 
//            {
//                // To do - Figure out something useful to do if an exception is thrown
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            // Don't create the file
//            shouldILog = false;
//            loggingLevel = -1;
//        }
//    }
//
//    /**
//     * Initialize the logging mechanism.   If logging is turned on, then rock and roll
//     * Otherwise - don't create the log file
//     * 
//     * @param loggingOn
//     */
//    public static void initialize(boolean loggingOn)
//     {
//        // First time I call it - check to see if I should log or not
//        _logger = new BulldogLogger(loggingOn);
//    }
//    
//    /**
//     * Singleton pattern return function
//     * 
//     * @return
//     */
//    public static BulldogLogger getInstance()
//    {
//    	if ( _logger == null)
//    	{
//    		_logger = new BulldogLogger(true);
//    	}
//    	
//        return(_logger);
//    }
//
//    /**
//     * Log a debug message
//     * 
//     * @param msg - String to log
//     */
//    public void logDebug(String msg)
//    {
//        if ( loggingLevel >= BulldogLogger.DEBUG)
//        {
//            log(BulldogLogger.DEBUG,msg);
//        }
//    }
//
//    /**
//     * Log an informational message
//     * 
//     * @param msg - String to log
//     */
//    public void logInfo(String msg)
//    {
//        //if ( loggingLevel >= BulldogLogger.INFO)
//        //{
//            log(BulldogLogger.INFO,msg);
//        //}
//    }
//    
//    /**
//     * Log a warning message
//     * 
//     * @param msg - String to log
//     */
//    public void logWarn(String msg)
//    {
//        //if ( loggingLevel >= BulldogLogger.WARN)
//        //{
//            log(BulldogLogger.WARN,msg);
//        //}
//        
//    }
//    
//    /**
//     * Log an error message
//     * 
//     * @param msg - String to log
//     */
//    public void logError(String msg)
//    {
//        //if ( loggingLevel >= BulldogLogger.ERROR )
//        //{
//            log(BulldogLogger.ERROR,msg);
//        //}
//    }
//    
//    /**
//     * Private method to write the data to a file.   Method will create a string
//     * that contains a timestamp - out to the nanosecond - and also adds in a constant
//     * string of DEBUG, INFO, WARN or ERROR to distinguish what is in the logfile
//     * 
//     * @param type - Type of logging message
//     * @param msg - String to write out
//     */
//    private void log(int type, String msg)
//    {
//        if ( shouldILog == false) { return; }
//        
//        // Write the logging information out to the log file
//        // First - get the current timestamp
//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.sssSSS").format(new java.util.Date());
// 
//        String logMsg = new String();
//        
//        switch (type)
//        {
//          case 0:
//              logMsg = "[DEBUG - " + timeStamp + "] ";
//              break;
//          case 1:
//              logMsg = "[INFO  - " + timeStamp + "] ";
//              break;
//          case 2:
//              logMsg = "[WARN  - " + timeStamp + "] ";
//              break;
//          case 3:
//              logMsg = "[ERROR - " + timeStamp + "] ";
//              break;
//          default:
//              logMsg = "[WHAT? - " + timeStamp + "] ";
//        }
//                  
//        logMsg = logMsg + msg;
//        
//        try
//        {
//            outStream.println(logMsg);
//        }
//        catch ( Exception e)
//        {
//            // To do - Figure out something useful to do if an exception is thrown
//            e.printStackTrace();
//        }
//    }
//    
//    public void finishLogging()
//    {
//        this.outStream.flush();
//        this.outStream.close();
//    }
//}
