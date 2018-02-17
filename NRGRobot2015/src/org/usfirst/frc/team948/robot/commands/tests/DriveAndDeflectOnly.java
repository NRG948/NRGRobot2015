package org.usfirst.frc.team948.robot.commands.tests;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.commands.DeflectBin;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.TurnAngle;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndDeflectOnly extends CommandGroup {
    
    public  DriveAndDeflectOnly() {
    	Command command = new DriveStraightDistance(0.3, 4);
        addParallel(new DeflectBin(RobotMap.Direction.Right, 1, command));
        addSequential(command);
        addSequential(new TurnAngle(90, 0.5));
    }
}
