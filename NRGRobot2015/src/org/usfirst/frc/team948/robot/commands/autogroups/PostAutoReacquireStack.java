package org.usfirst.frc.team948.robot.commands.autogroups;
import org.usfirst.frc.team948.robot.RoboConstants;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.LiftToHeight;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.commands.TurnAngle;
import org.usfirst.frc.team948.robot.commands.TurnToXY;
import org.usfirst.frc.team948.robot.subsystems.Pincher;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PostAutoReacquireStack extends CommandGroup {
    
	private static final double DRIVE_POWER = 0.7;
	private static final double TURN_ANGLE = -160;
	private static final double TURN_POWER = 0.8;
	
	public PostAutoReacquireStack(boolean qualifiers) {
		this(TURN_ANGLE, qualifiers);
		
	}
	
    public PostAutoReacquireStack(double turnAngle, boolean qualifiers) {
    	addParallel(new TurnAngle(6, 0.2, 2));
    	addSequential(new LiftToHeight(ScissorLift.Level.Ground));
        addSequential(new DriveStraightDistance(DRIVE_POWER, RoboConstants.BACK_AWAY_DISTANCE/2));
        addSequential(new PinchAndAcquire());
        addParallel(new Pinch(Pincher.Position.Open));
        addSequential(new LiftToHeight(ScissorLift.Level.TwoInch));
        addParallel(new Pinch(Pincher.Position.Close));
        if (qualifiers = true)
        {
        addSequential(new TurnToXY(TURN_POWER, 27.0, 13.5, 8.0));
        }
    }
}
