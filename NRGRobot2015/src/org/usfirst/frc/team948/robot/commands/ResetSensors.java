package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;

/**
 *
 */
public class ResetSensors extends CommandBase {

	public ResetSensors() {
		requires(drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		RobotMap.driveGyro.reset();
		RobotMap.rightEncoder.reset();
		RobotMap.leftEncoder.reset();
		positionTracker.setPosition(0,0);
		drive.setDesiredHeading(0.0);
	}

	/**
	 * return true if the angle of the Gyro is within 0.5 degrees of 0
	 */
	protected boolean isFinished() {
		return Math.abs(RobotMap.driveGyro.getAngle()) < 0.5;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}