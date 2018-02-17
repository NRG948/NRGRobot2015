package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.RobotMap.Direction;
import org.usfirst.frc.team948.robot.commands.CommandBase;
import org.usfirst.frc.team948.robot.commands.DeflectBin;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.DriveToXY;
import org.usfirst.frc.team948.robot.commands.LiftToHeight;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.commands.SetPosition;
import org.usfirst.frc.team948.robot.commands.TurnAngle;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift.Level;
import org.usfirst.frc.team948.robot.utilities.PreferenceKeys;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousGrabLandfillTotes extends CommandGroup {
	private final double DRIVE_SPEED = 0.5;
	private final double DRIVE_DISTANCE = 9;
	private final double DRIVE_DISTANCE_SHORT = 1.5;
	private final double DEFLECT_SPEED = 0.7;
	private final double AUTOZONE_START_X = 18.5;
	private final double AUTOZONE_START_Y = 0.5;
	private final double AUTOZONE_DESTINATION_X = 13.5;
	private final double AUTOZONE_DESTINATION_Y = 13.5;
	private final double TURN_TO_TOTE_ANGLE = 26.5;
	private final double TURN_TO_TOTE_POWER = 0.5;
	private final double TURN_TO_DESTINATION_ANGLE = -64.0;
	private final double TURN_TO_DESTINATION_POWER = 0.5;
	
	public AutonomousGrabLandfillTotes() {
		// set position as (18.5, .5) and start heading is 0
		addSequential(new SetPosition(CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Start_X, AUTOZONE_START_X),
				CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Start_Y, AUTOZONE_START_Y)));
		
		// turn to heading 26 degrees
		addSequential(new TurnAngle(CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Turn_To_Tote_Angle, TURN_TO_TOTE_ANGLE),
				CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Turn_To_Tote_Power, TURN_TO_TOTE_POWER)));
		
		// close pinchers and drive to tote
		addParallel(new Pinch(Position.Close));
		addSequential(new DriveStraightDistance(CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Drive_Speed, DRIVE_SPEED),
				CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Drive_Distance, DRIVE_DISTANCE)));
		
		// deflect tote into position
		addSequential(new DeflectBin(Direction.Left,
				CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Deflect_Speed, DEFLECT_SPEED)));
		
		// open pinchers
		addSequential(new Pinch(Position.Open));
		
		// drive tiny amount up to tote
		addSequential(new DriveStraightDistance(CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Drive_Speed, DRIVE_SPEED),
				CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Drive_Distance_Short, DRIVE_DISTANCE_SHORT)));
		
		// acquire tote
		addSequential(new PinchAndAcquire());
		
		// lift tote and drive to auto zone
		addParallel(new LiftToHeight(Level.TwoInch));
		addSequential(new TurnAngle(CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Turn_To_Destination_Angle, TURN_TO_DESTINATION_ANGLE),
				CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Turn_To_Destination_Power, TURN_TO_DESTINATION_POWER)));
		
		
		addSequential(new DriveToXY(CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Destination_X, AUTOZONE_DESTINATION_X),
				CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Destination_Y, AUTOZONE_DESTINATION_Y),
				CommandBase.preferences.getDouble(PreferenceKeys.Grab_Landfill_Drive_Speed, DRIVE_SPEED)));
	}
}