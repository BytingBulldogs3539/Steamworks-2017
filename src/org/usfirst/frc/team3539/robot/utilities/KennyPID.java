package org.usfirst.frc.team3539.robot.utilities;

/**
 * This class is a combination of PID logic and 
 * range-based speed controller computation to
 * accurately transit the robot to a specific 
 * position based on a series of mathematical 
 * inputs.
 * 
 * @author Kenny T.
 * @version .7
 */

public class KennyPID
{
	private double start;
	private double target;

	private double targetishTop;
	private double targetishBottom;

	private double accBottom;
	private double accTop;

	private double highBottom;
	private double highTop;

	private int inRange;
	private int inRangeTarget;

	private boolean itsBackwards;

	private boolean isFinished;

	public KennyPID(double from, double to, double highRange, double acceptableRange, double targetishRad, int countOnTarget,
			boolean backwards)
	{
		start = from;
		target = to;

		targetishTop = target + targetishRad;
		targetishBottom = target - targetishRad;

		itsBackwards = backwards;

		accBottom = target - acceptableRange;
		accTop = target + acceptableRange;

		highBottom = target - highRange;
		highTop = target + highRange;

		inRangeTarget = countOnTarget;
	}

	/*public KennyPID(double from, double to, double acceptableRange, Gyro gyr)
	{
	
	}*/

	public double normalizeLinear(double left, double right)
	{
		return (left + right) / 2;
	}

	public double linearCalc(double leftEncoder, double rightEncoder)
	{
		if(itsBackwards)
		{
			rightEncoder = rightEncoder * -1;
		}

		double linear = normalizeLinear(leftEncoder, rightEncoder);

		if(linear <= highBottom)
		{
			System.out.println("Bottom High Speed Range .A");
			return 1;
		}
		if(linear > highBottom && linear <= accBottom)
		{
			System.out.println("Bottom Decay Range .B");
			return .5;
		}
		if(linear > accBottom && linear <= targetishBottom)
		{
			System.out.println("Bottom Acceptable Range .C");
			inRange = 0;
			return .3;
		}
		if(linear > targetishBottom && linear <= targetishTop)
		{
			System.out.println("Perfect Range .D");
			inRange++;
		}
		if(linear > targetishTop && linear <= accTop)
		{
			System.out.println("Top Acceptable Range .E");
			inRange = 0;
			return -3.;
		}
		if(linear > accTop && linear <= highTop)
		{
			System.out.println("Top Decay Range .F");
			return -.5;
		}
		if(linear > highTop)
		{
			System.out.println("Top High Speed Range .G");
			return -1;
		}

		if(inRange == inRangeTarget)
		{
			System.out.println("Reached inRange");
			isFinished = true;
			return 0;
		}

		System.out.println("!Warning! default return!");
		return 0;
	}

	public void pivotCalc(double gyroAngle)
	{

	}

	public void printValues()
	{

		System.out.println("start: " + start);
		System.out.println("target: " + target);

		System.out.println("targetishTop: " + targetishTop);
		System.out.println("targetishBottom: " + targetishBottom);

		System.out.println("accBottom: " + accBottom);
		System.out.println("accTop: " + accTop);

		System.out.println("highBottom: " + highBottom);
		System.out.println("highTop: " + highTop);

		System.out.println("inRange: " + inRange);
		System.out.println("inRangeTarget: " + inRangeTarget);

		System.out.println("itsBackwards: " + itsBackwards);

		System.out.println("isFinished: " + isFinished);
	}

	public boolean isFinished()
	{
		return isFinished;
	}
}