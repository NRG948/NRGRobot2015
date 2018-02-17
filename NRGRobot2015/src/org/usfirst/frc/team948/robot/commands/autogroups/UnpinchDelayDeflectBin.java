package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.RobotMap.Direction;
import org.usfirst.frc.team948.robot.commands.DeflectBin;
import org.usfirst.frc.team948.robot.commands.Delay;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.commands.WaitForToteClear;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class UnpinchDelayDeflectBin extends CommandGroup {
	public UnpinchDelayDeflectBin(double seconds, double deflectSpeed,
			double driveSpeed, boolean bin, int binNumber) {
		addSequential(new Pinch(Position.Open));
		double driveDistance = (binNumber == 1) ? 3.75 : 3.15;
		Command DriveCommand=new DriveStraightDistance(0,driveSpeed, driveDistance); 
		// addSequential(new WaitForToteClear());
	/*	if (bin && binNumber != 3) {
		//	addSequential(new Delay(seconds)); // TODO: Consider waiting until middle
											// break-beam untriggers
			//addParallel(new DeflectBin(Direction.Right, deflectSpeed, true));
		}*/
		if(binNumber != 3){
		addSequential(DriveCommand); 
		}
	}
	@Override
	protected void end(){
		super.end();
		System.out.println("Unpinch Delay and Deflect Bin Ended");
	}
}
