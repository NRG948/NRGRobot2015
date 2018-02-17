package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift.Level;
/**
 *
 */
public class QuickLiftDown extends CommandBase {

    private Level level;
    private double tolerance;
	public QuickLiftDown(ScissorLift.Level level) {
    	requires(scissorLift);
    	this.level = level;
    	tolerance = 0.005;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	scissorLift.rawLift(-1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (RobotMap.scissorLiftPotentiometer.get() <= this.level.voltage+tolerance){
    		return true;	
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	scissorLift.rawLift(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
