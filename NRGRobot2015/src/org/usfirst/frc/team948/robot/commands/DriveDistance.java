package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.RobotMap.Direction;
import org.usfirst.frc.team948.robot.utilities.MathHelper;

//Create a command called DriveDistance. 
//The command should drive a single side of the robot for a specified distance. 
//The constructor should take in a boolean (left or right side) and distance. 
//This will be used in the Shuffle command instead of DriveTimed.
public class DriveDistance extends CommandBase {

	public Direction direction;
	public double distance;
	public double power;
	
	private double slowingDownDistance;
	private double distanceTraveled;
	
	public double initLeftDistance, initRightDistance;

	public DriveDistance(Direction direction, double distance, double power) {
		requires(drive);
		this.direction = direction;
		this.distance = distance;
		this.power = power;
		slowingDownDistance = 3 * distance / 4;
	}

	@Override
	protected void end() {
		drive.rawStop();
	}
	protected void interrupted() {
		end();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double actualPower = MathHelper.clamp(this.power * (distance - distanceTraveled) / slowingDownDistance, -Math.abs(power), Math.abs(power));
		if (direction == Direction.Left) {
			drive.rawTankDrive(actualPower, 0);
		} else {
			drive.rawTankDrive(0, actualPower);
		}
		

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		initLeftDistance = RobotMap.leftEncoder.getDistance();
		initRightDistance = RobotMap.rightEncoder.getDistance();

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if (direction == Direction.Left) {
			distanceTraveled = RobotMap.leftEncoder.getDistance() - initLeftDistance;
		} else {
			distanceTraveled = RobotMap.rightEncoder.getDistance() - initRightDistance;
		}
		return distanceTraveled >= distance;
		
	}

}
