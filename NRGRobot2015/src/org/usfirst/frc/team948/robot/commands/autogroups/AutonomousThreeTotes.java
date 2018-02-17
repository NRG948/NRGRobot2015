package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.RoboConstants;
import org.usfirst.frc.team948.robot.Robot;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.EjectTote;
import org.usfirst.frc.team948.robot.commands.LiftToHeight;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.commands.ResetSensors;
import org.usfirst.frc.team948.robot.commands.SetPosition;
import org.usfirst.frc.team948.robot.commands.TurnAngleQuick;
import org.usfirst.frc.team948.robot.subsystems.Pincher;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousThreeTotes extends CommandGroup implements RoboConstants {
	final Double deflectSpeed = 1.0;
	final Double deflectTime = 1.0;
	Double driveSpeed = 0.7; // REVIEW: was 0.9 but jammed RC; then had 0.8
	Double driveDistance = 5.0; // 4.85
	public AutonomousThreeTotes(int totes, boolean[] bins, double angleToTurn, boolean deflectDirection){
		addParallel(new SetPosition(3.42, 5.58)); //TODO verify x and y coordinates for the starting position of the robot
		addSequential (new ResetSensors());
		addSequential(new PinchAndAcquire());
		
		for(int tote = 1; tote < totes; tote ++){
			if(tote == 1) {
				DelayAndDrive driveCommand = new DelayAndDrive(0, driveSpeed, driveDistance, HEIGHT_TO_CLEAR_ACQUIRER);
				addParallel(new DeflectAndLift(deflectSpeed, deflectTime, tote, bins[tote-1], deflectDirection, driveCommand,0.3));
				addSequential(driveCommand);	
			}else if(tote == 2) {
				driveSpeed = 0.75; // 0.7;
				driveDistance = 6.8; // 6.85 - decreased in order to avoid knocking over third bin;
				DelayAndDrive driveCommand = new DelayAndDrive(0, driveSpeed, driveDistance, HEIGHT_TO_CLEAR_ACQUIRER);
				addParallel(new DeflectAndLift(deflectSpeed, deflectTime, tote, bins[tote-1], deflectDirection, driveCommand,0.9));
				addSequential(driveCommand);
			}
			addSequential(new PinchAndAcquire());
		}
		
		addParallel(new Pinch(Pincher.Position.Close));
		//Turn to face the auto zone
		addParallel(new LiftToHeight(ScissorLift.Level.Step));
		addSequential(new TurnAngleQuick(-angleToTurn, 0.7, 20.0));
		addParallel(new Pinch(Pincher.Position.Close));
		//Drive into the auto zone
		//addSequential(new DriveToX(-1, DISTANCE_TO_AUTO));
		//addSequential(new DriveToXY(12, 17, -1));
		addSequential(new DriveStraightDistance(-0.95, 7.9 * Math.sin(80 * Math.PI/180)/Math.sin(angleToTurn*Math.PI/180)));
		//addSequential(new DriveStraightDistance(-1, 7.4* Math.sin(Math.toRadians(80))/Math.sin(Math.toRadians(angleToTurn))));
		addParallel(new Pinch(Position.Open));
		//Place stack of totes down
		Command backAwayDriveCommand = new DriveStraightDistance(-1, BACK_AWAY_DISTANCE){
			@Override
			protected void initialize(){
				super.initialize();
				Robot.isAutoThreeToteFinished = true;
			}
		};
		addParallel(new EjectTote(backAwayDriveCommand));
		// Back away from stack
		addSequential(backAwayDriveCommand);
		
	}
	
	@Override 
	public void end()
	{
		super.end();
		Robot.isAutoThreeToteFinished = true;
	}
}
