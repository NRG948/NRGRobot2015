package org.usfirst.frc.team948.robot.commands;

import edu.wpi.first.wpilibj.command.Command;


public class EjectTote extends CommandBase{
	Command syncCommand = null;

	public EjectTote() {
		this(null);
	}

	public EjectTote(Command syncCommand){
		requires(acquirer);
		this.syncCommand = syncCommand;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		acquirer.slowPlace();
		acquirer.push(true);
	}

	@Override
	protected boolean isFinished() {
		if(syncCommand != null) {
			boolean cmdIsRunning = syncCommand.isRunning();
			System.out.println("EjectTote: syncCommand running: " + cmdIsRunning);
			return cmdIsRunning;
		}
		return false;
	}

	@Override
	protected void end() {
		acquirer.stop();
		acquirer.push(false);
	}

	@Override
	protected void interrupted() {
		end();
	}
}