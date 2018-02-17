package org.usfirst.frc.team948.robot.commands;

public class ManualAcquireBin extends ManualAcquireBase {

	public ManualAcquireBin() {
		super(false);
	}
	
	@Override
	protected boolean isFinished() {
		return acquirer.isBinDetected();
	}	
}
