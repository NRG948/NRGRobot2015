package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.commands.LiftToHeight;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.commands.QuickLiftDown;
import org.usfirst.frc.team948.robot.commands.QuickLiftUp;
import org.usfirst.frc.team948.robot.subsystems.Pincher;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift.Level;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Part of the autonomous sequence for lifting totes.
 */
public class LiftNextTote extends CommandGroup {
    
    public  LiftNextTote(int tote) {
       if (tote != 1) {
    	   addSequential(new QuickLiftDown(Level.Ground));
       }
       addSequential(new QuickLiftUp((tote == 3) ? Level.TwoInch : Level.OneTote));
    }
    @Override
    protected void end(){
    	super.end();
    	System.out.println("LIft Next Tote ended");
    }
}
