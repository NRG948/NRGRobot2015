package org.usfirst.frc.team948.robot;

import edu.wpi.first.wpilibj.PIDSource;

public class EncoderPIDSource implements PIDSource {
	double startingLeftDistance, startingRightDistance;
	private volatile double pidGet;
	public void reset() {
		startingLeftDistance = RobotMap.leftEncoder.getDistance();
		startingRightDistance = RobotMap.rightEncoder.getDistance();
	}
	
	@Override
	public double pidGet() {
		pidGet =(Math.max(Math.abs(RobotMap.leftEncoder.getDistance() - startingLeftDistance), 
				Math.abs(RobotMap.rightEncoder.getDistance() - startingRightDistance)));
		return pidGet;
	}
	public double getPidGet(){
		return pidGet;
	}
}
