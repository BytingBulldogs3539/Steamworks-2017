package org.usfirst.frc.team3539.robot.autoncommands;
import org.usfirst.frc.team3539.robot.Robot;
import org.usfirst.frc.team3539.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class GearVisionTurn extends PIDCommand
{
        private double newAngle;

        public GearVisionTurn(double angle)
        {
            super("test", RobotMap.turnPea, RobotMap.turnEye, RobotMap.turnDee);
            newAngle = angle;
            requires(Robot.driveTrain);
            System.out.println("CON");
        }

        protected void initialize()
        {
            this.getPIDController().setPID(.2, .03, .001);

            Robot.driveTrain.gyroReset();
            Robot.driveTrain.zeroEncoders();
            Robot.raspberry.UpdateCamera(0);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            this.setSetpoint(0);


            this.getPIDController().setOutputRange(-1, 1); // original -.5. .5
            this.getPIDController().setAbsoluteTolerance(.2);
        }

        protected void execute()
        {
        }

        protected boolean isFinished()
        {
            
            return true;
        }

        protected void end()
        {
            Robot.driveTrain.stopTrain();
            Robot.raspberry.UpdateCamera(0);
        }

        protected void interrupted()
        {
            end();
        }

        @Override
        protected double returnPIDInput()
        {
            return Robot.raspberry.getAngle();
        }

        @Override
        protected void usePIDOutput(double output)
        {
            Robot.driveTrain.turnLinear(output);
        }
    }
