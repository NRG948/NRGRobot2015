package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.utilities.MathHelper;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToXY extends CommandBase {

	private static final double DEFAULT_TOLERANCE = 10;
	private double power;
	private double finalX;
	private double finalY;
	private double desiredHeading;
	private double toleranceInDegrees;

	public TurnToXY(double power, double finalX, double finalY, double toleranceInDegrees){
		this.power = power;
		this.finalX = finalX;
		this.finalY = finalY;
		this.toleranceInDegrees = toleranceInDegrees;
		requires(drive);  
	}
	
	public TurnToXY(double power, double finalX, double finalY){
		this(power, finalX, finalY, DEFAULT_TOLERANCE);
	}
	
	@Override
	protected void initialize() {
		this.desiredHeading = 90 -MathHelper.angleTowardsXY(positionTracker.getX(), positionTracker.getY(), finalX, finalY);
		drive.turnToHeadingInit(toleranceInDegrees, power);
	}

	@Override
	protected void execute() {
		drive.turnToHeading(MathHelper.nearestEquivalentHeading(desiredHeading, RobotMap.driveGyro.getAngle()), power);
	}

	@Override
	protected boolean isFinished() {
		return drive.isTurnToHeadingDone();
	}

	@Override
	protected void end() {
		drive.turnToHeadingEnd(desiredHeading);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
