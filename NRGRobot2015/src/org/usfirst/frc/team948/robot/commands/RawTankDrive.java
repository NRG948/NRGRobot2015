package org.usfirst.frc.team948.robot.commands;

public class RawTankDrive extends CommandBase {

	private double leftPower;
	private double rightPower;

	public RawTankDrive(double leftPower, double rightPower) {
		requires(drive);
		this.leftPower = leftPower;
		this.rightPower = rightPower;
	}
	
	@Override
	protected void initialize() {
		drive.rawTankDrive(leftPower, rightPower);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drive.rawStop();
		drive.setDesiredHeadingFromGyro();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
