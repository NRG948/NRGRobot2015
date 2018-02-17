package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.commands.autogroups.LiftNextTote;
import org.usfirst.frc.team948.robot.commands.autogroups.PinchAndAcquire;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousOneToteBulldozeBin extends CommandGroup implements FieldDimensions {
	private final double DRIVE_SPEED = 0.5;

	public AutonomousOneToteBulldozeBin() {

		addSequential(new PinchAndAcquire());

		addSequential(new LiftNextTote(1)); // 1 for first tote

		// Drive to the landmark in the auto zone
		double x = BIN_TOTE_GROUP_WIDTH/2 + BIN_TOTE_GROUP_TO_LANDMARK;
		double y = x; // REVIEW(pauldavis): wha?
		
		addSequential(new DriveToXY(x, y, 1));
		
		// Place bin down 
		addParallel(new Pinch(Position.Open));
		
		addSequential(new DriveStraightDistance(-DRIVE_SPEED, 2.5));
		
		addSequential(new LiftToHeight(ScissorLift.Level.Ground));
		
		addSequential(new EjectTote());

		// Back away from stack
		addSequential(new DriveStraightDistance(-DRIVE_SPEED, 2.5));
	}
}
