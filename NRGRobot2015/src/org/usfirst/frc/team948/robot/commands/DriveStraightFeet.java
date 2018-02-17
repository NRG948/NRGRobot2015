package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.utilities.MathHelper;

public class DriveStraightFeet extends CommandBase{
	private double power;
	private double heading;
	private double feet;
	private double initialLeft;
	private double initialRight;
	private double maxTraveled;
	private static final double SLOWING_DOWN_DISTANCE = 1.0;
	
	/**
	 * The robot drives straight for a certain distance in feet at a certain power.
	 * @param feet desired distance in feet to be traveled
	 * @param power desired motor power (-1 to 1)
	 */
	public DriveStraightFeet(double feet, double power) {
		requires(drive);
		this.power = power;
		this.feet = feet;
	}    
	
	protected void initialize() {
		heading = drive.driveOnHeadingInit(power);
		initialLeft = RobotMap.leftEncoder.getDistance();
        initialRight = RobotMap.rightEncoder.getDistance();
	}
	
	protected void execute() {
		// We take the absolute values, since we just need the total distance traveled if the robot is going backwards
		double leftTraveled = Math.abs(RobotMap.leftEncoder.getDistance() - initialLeft);
		double rightTraveled = Math.abs(RobotMap.rightEncoder.getDistance() - initialRight);
		maxTraveled = Math.max(leftTraveled, rightTraveled);
		double feetRemaining = feet - maxTraveled;
		// This ramps the power down to zero when we reach the target distance
		double powerMod = MathHelper.clamp(power * feetRemaining / SLOWING_DOWN_DISTANCE, -Math.abs(power), Math.abs(power));
		drive.driveOnHeading(heading, powerMod);
	}

	// Finishes the command if the target distance has been exceeded
	protected boolean isFinished() {
		return (maxTraveled >= feet);
	}
	
	protected void end() {
		drive.driveOnHeadingEnd();
		drive.rawStop();
	}
	protected void interrupted() {
		end();
	}
}
