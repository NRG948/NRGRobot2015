package org.usfirst.frc.team948.robot.commands;


public class PressurizePincher extends CommandBase {

	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		pincher.pressurizePincher();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
	}
	
	@Override
	protected void interrupted() {
	}
}
