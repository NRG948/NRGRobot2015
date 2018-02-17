package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;

public class Pinch extends CommandBase {
	private Position position;

	public Pinch(Position position) {
		requires(pincher);
		this.position = position;
	}

	@Override
	protected void initialize() {
		System.out.println("Pinch Started");
	}

	@Override
	protected void execute() {
		pincher.setBoth(position);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
		System.out.println("Pinch Ended! Currrent Position is: " +position.toString());
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
