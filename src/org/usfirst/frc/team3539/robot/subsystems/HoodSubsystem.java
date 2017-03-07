package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;
import org.usfirst.frc.team3539.robot.commands.SetPointCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HoodSubsystem extends BulldogSystem
{
    private CANTalon shooterHoodMotor;

    DigitalInput lightSensorOne = new DigitalInput(1);
    DigitalInput lightSensorTwo = new DigitalInput(0);
    
    public HoodSubsystem()
    {
        super("HoodSubsystem");

        shooterHoodMotor = new CANTalon(RobotMap.shooterServoTalon);
        shooterHoodMotor.setSafetyEnabled(false);
        shooterHoodMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);

    }

    public void setMotorPower(double power)
    {
        shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        shooterHoodMotor.set(power);
    }

    public void settempHoodAngle(double encoderValue)
    {
        shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.Position);
        shooterHoodMotor.set(encoderValue);
    }
    
    public void setHoodAngle(double encoderValue)
    {
        shooterHoodMotor.changeControlMode(CANTalon.TalonControlMode.Position);
        shooterHoodMotor.setP(RobotMap.hoodPea);
        shooterHoodMotor.setI(RobotMap.hoodEye);
        shooterHoodMotor.setD(RobotMap.hoodDee);
    
        System.out.println("shooterHoodMotor set: " + encoderValue);
        shooterHoodMotor.set(encoderValue);
    }
    
    public void zeroHoodEncoders()
    {
        shooterHoodMotor.setEncPosition(0);
    }

        
    public double getHoodPosition()
    {
        return shooterHoodMotor.getPulseWidthPosition();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void Update()
    {
        SmartDashboard.putDouble("Shooter Hood Encoder", getHoodPosition());
        
        RobotMap.hoodTarget = SmartDashboard.getDouble("hoodTarget");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void SmartInit()
    {
        SmartDashboard.putDouble("hoodTarget", RobotMap.hoodTarget);
        SmartDashboard.putDouble("Shooter Hood Encoder", 0);
    }

    public void initDefaultCommand()
    {
    }
}