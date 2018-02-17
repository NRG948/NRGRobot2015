package org.usfirst.frc.team948.robot.commands;

/**
* Drive straight on a heading under human control (right joystick controls speed)
* until the joystick trigger is released or the command gets interrupted.
*/
public class ManualDriveStraight extends CommandBase {
	private double heading;
	
	public ManualDriveStraight() {
		requires(drive);
	}
	
	protected void initialize() {
		heading = drive.driveOnHeadingInit(1.0); // Try to maintain the current gyro heading
	}
	
	// Drives on a fixed heading using the right joystick y-value for power
	protected void execute() {
		drive.driveOnHeading(heading, ds.getRightJSY());
	}
	
	// Command runs forever, until it is interrupted or the battery goes dead
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		drive.driveOnHeadingEnd();
		drive.rawStop(); 
		drive.setDesiredHeading(heading);
	}
	
	protected void interrupted() {
		end();
	}
}
