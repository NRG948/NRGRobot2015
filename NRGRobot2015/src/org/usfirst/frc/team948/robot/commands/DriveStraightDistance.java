package org.usfirst.frc.team948.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team948.robot.EncoderPIDSource;
import org.usfirst.frc.team948.robot.utilities.MathHelper;
import org.usfirst.frc.team948.robot.utilities.PreferenceKeys;

/**
 * 
 * Drive straight for a desired distance;
 * 
 */

public class DriveStraightDistance extends CommandBase implements PIDOutput{
	
	private double power;
	private double heading;
	private double distance;
	private double tolerance = 2.0/12.0;
	private boolean hasHeading;
	
	//private double p = 0.02, i = 0.015, d = 0.02,
	private double p, i, d;
	double distancePidOutput;
	public EncoderPIDSource encoderPIDSource= new EncoderPIDSource();
	private PIDController distancePID = new PIDController(p,i,d,encoderPIDSource , this);
	private int cyclesOnTarget;

	public DriveStraightDistance(double power, double distance)
	{
		requires(drive);
		this.power = power;
		this.distance = distance;
		hasHeading = false;
	}

	public DriveStraightDistance(double heading, double power, double distance)
	{
		this(heading, power, distance, 0.25);
	}

	public DriveStraightDistance(double heading, double power, double distance, double tolerance)
	{
		requires(drive);
		this.heading = heading;
		this.power = power;
		this.distance = distance;
		this.tolerance = tolerance;
		hasHeading = true;
	}
	
	@Override
	protected void initialize() {
		System.out.println("Drive Started!");
		drive.driveOnHeadingInit(power);
		if (!hasHeading)
		{
			heading = drive.getDesiredHeading();
		}
		p = preferences.getDouble(PreferenceKeys.Distance_P, 0.5);
		i = preferences.getDouble(PreferenceKeys.Distance_I, 0.02);
		d = preferences.getDouble(PreferenceKeys.Distance_D, 1.5);
		System.out.println("DriveDistance P:" + p + " I:" + i + " D:" + d);
		encoderPIDSource.reset();
		distancePidOutput = 0.0;
		distancePID.reset();
		distancePID.setOutputRange(-Math.abs(power), Math.abs(power));
		distancePID.setAbsoluteTolerance(tolerance);
		distancePID.setSetpoint(distance);
		distancePID.setPID(p,i,d);
		distancePID.enable();
		cyclesOnTarget = 0;
	}
	
	@Override
	protected void execute() {
		//SmartDashboard.putNumber("Distance PIDO", value);
		SmartDashboard.putNumber("Distance PID OUTPUT", distancePidOutput);
		SmartDashboard.putNumber("Distance PID ERROR", distancePID.getError());
		double factor = MathHelper.clamp(distancePidOutput, -1, 1);
		drive.driveOnHeading(heading, power*factor);
	}

	@Override
	protected boolean isFinished() {
		if(distancePID.onTarget()){
			cyclesOnTarget ++;
		}else{
			cyclesOnTarget = 0;
		}
		return(cyclesOnTarget >= 3) || (distancePID.getError() < 0);
	}
	
	@Override
	protected void end() {
		System.out.println("Drive Finished! PID Error: " + distancePID.getError());
		distancePID.reset();
		distancePidOutput = 0.0;
		drive.driveOnHeadingEnd();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {
		distancePidOutput = output;
	}
}