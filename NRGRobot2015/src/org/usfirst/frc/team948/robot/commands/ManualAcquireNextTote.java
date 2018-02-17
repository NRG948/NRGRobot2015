package org.usfirst.frc.team948.robot.commands;

public class ManualAcquireNextTote extends ManualAcquireBase {

	public ManualAcquireNextTote() {
		super(true);
	}
	
	@Override
	protected boolean isFinished() {
		return acquirer.isNextToteDetected();
	}	

}
