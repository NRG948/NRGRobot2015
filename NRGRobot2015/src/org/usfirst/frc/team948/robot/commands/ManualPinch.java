package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.DS2015;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;

public class ManualPinch extends CommandBase {

	public static final double TILT_MAGNITUDE = 0.9;

	public ManualPinch() {
		requires(pincher);
	}

	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		if (!DS2015.xboxAButton.get() && !DS2015.xboxBButton.get() && !DS2015.xboxYButton.get()) {
			if (ds.getXboxRX() <= -TILT_MAGNITUDE) {
				pincher.setRight(Position.Close);
			} else if (ds.getXboxRX() >= TILT_MAGNITUDE) {
				pincher.setRight(Position.Open);
			}
			if (ds.getXboxLX() <= -TILT_MAGNITUDE) {
				pincher.setLeft(Position.Open);
			} else if (ds.getXboxLX() >= TILT_MAGNITUDE) {
				pincher.setLeft(Position.Close);
			}
			if(ds.getXboxRT() > 0.1){
				pincher.setBoth(Position.Open);
			}
		}
		else {
			if (ds.getXboxLX() >= TILT_MAGNITUDE) {
				pincher.setBoth(Position.Close);
			} else if (ds.getXboxLX() <= -TILT_MAGNITUDE) {
				pincher.setBoth(Position.Open);
			}
		}
		
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
