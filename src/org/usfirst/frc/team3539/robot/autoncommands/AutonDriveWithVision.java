package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class AutonDriveWithVision extends PIDCommand
{
	private double myTicks;
	
		private PIDController AnglePID;
		private double angleCorrection = 0;
	  
		//private final PIDOutput angle_output = useAnglePIDOutput;
	  /**
	   * A source which calls {@link PIDCommand#returnPIDInput()}.
	   */
	  private final PIDSource angle_output_source = new PIDSource() {
	    public void setPIDSourceType(PIDSourceType pidSource) {
	    }
	
	    public PIDSourceType getPIDSourceType() {
	      return PIDSourceType.kDisplacement;
	    }
	
	    public double pidGet() {
	      return returnAnglePIDInput();
	    }
	  };
	    public final PIDOutput angle_output = new PIDOutput() {
	        double output;
	        
	        public void DummyPIDOutput()
	        {
	            output = 0;
	        }

	        public void pidWrite(double output) {
	            this.output = output;
	        }

	        public double getOutput() {
	            return output;
	        } 
	    };
	public AutonDriveWithVision(double inches)
	{
		super("test", RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		Robot.driveTrain.zeroEncoders();
		myTicks = Robot.driveTrain.inchToEnc(inches);
		requires(Robot.driveTrain);
		
		this.getPIDController().setOutputRange(-.7, .7);
		PIDController anglePID = new PIDController(.001, 0, .001, angle_output_source, angle_output);
		setSetpoint(myTicks);
	}
	public double returnAnglePIDInput()
	{
		return Robot.raspberry.getAngle();
	}
	
	public void useAnglePIDOutput(double output)
	{
		angleCorrection = output;
	}
	
	protected void initialize()
	{
		this.getPIDController().setPID(RobotMap.drivePea, RobotMap.driveEye, RobotMap.driveDee);
		Robot.driveTrain.zeroEncoders();
		this.setSetpoint(myTicks);
		this.getPIDController().setAbsoluteTolerance(2000);
	}

	protected void execute()
	{
//		System.out.println("Drive On Target: " + this.getPIDController().onTarget());
	}

	protected boolean isFinished()
	{
		return this.getPIDController().onTarget();
	}

	protected void end()
	{
		Robot.driveTrain.zeroEncoders();
		Robot.driveTrain.stopTrain();
	}

	protected void interrupted()
	{
		System.out.println("AutonDrive interrupted");
		end();
	}

	@Override
	protected double returnPIDInput()
	{
		return Robot.driveTrain.getBalancedEncoderPosition();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		Robot.driveTrain.driveArcade(output, angleCorrection);
	}
}
