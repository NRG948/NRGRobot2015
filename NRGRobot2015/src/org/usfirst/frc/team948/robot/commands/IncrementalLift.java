package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

public class IncrementalLift extends CommandBase{

	private boolean up;
	
	public IncrementalLift(boolean up){
		requires(scissorLift);
		this.up = up;
	}
	
	@Override
	protected void initialize() {
		double currentVoltage = RobotMap.scissorLiftPotentiometer.get();
		ScissorLift.Level level = scissorLift.findNearestLevel(currentVoltage);
		level = up ? scissorLift.nextHigher(level) : scissorLift.nextLower(level);
		scissorLift.liftToHeightInit(level.voltage);
	}

	@Override
	protected void execute() {
		if(up)
			pincher.setBoth(Position.Open);
		scissorLift.liftToHeight();
	}

	@Override
	protected boolean isFinished() {
		return scissorLift.isLiftToHeightDone();
	}

	@Override
	protected void end() {
		scissorLift.liftToHeightEnd();
	}

	@Override
	protected void interrupted() {
		this.end();
	}
}
