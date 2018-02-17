package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;



public class ManualDrive extends CommandBase {
	
	public ManualDrive() {
		requires(drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		drive.rawTankDrive(CommandBase.ds.getLeftJSY()*0.9,
		    CommandBase.ds.getRightJSY()*0.9);  
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
