package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class UnpinchAndDrive extends CommandGroup {
    
    public  UnpinchAndDrive(double power, double distance) {
    	addParallel(new Pinch(Position.Open));
    	addSequential(new DriveStraightDistance(0,power, distance));
    }
    @Override
	protected void end(){
    	super.end();
    	System.out.println("Unpinch and Drive Ended!");
    }
}
