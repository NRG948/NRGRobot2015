package org.usfirst.frc.team948.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
*
* Drive for a desired time using Raw Motor left/right values.  
*
*/                                                            
public class DriveTimed extends CommandBase {
	Timer timer;
	private double milliseconds;
	private double leftPower;
	private double rightPower;

	// Resets and starts the timer
	private void startTimer() {
		timer = new Timer();
		timer.reset();
		timer.start();
	}

	// Takes in two doubles for desired motor power (-1 to 1) and run time
	// (seconds)
	public DriveTimed(double leftPower, double rightPower, double seconds) {
		requires(drive);
		this.leftPower = leftPower;
		this.rightPower= rightPower;
		this.milliseconds = seconds;
	}    
	
	protected void initialize() {
	startTimer();
	} 
	
	protected void execute() {
		drive.rawTankDrive(leftPower, rightPower);
	}

	//Checks if the target time has been exceeded and returns
	protected boolean isFinished() {
		return (timer.get() >= milliseconds);
	}
	
	protected void end() {
		drive.rawStop();
	}
	protected void interrupted() {
		end();
	}

}
