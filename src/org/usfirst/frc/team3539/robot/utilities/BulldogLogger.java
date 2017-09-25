package org.usfirst.frc.team3539.robot.utilities;

import java.text.SimpleDateFormat;
//import java.util.Iterator;
//import java.util.Random;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * Logging class for all Team 3539 file logging Somewhat similiar to Log4J
 * 
 * @author Kevin Hein
 * @author Programmers of 3539
 * 
 * @version 3.539
 *
 */

public class BulldogLogger
{
	private static BulldogLogger bl;

	// Constants
	private static int DEBUG = 0; // Print -- logs in periodic
	private static int INFO = 1; // No Print -- logs in periodic
	private static int ERROR = 3; // Print -- logs in periodic
	private static int COMMAND = 4; // Print -- logs in command
	private static int EVENT = 5; // Print -- logs in event

	// TODO: second base file for backup, add log directory for flash drive
	private static String PERIODIC_BASE_FILE = "periodicLog";
	private static String EVENT_BASE_FILE = "eventLog";
	private static String COMMAND_BASE_FILE = "commandLog";
	private static String RIO_DIR = "/home/lvuser/logs/";
	private static String FLASH_DIR = "/u/";

	// Instance variables
	private int loggingLevel = 0;

	private File periodicFile;
	private File eventFile;
	private File commandFile;

	private PrintStream periodicStream;
	private PrintStream eventStream;
	private PrintStream commandStream;

	private int entryCount;

	// Year, Month, day
	// Hour Minute Seconds Millisecond

	private int fileGeneration = 10;

	private boolean hasFiles = false;

	/**
	 * public constructor
	 * 
	 * @param enableLogging
	 *            If is___Logging is false - no file is created If is___Logging
	 *            is true - a log file is created
	 */
	private BulldogLogger(boolean isPeriodicLogging, boolean isEventLogging, boolean isCommandLogging)
	{
		startLogging(isPeriodicLogging, isEventLogging, isCommandLogging);
	}

	public void startLogging(boolean isPeriodicLogging, boolean isEventLogging, boolean isCommandLogging)
	{
		System.out.println("Started logging");
		periodicStream = createFile(periodicFile, PERIODIC_BASE_FILE, periodicStream, isPeriodicLogging);
		eventStream = createFile(eventFile, EVENT_BASE_FILE, eventStream, isEventLogging);
		commandStream = createFile(commandFile, COMMAND_BASE_FILE, commandStream, isCommandLogging);
		hasFiles = true;
	}

	public static BulldogLogger getInstance()
	{
		if (bl == null)
		{
			bl = new BulldogLogger(true, true, true);
			return bl;
		}
		else
			return bl;
	}

	//if the directory does not exist, this code will not create it
	//home/lvuser/logs does not exist by default
	private PrintStream createFile(File file, String fileName, PrintStream stream, boolean isEnabled)
	{
		if (isEnabled)
		{
			entryCount = 0;

			// Name the log file

			String appendFileName = getDate() + "_" + getTime() + "_" + fileName + ".log"; //replace 10 with filegeneration

			

			// Place the log file

			try // Put file on Flash Drive
			{
				file = new File(FLASH_DIR + appendFileName);
				stream = new PrintStream(new FileOutputStream(file));
				System.out.println("==========================================================");
				System.out.println(" FILE IS ON THE USB STICK");
				System.out.println("==========================================================");

			}
			catch (FileNotFoundException e)
			{
				try // If it fails, put file on RIO
				{
					System.out.println("==========================================================");
					System.out.println(" FILE IS ON THE ROBORIO");
					System.out.println("==========================================================");
					
					file = new File(RIO_DIR + appendFileName);
					stream = new PrintStream(new FileOutputStream(file));
					
				}
				catch (FileNotFoundException ee)
				{
					ee.printStackTrace();
					
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("WARNING!!! The file " + fileName + " is NOT being used");
		}
		
		return stream;
	}

	// This stores Data about the day and persists between runs
	// public void getDataFile()
	// {
	// try
	// {
	//
	// }
	// catch (Exception e)
	// {
	//
	// e.printStackTrace();
	// }
	//
	// File folderUSB = new File(""); // USB
	// File folderRIO = new File("");// RIO
	//
	// // put a singleton style file creator here. 1 per directory (directory
	// // per day needs to work first);
	//
	// // check if directory of the day exists.
	// // if it does not then create a new data file inside the date directory.
	// // fill the data with defaults to be read
	//
	// // if the directory exists then check for file
	// // if file doesnt exist then create defaults
	// // if file does exist then read all the defaults and load the variables
	//
	// // Lets use a .JSON file for this because it's easier
	//
	// JSONParser parser = null;
	// JSONObject jsonObject = null;
	//
	// try
	// {
	// // FindFolder + date.json
	// parser = new JSONParser();
	// jsonObject = (JSONObject) parser.parse(new FileReader(FLASH_DIR +
	// getDate() + ".json"));
	// }
	// catch (Exception e)
	// {
	// try
	// {
	// parser = new JSONParser();
	// jsonObject = (JSONObject) parser.parse(new FileReader(RIO_DIR + getDate()
	// + ".json"));
	// }
	// catch (Exception ee)
	// {
	// ee.printStackTrace();
	// }
	// }
	//
	// try
	// {
	// System.out.println(jsonObject);
	//
	// String name = (String) jsonObject.get("name");
	// System.out.println(name);
	//
	// long age = (Long) jsonObject.get("age");
	// System.out.println(age);
	//
	// // loop array
	// // JSONArray msg = (JSONArray) jsonObject.get("messages");
	// // Iterator<String> iterator = msg.iterator();
	// while (iterator.hasNext())
	// {
	// System.out.println(iterator.next());
	// }
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// }

	// TimeStamps-------
	public String getDate()
	{
		return new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());
	}

	public String getTime()
	{
		return new SimpleDateFormat("HH:mm:ss:SSS").format(new java.util.Date());
	}
	// -------


	/**
	 * Log a debug message
	 * 
	 * @param msg
	 *            - String to log
	 */
	public void logDebug(String msg)
	{
		if (loggingLevel >= BulldogLogger.DEBUG)
		{
			log(BulldogLogger.DEBUG, msg, true);
		}
	}

	/**
	 * Log an informational message
	 * 
	 * @param msg
	 *            - String to log
	 */
	public void logInfo(String msg)
	{
		log(BulldogLogger.INFO, msg, true);
	}

	
	/**
	 * Log an error message
	 * 
	 * @param msg
	 *            - String to log
	 */
	public void logError(String msg)
	{
		log(BulldogLogger.ERROR, msg, true);
	}

	/**
	 * Log a Command message
	 * 
	 * @param msg
	 *            - String to log
	 */
	public void logCommand(String msg)
	{
		log(BulldogLogger.COMMAND, msg, true);
	}

	/**
	 * Log an Event message
	 * 
	 * @param msg
	 *            - String to log
	 */
	public void logEvent(String msg)
	{
		log(BulldogLogger.EVENT, msg, true);
	}

	/**
	 * Private method to write the data to a file. Method will get a string that
	 * contains a timestamp - out to the nanosecond - and also adds in a
	 * constant string of DEBUG, INFO, ERROR, COMMAND, or EVENT to distinguish
	 * what is in the logfile or what file to place it in
	 * 
	 * @param type
	 *            - Type of logging message
	 * @param msg
	 *            - String to write out
	 */

	private void log(int type, String msg, boolean printlin)
	{
		if (hasFiles)
		{
			entryCount++;

			// Write the logging information out to the log file
			// First - get the current time stamp
			String timeStamp = getTime();

			String logMsg = new String();

			switch (type)
			{
			case 0:
				logMsg = "[DEBUG - " + timeStamp + "] ";
				break;
			case 1:
				logMsg = "[INFO  - " + timeStamp + "] ";
				break;
			case 3:
				logMsg = "[ERROR - " + timeStamp + "] ";
				break;
			case 4:
				logMsg = "[COMMAND - " + timeStamp + "] ";
				break;
			case 5:
				logMsg = "[EVENT - " + timeStamp + "] ";
				break;
			default:
				logMsg = "[WHAT? - " + timeStamp + "] ";
			}

			logMsg = logMsg + msg;

			try // we can make this optional if periodic is getting too
				// cluttered
			{
				periodicStream.println(logMsg);
				
				this.periodicStream.flush();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			if (type == BulldogLogger.EVENT)
			{
				try
				{
					eventStream.println(logMsg);
					this.eventStream.flush();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			if (type == BulldogLogger.COMMAND)
			{
				try
				{
					commandStream.println(logMsg);
					this.commandStream.flush();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			if (printlin)
			{
				System.out.println(logMsg);
			}
		}
		else
			System.out.println("No Files!!!");
	}

	public void finishLogging()
	{
		String s = "We logged " + entryCount + " things! log log log";

		log(10, s, true);

		hasFiles = false;

		try
		{
			this.periodicStream.flush();
			this.periodicStream.close();
			this.eventStream.flush();
			this.eventStream.close();
			this.commandStream.flush();
			this.commandStream.close();
		}
		catch (Exception e)
		{

		}
	}
}

/*
 * try { periodicStream.println(logMsg); if (periodicStream.checkError()) {
 * System.out.println(
 * "flash drive removed, detected ==========================================================================="
 * ); System.out.println("done making new file on rio FIRST ONE"); } } catch
 * (Exception e) { System.out.println("error in wtf"); e.printStackTrace();
 * System.out.println("done making new file on rio SECOND ONE"); }
 */