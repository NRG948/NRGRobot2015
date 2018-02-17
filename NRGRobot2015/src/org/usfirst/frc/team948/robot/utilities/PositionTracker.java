package org.usfirst.frc.team948.robot.utilities;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Tracks x and y cordinate of robot on field, there are 2 ways that it is being
 * calculated.
 * 
 * @author Eli
 *
 */
public class PositionTracker implements PIDSource{
	private final Gyro gyro;
	private final Encoder leftQuad;
	private final Encoder rightQuad;
	private double x;
	private double y;
	// x2 and y2 are computed using average heading instead of instantaneous
	// heading.
	// TODO: Do testing to see which method is more accurate.
	private double x2;
	private double y2;
	private double lastAngle;
	private boolean isInitialized = false;
	private double lastLeftQuadDistance;
	private double lastRightQuadDistance;
	private long lastTime;
	private double velocity;

	public PositionTracker(Gyro gyro, Encoder leftQuad, Encoder rightQuad) {
		this.gyro = gyro;
		this.leftQuad = leftQuad;
		this.rightQuad = rightQuad;

		// TODO Auto-generated constructor stub
	}

	public void update() {
		if (!isInitialized) {
			initialize();
		}
		double leftChange = leftQuad.getDistance() - lastLeftQuadDistance;
		double rightChange = rightQuad.getDistance() - lastRightQuadDistance;
		double averageChange = (leftChange + rightChange) / 2.0;
		double angle = Math.toRadians(MathHelper.headingToDegrees(gyro
				.getAngle()));
		long time = System.currentTimeMillis();
		x += averageChange * Math.cos(angle);
		y += averageChange * Math.sin(angle);
		double averageAngle = (lastAngle + angle) / 2.0;
		x2 += averageChange * Math.cos(averageAngle);
		y2 += averageChange * Math.sin(averageAngle);
		lastLeftQuadDistance += leftChange;
		lastRightQuadDistance += rightChange;
		velocity = averageChange * 1000.0 / (time - lastTime);
		lastTime = time;
		lastAngle = angle;
	}

	public void initialize() {
		x = 0;
		y = 0;
		x2 = 0;
		y2 = 0;
		lastAngle = 0;
		lastLeftQuadDistance = lastRightQuadDistance = 0;
		lastTime = System.currentTimeMillis();
		isInitialized = true;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
		this.x2 = x;
		this.y2 = y;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}

	@Override
	public double pidGet() {
		return x;
	}
}
