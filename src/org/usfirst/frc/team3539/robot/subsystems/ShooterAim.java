package org.usfirst.frc.team3539.robot.subsystems;

import org.usfirst.frc.team3539.robot.commands.ShooterAimCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterAim extends Subsystem {


	private CANTalon motor;

	// Put methods for controlling this subsystem
    // here. Call these from Commands.

	public ShooterAim()
	{
		super("ShooterAim");
		motor = new CANTalon(1);
		
	}
    public void initDefaultCommand()
    {
        setDefaultCommand(new ShooterAimCommand());
    }
}