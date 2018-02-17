/**
 * 
 */
package org.usfirst.frc.team948.robot.commands;


/**
 * @author Sai
 * @author Chandra
 * 
 * Command sets x and y co-ordinates of the robot position on the field. 
 */
public class SetPosition extends CommandBase {

	private final double x;
	private final double y;
	
	/**
	 * Constructs a command which sets the initial robot's position on the field.
	 */
	public SetPosition(double xBegin, double yBegin) {
		requires(drive);
		this.x = xBegin;
		this.y = yBegin;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		positionTracker.setPosition(x, y);
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
