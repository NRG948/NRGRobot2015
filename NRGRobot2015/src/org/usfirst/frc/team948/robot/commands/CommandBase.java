package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.DS2015;
import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.Acquirer;
import org.usfirst.frc.team948.robot.subsystems.Drive;
import org.usfirst.frc.team948.robot.subsystems.Pincher;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;
import org.usfirst.frc.team948.robot.utilities.PositionTracker;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system.
 * 
 */
public abstract class CommandBase extends Command {
	
	public static final DriverStation driverStation = DriverStation
			.getInstance();
	public static DS2015 ds = new DS2015();
	public static PositionTracker positionTracker = new PositionTracker(RobotMap.driveGyro, RobotMap.leftEncoder, RobotMap.rightEncoder);
	public static Drive drive = new Drive();
	public static Acquirer acquirer = new Acquirer();
	public static Pincher pincher = new Pincher();
	public static ScissorLift scissorLift = new ScissorLift();

	public static Preferences preferences = Preferences.getInstance();
}