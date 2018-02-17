package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift.Level;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MatchPrep extends CommandGroup {
	
	public MatchPrep() {
		addSequential(new Pinch(Position.Open));
		addSequential(new LiftToHeight(Level.Ground));
		addSequential(new SealPincher());
	}
}
