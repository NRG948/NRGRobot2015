package org.usfirst.frc.team948.robot.subsystems;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.commands.ManualAcquireBase;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Acquirer extends Subsystem {

	private final SpeedController acquirerRight = RobotMap.talonAcquirerRight;
	private final SpeedController acquirerLeft = RobotMap.talonAcquirerLeft;
	private static final double FAST_ACQUIRE_POWER = -0.6;
	private static final double SLOW_ACQUIRE_POWER = -0.2;
	private static final double SLOW_PLACE_POWER = 0.3;

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualAcquireBase(true));
	}

	/**
	 * Runs the left side of the Acquirer independently.
	 * 
	 * @param power
	 *            is speed to run the motor controllers. Positive values will
	 *            expel objects and Negative will pull objects in.
	 */
	public void runLeft(double power) {
		// TODO Find out if left or right motor is running in reverse power
		acquirerLeft.set(power);
	}

	/**
	 * // * Runs the right side of the Acquirer independently. // * // * @param
	 * power // * is speed to run the motor controllers. Positive values will //
	 * * expel objects and Negative will pull objects in. //
	 */
	public void runRight(double power) {
		acquirerRight.set(-power);
	}

	// /**
	// * If needed, runs the acquirer at a "high" speed (can be configured up
	// * above)
	// */
	public void fastAcquire() {
		runLeft(-FAST_ACQUIRE_POWER);
		runRight(-FAST_ACQUIRE_POWER);
	}

	// /**
	// * If needed, runs the acquirer at a "low" speed (can be configured up
	// * above)
	// */
	public void slowAcquire() {
		runLeft(-SLOW_ACQUIRE_POWER);
		runRight(-SLOW_ACQUIRE_POWER);
	}

	public void deflect(double power) {
		runLeft(power);
		runRight(-power);
	}
	
	// /**
	// * Places totes OR garbage containers at a stable, low speed (can be
	// * configured up above)
	// */
	public void slowPlace() {
		runLeft(-SLOW_PLACE_POWER);
		runRight(-SLOW_PLACE_POWER);
	}

	public void stop() {
		acquirerRight.disable();
		acquirerLeft.disable();
	}

	public boolean isBinDetected() {
		//note: digital inputs read inverted i.e. the sensor returns false when activated
		return !RobotMap.binSensor.get();
	}
	
	public boolean isFirstToteDetected() {
		return !RobotMap.firstToteSensor.get();
	}
	
	public boolean isNextToteDetected() {
		return !RobotMap.nextToteSensor.get();
	}
	
	public void push(boolean push) {
		RobotMap.pushPiston
				.set(push ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
	}
}