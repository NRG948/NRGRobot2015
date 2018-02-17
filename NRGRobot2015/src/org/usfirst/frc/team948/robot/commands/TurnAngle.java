package org.usfirst.frc.team948.robot.commands;


/**
*
* Turns a desired angle
* 
*/
public class TurnAngle extends CommandBase {

	private static final double DEFAULT_TOLERANCE = 3.0;
	private double finalHeading;
	private double angle;
	private double power;
	private double toleranceInDegrees;
	 
	public TurnAngle(double angle, double power, double toleranceInDegrees) {
		requires(drive);
		this.power = power;
		this.angle = angle;
		this.toleranceInDegrees = toleranceInDegrees;
	}

	public TurnAngle(double angle, double power) {
		this(angle, power, DEFAULT_TOLERANCE);
	}

	@Override
	protected void initialize() {
		// sets initial heading to drive subsystem's current desired heading  
		double initialHeading = drive.turnToHeadingInit(toleranceInDegrees, power);
		finalHeading = initialHeading + angle;
		System.out.println("Turn started! Heading : " + finalHeading);
	}
	
	@Override
	protected void execute() {
		drive.turnToHeading(finalHeading, power);
	}
	
	@Override
	protected boolean isFinished() {
		return angle == 0 || drive.isTurnToHeadingDone();
	}
	
	@Override 
	protected void end() {
		drive.rawStop();
		System.out.println("Turn Finished");
		drive.turnToHeadingEnd(finalHeading);  
	}

	@Override
	protected void interrupted() {
		end();
	}
}