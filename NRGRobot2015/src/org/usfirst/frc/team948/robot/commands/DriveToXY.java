package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.utilities.MathHelper;
import org.usfirst.frc.team948.robot.utilities.PositionTracker;

public class DriveToXY extends CommandBase {

	private static final double ON_TARGET_TOLERANCE_FT = 0.5;
	private static final double DISTANCE_SLOWDOWN_FT = 2.0;
	private static final double MIN_POWER_TO_MOVE = 0.1;
	private static final double DRIVEXY_OVERSHOOT_TOLERANCE = 0.1 / 12.0;

	// we will get current position from position tracker
	private PositionTracker positionTracker = CommandBase.positionTracker;

	private final double driveToX;
	private final double driveToY;
	private final double maxPower;
	private double previousDistanceToGo;
	private double distanceToGo;

	public DriveToXY(double x, double y, double p) {
		driveToX = x;
		driveToY = y;
		maxPower = p;
		requires(drive);
	}

	@Override
	protected void initialize() {
		drive.driveOnHeadingInit(maxPower);
		previousDistanceToGo = Double.MAX_VALUE;
	}

	@Override
	protected void execute() {
		// assigning power to a local variable
		double power = this.maxPower;
		// finding change on x
		double dX = driveToX - positionTracker.getX();
		// finding change on y
		double dY = driveToY - positionTracker.getY();
		// how much distance it will go
		distanceToGo = Math.sqrt(dX * dX + dY * dY);
		// declare heading
		double desiredHeading;
		// computing the intermediate drive angle
		double theta = Math.toDegrees(Math.atan2(dY, dX));
		// get current heading from gyro
		double currentHeading = RobotMap.driveGyro.getAngle();
		// decreases power when nearing destination
		if (distanceToGo <= DISTANCE_SLOWDOWN_FT) {
			power = MathHelper.max(power * distanceToGo / DISTANCE_SLOWDOWN_FT, MIN_POWER_TO_MOVE);
		}
		// setting heading based on drive direction
		if (power >= 0) {
			desiredHeading = 90.0 - theta; // driving Forward
		} else {
			desiredHeading = -90.0 - theta; // driving Backward
		}
		System.out.println("dX: " + dX + " dY: " + dY + " desired Heading: " + desiredHeading + " distanceToGO: " + distanceToGo);
		// Convert a heading into an equivalent heading which is within 180
		// degrees of the gyroHeading.
		double nearestEquivalentHeading =
			MathHelper.nearestEquivalentHeading(desiredHeading,	currentHeading);

		// assign drive action
		drive.driveOnHeading(nearestEquivalentHeading, power);
	}

	@Override
	protected boolean isFinished() {
		boolean finished = (distanceToGo <= ON_TARGET_TOLERANCE_FT);
		previousDistanceToGo = distanceToGo;
		return finished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		drive.driveOnHeadingEnd();
		drive.rawStop();
		// We end up at a not-exactly-predictable heading, so just update the
		// DesiredHeading to whatever direction we finish at.
		drive.setDesiredHeading(RobotMap.driveGyro.getAngle());
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
