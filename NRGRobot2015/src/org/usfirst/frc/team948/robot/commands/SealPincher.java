package org.usfirst.frc.team948.robot.commands;


public class SealPincher extends CommandBase {

	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		pincher.sealPincher();
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