package org.usfirst.frc.team948.robot.commands;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StackToteAndLiftStack extends CommandGroup {
	
	public StackToteAndLiftStack()
	{
		//add sequential for set height to one tote
		addSequential(new LiftToHeight(ScissorLift.Level.OneTote));
		//add sequential aquire new tote
		addSequential(new AcquireFastOrSlow(true));
		//add sequential to set tote on new tote
		addSequential(new LiftToHeight(ScissorLift.Level.Ground));
		//add sequential to lift tote to two inch
		addSequential(new LiftToHeight(ScissorLift.Level.TwoInch));
	}
	
	
}
