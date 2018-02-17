package org.usfirst.frc.team948.robot.commands;


public class ManualLift extends CommandBase {

	public ManualLift() {
		requires(scissorLift);
	}
	
	@Override
	protected void initialize() {	
	}
	
	@Override
	protected void execute() {
		if (CommandBase.ds.getXboxLT() > 0) {
			scissorLift.rawLift(-CommandBase.ds.getXboxLT());
		}
		else if (CommandBase.ds.getXboxRT() > 0) {
			scissorLift.rawLift(CommandBase.ds.getXboxRT());
		}else{
			scissorLift.rawLift(0);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		scissorLift.rawStop();
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
