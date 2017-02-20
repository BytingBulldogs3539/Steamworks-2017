package org.usfirst.frc.team3539.robot.utilities;

import edu.wpi.first.wpilibj.Encoder;

public class PIDtake2
{
	private final double P; // 0<p<1
	private final double I; // accum speed
	private final double D;
	private int currentencpos;
	private int targetencpos;
	private double p_motoroutput;
	private int univerror;
	private final int irange; //   +/-
	private final int izone_lowbound;  // apply ir
	private final int izone_highbound;
	private int accumulation;
	private Encoder e;
	
	public PIDtake2(double p, double i, double d, int target, Encoder e)
	{
		this.P = p;
		this.I = i;
		this.D = d;
		targetencpos = target;
		getEncPos();
		univerror = targetencpos - currentencpos;
		irange = (int) ((1/24) * univerror);
		izone_lowbound = (int) (targetencpos - irange);
		izone_highbound = (int) (targetencpos + irange);
		this.e = e;
		accumulation = 0;
		p_motoroutput = 0;
	}
	
	public void getEncPos()
	{
		currentencpos = (e.get());
	}
	
	public boolean checkIzone()
	{
		if(izone_lowbound > currentencpos || izone_highbound < currentencpos)
		{
			return false;
		}
		else return true;
	}
	
	public void clearAccum() // only if needed in specific circumstances
	{
		accumulation = 0;
	}
	
	public void calcError()
	{
		univerror = targetencpos - currentencpos;
	}
	
	public double UpdatePID() // add common ratio, etc
	{
		getEncPos();
		if(checkIzone() == false) accumulation = 0;
		
		//still more math to do in here
		
		return p_motoroutput;
	}
	
	
	
	
	
	
	
	
	
	
	
}