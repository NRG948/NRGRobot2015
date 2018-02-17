package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

public class IncrementalQuickLift extends CommandBase{

	private boolean up;
	ScissorLift.Level level;
	private double tolerance = 0;
	public IncrementalQuickLift(boolean up, double tolerance){
		requires(scissorLift);
		this.up = up;
		this.tolerance = tolerance;
	}
	
	@Override
	protected void initialize() {
		double currentVoltage = RobotMap.scissorLiftPotentiometer.get();
		level = scissorLift.findNearestLevel(currentVoltage);
		level = up ? scissorLift.nextHigher(level) : scissorLift.nextLower(level);
	}

	@Override
	protected void execute() {
		if(up)
			pincher.setBoth(Position.Open);
		scissorLift.rawLift(up? 1:-1);
	}

	@Override
	protected boolean isFinished() {
		return up? RobotMap.scissorLiftPotentiometer.get() > level.voltage-tolerance : 
			RobotMap.scissorLiftPotentiometer.get() < level.voltage+tolerance;
	}

	@Override
	protected void end() {
		scissorLift.rawStop();
	}

	@Override
	protected void interrupted() {
		this.end();
	}
}
