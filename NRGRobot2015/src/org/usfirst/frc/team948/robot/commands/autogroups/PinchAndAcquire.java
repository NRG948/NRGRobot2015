package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.commands.AcquireFastOrSlow;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PinchAndAcquire extends CommandGroup {
    
    public  PinchAndAcquire() {
    	addParallel(new Pinch(Position.Close));
    	addSequential(new AcquireFastOrSlow(true));
    }
    @Override
    protected void end(){
    	super.end();
    	System.out.println("Pinch and Acquire Ended");
    }
}
