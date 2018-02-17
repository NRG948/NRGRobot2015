package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.Pincher;

import edu.wpi.first.wpilibj.Timer;

// Create a command that runs the acquirer. Have the constructor pass in a boolean that determines whether the acquiring will be fast or slow.
public class AcquireFastOrSlow extends CommandBase {
	private boolean fast,  opened = false;
	//Timer timer;
	double counter = 0;
	public AcquireFastOrSlow(boolean fast) {
		requires(acquirer);
		this.fast = fast;
		//timer = new Timer();
	}

	protected void initialize() {
	//	timer.reset();
	//	timer.start();
		System.out.println("Acquire Started");
	}

	protected void execute() {
		if (fast) {
			acquirer.fastAcquire();
		} else {
			acquirer.slowAcquire();
		}
		/*if(opened){
			pincher.setBoth(Pincher.Position.Close);
			opened = false;
		}
		if(timer.get() - counter > 2){
			counter += 2;
			pincher.setBoth(Pincher.Position.Open);
			opened = true;
		}*/
	}

	protected boolean isFinished() {
		//tests if tote is fully sucked into aquirer
		return acquirer.isNextToteDetected();
	}

	protected void end() {
		System.out.println("Acquire Ended");
		acquirer.stop();
	}

	protected void interrupted() {
		end();
	}

}
