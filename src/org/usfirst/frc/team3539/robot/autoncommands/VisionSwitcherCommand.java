package org.usfirst.frc.team3539.robot.autoncommands;

import org.usfirst.frc.team3539.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionSwitcherCommand extends Command {

	private boolean visionOn;
    public VisionSwitcherCommand(boolean vision)
    {
        this.visionOn = vision;
    }

    protected void initialize() {
    }

    protected void execute()
    {
    	RobotMap.isVisionTracking = this.visionOn;
    }

    protected boolean isFinished()
    {
        return true;
    }

    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
