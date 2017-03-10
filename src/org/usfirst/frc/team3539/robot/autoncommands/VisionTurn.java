package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.utilities.DpadButton;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class VisionTurn extends PIDCommand
{
    private double newAngle;

    public VisionTurn(double angle)
    {
        super("test", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
        newAngle = angle;
        requires(Robot.driveTrain);
        System.out.println("CON");
    }

    protected void initialize()
    {
        this.getPIDController().setPID(RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);

        Robot.driveTrain.gyroReset();
        Robot.driveTrain.zeroEncoders();
        
        this.setSetpoint(Robot.raspberry.getAngle());

        this.getPIDController().setOutputRange(-.6, .6); // original -.5. .5
        this.getPIDController().setAbsoluteTolerance(.2);
    }

    protected void execute()
    {
    }

    protected boolean isFinished()
    { 
        return !Robot.oi.visionButton.checkValue();
    }

    protected void end()
    {
        Robot.driveTrain.stopTrain();
    }

    protected void interrupted()
    {
        end();
    }

    @Override
    protected double returnPIDInput()
    {
        return Robot.driveTrain.getGyroAngle();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        Robot.driveTrain.turnLinear(output);
    }
}
