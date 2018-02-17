package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.EncoderPIDSource;
import org.usfirst.frc.team948.robot.RobotMap.Direction;
import org.usfirst.frc.team948.robot.commands.autogroups.DelayAndDrive;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DeflectBin extends CommandBase {

	private Direction direction;
	private double power;
	private Command synchronizedCommand = null;
	private DelayAndDrive driveCommand =  null;
	private DriveStraightDistance driveCommand2 = null;
	private boolean delayDistanceBased = false;
	private boolean distanceBased = false;
	private double time;
	private boolean timeBased  = false;
	private double distance;
	private double initDistance;
	EncoderPIDSource encoderPIDSource;
	Timer timer;
	
	//the fraction of the time or distance for the arm to remain closed
	private double FRACTION_CLOSED = 0.6;
	
	public DeflectBin(Direction direction, double power) {
		this(direction,power,null);
	}
	public DeflectBin(Direction direction, double power, Command synchronizedCommand) {
		requires(acquirer);
		requires(pincher);
		this.direction = direction;
		this.power = power;
		this.synchronizedCommand = synchronizedCommand;
	}
	public DeflectBin(Direction direction, double power, double seconds){
		requires(acquirer);
		requires(pincher);
		this.direction = direction;
		this.power = power;
		timeBased = true;
		time = seconds;
	}
	public DeflectBin(Direction direction, double power, DelayAndDrive distanceCommand, double distance, double fractionClosed){
		requires(acquirer);
		requires(pincher);
		this.direction = direction;
		this.power = power;
		delayDistanceBased = true;
		this.driveCommand = distanceCommand;
		this.distance = distance;	
		FRACTION_CLOSED = fractionClosed;
	}
	@Override
	protected void initialize() {
		pincher.setBoth(Position.Close);
		SmartDashboard.putString("Autonomous Failure", "Deflect Bin Has Started");
		System.out.println("Deflect Bin Started");
		if(timeBased){
			timer = new Timer();
			timer.reset();
			timer.start();
		}
		if(delayDistanceBased) encoderPIDSource = driveCommand.encoderPIDSource;
		if(distanceBased) encoderPIDSource = driveCommand2.encoderPIDSource;
		if(delayDistanceBased || distanceBased)initDistance = encoderPIDSource.getPidGet();
	}

	@Override
	protected void execute() {
		boolean openArm = (delayDistanceBased && (encoderPIDSource.getPidGet()- initDistance) > distance*FRACTION_CLOSED)
				|| (timeBased && timer.get() > time*FRACTION_CLOSED);
		
		if (direction == Direction.Left) {
			acquirer.deflect(-power);
			if (openArm) pincher.setLeft(Position.Open);
			
		} else if (direction == Direction.Right) {
			acquirer.deflect(power);			
			if (openArm) pincher.setRight(Position.Open);
		}
	}
	
	@Override
	protected void end() {
		acquirer.stop();
		System.out.println("Deflect Bin Ended");
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		if (timeBased) {
			return timer.get() > time;
		}else if(delayDistanceBased || distanceBased){
			return (encoderPIDSource.getPidGet() - initDistance) > distance;
		}
		return synchronizedCommand != null && !(synchronizedCommand.isRunning());
	}

} 