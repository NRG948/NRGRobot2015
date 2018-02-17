package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.RobotMap.Direction;
import org.usfirst.frc.team948.robot.commands.DeflectBin;
import org.usfirst.frc.team948.robot.commands.Delay;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.EjectTote;
import org.usfirst.frc.team948.robot.commands.LiftToHeight;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.commands.ResetSensors;
import org.usfirst.frc.team948.robot.commands.TurnAngle;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives the robot straight for a certain distance, turns 90 degrees clockwise,
 * and goes straight for a certain distance again.
 */
public class OutdatedAutonomousThreeTotes extends CommandGroup {	
	// TODO: Values TBD
	
	private final double DEFLECT_SPEED = 0.7;
	private final double DELAY_TO_CLEAR_WHEELS = 0.5;
	
	private  double driveSpeed = 0.7;
	private int position;
	
	public OutdatedAutonomousThreeTotes(int startingPosition, int totes, boolean[] bins) {
		position = startingPosition;
		
		addSequential (new ResetSensors());
		/*
		//addSequential(new LiftToHeight(ScissorLift.Level.Ground.voltage));
		for (int tote = 1; tote <= 1; tote++)
		{
			if (tote != 1)
			{
				addSequential(new UnpinchAndDrive(driveSpeed, 2.9));
			}
			System.out.println("Tote Number:" + tote);
			addSequential(new PinchAndAcquire());
			if(tote != totes){
				driveSpeed = (tote == 2) ? 0.5 : 0.55;
				addParallel(new UnpinchDelayDeflectBin(DELAY_TO_CLEAR_WHEELS, DEFLECT_SPEED, driveSpeed, bins[tote-1], tote));
			}
			addSequential(new LiftNextTote(tote));
			position++;
		}
		*/
		addSequential(new DriveStraightDistance(driveSpeed, 5.0));//testing delite lator
		//Turn to face the auto zone
		addSequential(new TurnAngle(-75, 0.4, 3.0));
		
		//Drive into the auto zone
		addSequential(new DriveStraightDistance(-driveSpeed, 8.0));
		
		addParallel(new Pinch(Position.Open));
		//Place stack of totes down
		//addSequential(new LiftToHeight(ScissorLift.Level.Ground));
		Command backAwayDriveCommand = new DriveStraightDistance(-driveSpeed, 2);
		addParallel(new EjectTote(backAwayDriveCommand));
		// Back away from stack
		addSequential(backAwayDriveCommand);
	}
}