package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;

/**
 *The robot rotates a given angle without using a PID controller.  It's faster than TurnAngle
 *command but not as precise.    
 */
public class TurnAngleQuick extends CommandBase {
	private double finalHeading;
	private double initialHeading;
	private double angle;
	private double power;
	private double toleranceInDegrees;
	 
	public TurnAngleQuick(double angle, double power, double toleranceInDegrees) {
		requires(drive);
		this.power = Math.abs(power);
		this.angle = angle;
		this.toleranceInDegrees = Math.abs(toleranceInDegrees);
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	initialHeading = drive.getDesiredHeading();
		finalHeading = initialHeading + angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (angle >= 0) {
    		drive.rawTankDrive(+power, -power);
    	}
    	else {
    		drive.rawTankDrive(-power, +power);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double currentHeading = RobotMap.driveGyro.getAngle();
		if (angle >= 0) {
			return currentHeading > (finalHeading - toleranceInDegrees); 
		} else { 
			return currentHeading < (finalHeading + toleranceInDegrees); 
		}
	}

    // Called once after isFinished returns true
    protected void end() {
    	drive.rawStop(); 
    	drive.setDesiredHeading(finalHeading);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { 
    	end(); 
    }
}
