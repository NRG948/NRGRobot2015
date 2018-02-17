package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.EncoderPIDSource;
import org.usfirst.frc.team948.robot.commands.DelayForBinClear;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DelayAndDrive extends CommandGroup{
	public EncoderPIDSource encoderPIDSource;
	public DelayAndDrive(double heading, double speed, double distance, double potValueToStart){
		addSequential(new DelayForBinClear(potValueToStart));
		DriveStraightDistance driveDistance = new DriveStraightDistance(heading,speed,distance);
		encoderPIDSource = driveDistance.encoderPIDSource;
		addSequential(driveDistance);
	}
}
