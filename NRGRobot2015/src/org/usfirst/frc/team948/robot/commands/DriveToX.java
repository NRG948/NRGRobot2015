package org.usfirst.frc.team948.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToX extends CommandBase implements PIDOutput{

	double p = .5, i  = 0.02, d = 1.5, pidOutput, power, endingX;
	PIDController xPID = new PIDController(p,i,d, positionTracker, this);
    public DriveToX(double power, double endingX) {
        requires(drive);
        this.power = power;
        this.endingX = endingX;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.driveOnHeadingInit(power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.driveOnHeading(drive.getDesiredHeading(), power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return positionTracker.getX() > endingX;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.rawStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
}
