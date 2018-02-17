package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.subsystems.ScissorLift.Level;

public class LiftToHeight extends CommandBase {
	
	private double voltage;

	public LiftToHeight(double voltage){
		requires(scissorLift);
		this.voltage = voltage;
	}
	
	public LiftToHeight(Level level){
		this(level.voltage);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Lift to height Started");
		scissorLift.liftToHeightInit(voltage);
	}
	
	@Override
	protected void execute() {
		scissorLift.liftToHeight();
	}

	@Override
	protected boolean isFinished() {
		return scissorLift.isLiftToHeightDone();
	}
	
	@Override
	protected void end() {
		System.out.println("Lift to height ended");
		scissorLift.liftToHeightEnd();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
