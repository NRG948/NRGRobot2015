package org.usfirst.frc.team948.robot.subsystems;

import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.commands.CommandBase;
import org.usfirst.frc.team948.robot.commands.ManualLift;
import org.usfirst.frc.team948.robot.utilities.PreferenceKeys;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ScissorLift extends Subsystem implements PIDOutput {

	// ADD MORE LEVELS LATER
	public enum LifterMode {
		Bin, FirstTote, NextTote
	}
	
	public enum Level {

		Ground(CommandBase.preferences.getDouble(PreferenceKeys.Ground_Voltage, GROUND)), 
		TwoInch(CommandBase.preferences.getDouble(PreferenceKeys.Two_Inch_Voltage, TWO_INCH)), 
		Step(CommandBase.preferences.getDouble(PreferenceKeys.Step_Voltage, STEP)), 
		OneTote(CommandBase.preferences.getDouble(PreferenceKeys.One_Tote_Voltage, ONE_TOTE)), 
		OneTotePlusStep(CommandBase.preferences.getDouble(PreferenceKeys.One_Tote_Plus_Step_Voltage, ONE_TOTE_PLUS_STEP));

		public final double voltage;

		Level(double voltage) {
			this.voltage = voltage;
		}
	}

	private final PIDController scissorPID = new PIDController(0.01,
			0.0 * 2 * 0.05, 0.0, RobotMap.scissorLiftPotentiometer, this);
	public double pidOutput;
	private final double PID_MAX_OUTPUT = 1.0; // TODO: Make changes to 1.0
	private final double LIFT_TO_HEIGHT_P = 20.0;
	private final double LIFT_TO_HEIGHT_I = 1.5;
	private final double LIFT_TO_HEIGHT_D = 20.0;
	private final int REQUIRED_CYCLES_TO_HEIGHT = 2;
	public final double TOLERANCE = 0.01;
	private LifterMode lifterMode;
	private int cyclesOnTarget;

	/*
	 * Main Bot Voltages:
	 * Ground: 0.097
	 * Two Inch: 0.25
	 * Step : 0.41
	 * Tote: .65
	 * Tote Plus Step: .91
	 */
	private final static double GROUND = Robot.contestRobot? 0.082 : 0.071;
	private final static double TWO_INCH = Robot.contestRobot? 0.24 : 0.226;
	private final static double STEP = Robot.contestRobot? 0.435 : 0.394;
	private final static double ONE_TOTE = Robot.contestRobot? 0.655 : 0.630;
	private final static double ONE_TOTE_PLUS_STEP = Robot.contestRobot? 0.875 : 0.831;

	/**
	 * Sends a raw power value to the ScissorLift motor.
	 * 
	 * @param power
	 *            how fast to run the motor (positive means raise up, negative means lower down).
	 */
	public void rawLift(double power) {
		
		if (power > 0 && hasReachedUpperLimit()) {
			rawStop();
		} else if (power < 0 && (hasReachedLowerLimit() )) {
			rawStop();
		} else {
			RobotMap.lifterDart.set(power);
		}
	}

	public void rawStop() {
		RobotMap.lifterDart.set(0);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ManualLift());
	}

	public void liftToHeightInit(double voltage) {
		double kP = CommandBase.preferences.getDouble(
				PreferenceKeys.Lift_To_Height_P, LIFT_TO_HEIGHT_P);
		double kI = CommandBase.preferences.getDouble(
				PreferenceKeys.Lift_To_Height_I, LIFT_TO_HEIGHT_I);
		double kD = CommandBase.preferences.getDouble(
				PreferenceKeys.Lift_To_Height_D, LIFT_TO_HEIGHT_D);
		scissorPID.reset();
		scissorPID.setPID(kP, kI, kD);
		scissorPID.setOutputRange(-PID_MAX_OUTPUT, PID_MAX_OUTPUT);
		scissorPID.setSetpoint(voltage);
		scissorPID.setAbsoluteTolerance(TOLERANCE);
		scissorPID.enable();
		pidOutput = 0;
		SmartDashboard.putNumber("LiftToHeightP", kP);
		SmartDashboard.putNumber("LiftToHeightI", kI);
		SmartDashboard.putNumber("LiftToHeightD", kD);
	}

	public void liftToHeight() {
		rawLift(pidOutput);
		
	}

	public boolean isLiftToHeightDone(){
		if (scissorPID.onTarget()) {
			cyclesOnTarget++;
		}
		else {
			cyclesOnTarget = 0;
		}
		return cyclesOnTarget >= REQUIRED_CYCLES_TO_HEIGHT;
	}
	
	public void liftToHeightEnd() {
		scissorPID.reset();
		pidOutput = 0;
		rawStop();
	}

	public PIDController getPIDController() {
		return scissorPID;
	}

	@Override
	public void pidWrite(double output) {
		pidOutput = output;
	}

	public Level findNearestLevel(double currentVoltage) {
		double nextDiff;
		double prevDiff = Double.MAX_VALUE;
		Level prevLevel = null;
		for (Level level : Level.values()) {
			nextDiff = Math.abs(currentVoltage - level.voltage);
			if (currentVoltage < level.voltage) {
				if (nextDiff < prevDiff) {
					return level;
				} else {
					return prevLevel;
				}
			}
			prevDiff = nextDiff;
			prevLevel = level;
		}
		return Level.OneTotePlusStep;
	}
	public double findCurrentLevel(double currentVoltage){
		double answer  = 0, nextDiff, prevDiff = Double.MAX_VALUE;
		int counter = 1;
		for(Level level: Level.values()){
			nextDiff = currentVoltage - level.voltage;
			if(Math.abs(nextDiff) < TOLERANCE * 1.05) return counter; 
			if(nextDiff < 0 && prevDiff > 0){
				answer = (2*counter-1)/2.0;
				return answer;
			}
			prevDiff = nextDiff;
			counter++;
		}
		return 0;
	}

	public Level nextHigher(Level currentLevel) {
		Level[] levels = Level.values();
		for (int i = 0; i < levels.length; i++) {
			if (currentLevel.equals(levels[i])) {
				return levels[Math.min(levels.length - 1, i + 1)];
			}
		}
		return null;
	}

	public Level nextLower(Level currentLevel) {
		Level[] levels = Level.values();
		for (int i = 0; i < levels.length; i++) {
			if (currentLevel.equals(levels[i])) {
				return levels[Math.max(0, i - 1)];
			}
		}
		return null;
	}
	
	public LifterMode getLifterMode() {
		return lifterMode;
	}
	
	public void setLifterMode(LifterMode mode) {
		lifterMode = mode;
	}
	
	public boolean hasReachedUpperLimit() {
		return !RobotMap.upperLifterMovement.get();
	}
	
	public boolean hasReachedLowerLimit() {
		return !RobotMap.lowerLifterMovement.get();
	}
}