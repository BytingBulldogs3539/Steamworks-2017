package autoncommands;

import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *-3150 shooter rpm   250 Agitator Rpm 1600 Hood Encoder
 */
public class AutonShoot extends Command
{

    public AutonShoot()
    {
        requires(Robot.shooter);
    }

    protected void initialize()
    {
        Robot.shooter.resetShooterPID();
        Robot.shooter.setShooterPID();
        Robot.shooter.resetAgitatorPID();
        Robot.shooter.setAgitatorPID();
    }

    protected void execute()
    {
        Robot.shooter.startShooter(RobotMap.shooterRpm);
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Robot.shooter.startAgitator(-RobotMap.agitatorRpm);
        try
        {
            Thread.sleep(8000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected boolean isFinished()
    {
        return true;
    }

    protected void end()
    {
        Robot.shooter.resetShooterPID();
        Robot.shooter.resetAgitatorPID();
    }

    protected void interrupted()
    {
        end();
    }
}