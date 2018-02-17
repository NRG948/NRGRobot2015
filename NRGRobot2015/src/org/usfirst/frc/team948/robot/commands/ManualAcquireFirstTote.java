package org.usfirst.frc.team948.robot.commands;

public class ManualAcquireFirstTote extends ManualAcquireBase {

	public ManualAcquireFirstTote() {
		super(false);
	}

	@Override
	protected boolean isFinished() {
		return acquirer.isFirstToteDetected();
	}	
}
