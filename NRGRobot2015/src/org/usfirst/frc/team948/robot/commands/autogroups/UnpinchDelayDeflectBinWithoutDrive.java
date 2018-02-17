package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.RoboConstants;
import org.usfirst.frc.team948.robot.RobotMap.Direction;
import org.usfirst.frc.team948.robot.commands.CommandBase;
import org.usfirst.frc.team948.robot.commands.DeflectBin;
import org.usfirst.frc.team948.robot.commands.DelayForBinClear;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.subsystems.Pincher;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class UnpinchDelayDeflectBinWithoutDrive extends CommandGroup implements RoboConstants{
		
	public UnpinchDelayDeflectBinWithoutDrive(double deflectSpeed, double deflectTime, boolean bin, boolean deflectDirection, DelayAndDrive driveCommand, double fractionClosed){
		if(bin){
			addSequential(new Pinch(Pincher.Position.Open));
			addSequential(new DelayForBinClear(HEIGHT_TO_CLEAR_ACQUIRER));
			addSequential(new DeflectBin(deflectDirection ? Direction.Left : Direction.Right, deflectSpeed, driveCommand, 3.5, fractionClosed));
		}
		addSequential(new Pinch(Pincher.Position.Open));
	}
	public UnpinchDelayDeflectBinWithoutDrive(double deflectSpeed, double deflectTime, boolean bin, boolean deflectDirection, DriveStraightDistance driveCommand){
		if(bin){
			addSequential(new Pinch(Pincher.Position.Open));
			addSequential(new DelayForBinClear(HEIGHT_TO_CLEAR_ACQUIRER));
	//		addSequential(new DeflectBin(deflectDirection ? Direction.Left : Direction.Right, deflectSpeed, driveCommand, 3.0));
		}
		addSequential(new Pinch(Pincher.Position.Open));
	}
}
