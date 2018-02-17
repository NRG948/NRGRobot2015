package org.usfirst.frc.team948.robot.commands;

public class ManualAcquireBase extends CommandBase {

	private boolean dualJoystickControl;
	private double powerScaleFactor = 1.0;

	public ManualAcquireBase(boolean dualJoystickControl) {
		requires(acquirer);
		this.dualJoystickControl = dualJoystickControl;
	}

	public ManualAcquireBase(boolean dualJoystickControl, double powerScaleFactor) {
		requires(acquirer);
		this.dualJoystickControl = dualJoystickControl;
		this.powerScaleFactor = powerScaleFactor;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		if (dualJoystickControl) {
			acquirer.runLeft(ds.getXboxLY() * this.powerScaleFactor);
			acquirer.runRight(ds.getXboxRY() * this.powerScaleFactor);
		} else {
			double power = ds.getXboxLY() * this.powerScaleFactor;
			acquirer.runLeft(power);
			acquirer.runRight(power);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		acquirer.stop();
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
