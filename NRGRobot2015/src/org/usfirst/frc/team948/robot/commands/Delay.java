package org.usfirst.frc.team948.robot.commands;

import edu.wpi.first.wpilibj.Timer;

public class Delay extends CommandBase {
	Timer timer;
	double timeInSeconds;

	private void startTimer() {
		timer = new Timer();
		timer.reset();
		timer.start();
	}

	public Delay(double time) {
		// TODO Auto-generated constructor stub
		timeInSeconds = time;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		startTimer();
		System.out.println("Starting delay");	
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		return (timer.get() >= timeInSeconds);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
