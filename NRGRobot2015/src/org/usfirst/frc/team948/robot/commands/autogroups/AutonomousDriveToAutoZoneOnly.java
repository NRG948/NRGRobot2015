package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDriveToAutoZoneOnly extends CommandGroup {
	private final double DRIVE_SPEED = 0.5;
	private final double DRIVE_DISTANCE = 8;
	
	public  AutonomousDriveToAutoZoneOnly() {
    	addSequential(new DriveStraightDistance(DRIVE_SPEED, DRIVE_DISTANCE));
    }
}