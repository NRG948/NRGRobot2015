package org.usfirst.frc.team948.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * 
 * Drive straight for a desired time;
 * 
 */

public class DriveStraightTimed extends CommandBase {
	Timer timer;
	private double power;
	private double seconds;
	private double heading;
	private boolean headingProvided;

	// Resets and starts the timer
	private void startTimer() {
		timer = new Timer();
		timer.reset();
		timer.start();
	}

	// Takes in two doubles for desired motor power (-1 to 1) and run time
	// (seconds)
	public DriveStraightTimed(double power, double seconds) {
		requires(drive);
		this.power = power;
		this.seconds = seconds;
	}
	
	public DriveStraightTimed(double power, double seconds, double heading) {
		requires(drive);
		this.power = power;
		this.seconds = seconds;
		this.heading = heading;
		this.headingProvided = true;
	}
	
	protected void initialize() {
		startTimer();
		if (headingProvided == true) {
			drive.driveOnHeadingInit(power);
		}
		else {
			heading = drive.driveOnHeadingInit(power);
		}
	}

	protected void execute() {
		drive.driveOnHeading(heading, power);
	}

	// Checks if the target time has been exceeded and returns
	protected boolean isFinished() {
		return (timer.get() >= seconds);
	}

	protected void end() {
		drive.driveOnHeadingEnd();
		drive.rawStop();
	}

	protected void interrupted() {
		end();
	}

}
