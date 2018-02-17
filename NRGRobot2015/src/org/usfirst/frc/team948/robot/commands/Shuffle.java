package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.Drive;
import org.usfirst.frc.team948.robot.utilities.PreferenceKeys;

import edu.wpi.first.wpilibj.command.CommandGroup;



public class Shuffle extends CommandGroup {
	
	private final double SHUFFLE_DISTANCE = 3;
	private final double SHUFFLE_POWER = 0.5;
	private final double SHUFFLE_TURN_TIME = 0.25;
	private final double SHUFFLE_FORWARD_TIME = 0.4;
	private final double SHUFFLE_POWER_DIVISOR = 4;
	
	public Shuffle(RobotMap.Direction direction)
	{
		double power = CommandBase.preferences.getDouble(PreferenceKeys.Shuffle_Power, SHUFFLE_POWER);
		//double fractionPower = power/CommandBase.preferences.getDouble(PreferenceKeys.Shuffle_Power_Divisor, SHUFFLE_POWER_DIVISOR);
		//double time = CommandBase.preferences.getDouble(PreferenceKeys.Shuffle_Turn_Time, SHUFFLE_TURN_TIME);
		double currentHeading = RobotMap.driveGyro.getAngle();
		double distance = CommandBase.preferences.getDouble(PreferenceKeys.Distance, SHUFFLE_DISTANCE);
		
		if (direction.equals(RobotMap.Direction.Left))
		{
			addSequential(new DriveDistance(RobotMap.Direction.Right, distance, -power));
			addSequential(new Delay(0.2));
			addSequential(new DriveDistance(RobotMap.Direction.Left, distance, -power));
			addSequential(new Delay(0.2));
			addSequential(new DriveStraightDistance(currentHeading, power, distance));
		}		
		else if (direction.equals(RobotMap.Direction.Right))
		{
			addSequential(new DriveDistance(RobotMap.Direction.Left, distance, -power));
			addSequential(new Delay(0.2));
			addSequential(new DriveDistance(RobotMap.Direction.Right, distance, -power));
			addSequential(new Delay(0.2));
			addSequential(new DriveStraightDistance(currentHeading, power, distance));
		}
		
		
		
	}
}
