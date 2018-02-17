package org.usfirst.frc.team948.robot;

import org.usfirst.frc.team948.robot.commands.DeflectBin;
import org.usfirst.frc.team948.robot.commands.Delay;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.EjectTote;
import org.usfirst.frc.team948.robot.commands.InterruptAll;
import org.usfirst.frc.team948.robot.commands.IncrementalQuickLift;
import org.usfirst.frc.team948.robot.commands.LiftToHeight;
import org.usfirst.frc.team948.robot.commands.MatchPrep;
import org.usfirst.frc.team948.robot.commands.PressurizePincher;
import org.usfirst.frc.team948.robot.commands.QuickLiftDown;
import org.usfirst.frc.team948.robot.commands.SealPincher;
import org.usfirst.frc.team948.robot.commands.IncrementalLift;
import org.usfirst.frc.team948.robot.commands.ManualAcquireBase;
import org.usfirst.frc.team948.robot.commands.ManualAcquireBin;
import org.usfirst.frc.team948.robot.commands.ManualAcquireFirstTote;
import org.usfirst.frc.team948.robot.commands.ManualDrive;
import org.usfirst.frc.team948.robot.commands.ManualDriveStraight;
import org.usfirst.frc.team948.robot.commands.RawTankDrive;
import org.usfirst.frc.team948.robot.commands.ResetSensors;
import org.usfirst.frc.team948.robot.commands.Shuffle;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift.Level;
import org.usfirst.frc.team948.robot.utilities.DSButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * Represents the driver station made for 2015 Recycle Rush.
 * 
 */
public class DS2015 {
	public static final Joystick leftJoystick = new Joystick(1);
	public static final Joystick rightJoystick = new Joystick(2);
	public static final Joystick xboxController = new Joystick(3);
	public static final Joystick launchPad = new Joystick(0);
	public static final Button resetSensorsButton = new JoystickButton(leftJoystick, 6);
	public static final Button unsealArms = new JoystickButton(leftJoystick, 7);
	public static final Button matchPrepButton = new JoystickButton(leftJoystick, 8);
	
	// driver controls
	public static final Button driveStraightButton = new JoystickButton(rightJoystick, 1);
	
	public static final Button sealPincherButton = new JoystickButton(leftJoystick, 10);
	public static final Button pressurizePincherButton = new JoystickButton(leftJoystick, 11);
	public static final Button shuffleLeftButton = new JoystickButton(rightJoystick, 4);
	public static final Button shuffleRightButton = new JoystickButton(rightJoystick, 5);
	public static final Button inchForwardButton = new JoystickButton(rightJoystick, 3);
	public static final Button inchBackButton = new JoystickButton(rightJoystick, 2);
	public static final Button rotateClockWiseButton = new JoystickButton(leftJoystick, 5);
	public static final Button rotateCounterClockWiseButton = new JoystickButton(leftJoystick, 4);
	//public static final Button oneToteHeightButton = new JoystickButton(xboController, )
	public static final Button xboxAButton = new JoystickButton(xboxController, 1);
	public static final Button xboxBButton = new JoystickButton(xboxController, 2);
	public static final Button xboxXButton = new JoystickButton(xboxController, 3); // unassigned
	public static final Button xboxYButton = new JoystickButton(xboxController, 4);
	public static final Button xboxLBumper = new JoystickButton(xboxController, 5);
	public static final Button xboxRBumper = new JoystickButton(xboxController, 6);
	public static final Button xboxBackButton  = new JoystickButton(xboxController, 7); // unassigned
	public static final Button xboxStartButton = new JoystickButton(xboxController, 8); 
	public static final Button xboxLThumbstickButton = new JoystickButton(xboxController, 9);  // L joy click
	public static final Button xboxRThumbstickButton = new JoystickButton(xboxController, 10); // R joy click
	public static final Button interruptSubsystemButton = new JoystickButton(rightJoystick, 10);

	private final double DRIVE_DEADZONE = 0.05;
	private final double MANIPULATOR_DEADZONE = 0.11;
	private static boolean quick = false;	
	
	// Autonomous options
	public static final DSButton isAutoEnabled = new DSButton(10, true);
	public static final DSButton autoLift = new DSButton(11, true);
	public static final DSButton autoThreeTotes = new DSButton(12, false);
	public static final DSButton autoThreeTotesCoopertition = new DSButton(14, false);
	public static final DSButton autoThreeTotesElimination = new DSButton(15, false);
	public static final DSButton driveToAutoZone = new DSButton(16, false);

	public DS2015() {
		// resetSensorsButton.whenPressed(new ResetSensors());
		// driveStraightButton.whileHeld(new OperatorDriveStraight());
	}
	
	public static void buttonInit(){
		resetSensorsButton.whenPressed(new ResetSensors());
		matchPrepButton.whenPressed(new MatchPrep());
	  	driveStraightButton.whenPressed(new ManualDriveStraight());
	  	driveStraightButton.whenReleased(new ManualDrive());
	  	interruptSubsystemButton.whenPressed(new InterruptAll());
//	  	sealPincherButton.whenPressed(new SealPincher());
//	  	pressurizePincherButton.whenPressed(new PressurizePincher());
//	  	shuffleLeftButton.whenPressed(new Shuffle(RobotMap.Direction.Left));
//	  	shuffleRightButton.whenPressed(new Shuffle(RobotMap.Direction.Right));
	  	inchForwardButton.whenPressed(new DriveStraightDistance(RobotMap.driveGyro.getAngle(), 1, 0.5, 0.1));
	  	inchBackButton.whenPressed(new DriveStraightDistance(RobotMap.driveGyro.getAngle(), -1, 0.5, 0.1));
	  	//inchBackButton.whenPressed(new Delay(2.0));
	  	rotateClockWiseButton.whenPressed(new RawTankDrive(0.45, -0.45));
	  	rotateClockWiseButton.whenReleased(new ManualDrive());
	  	rotateCounterClockWiseButton.whenPressed(new RawTankDrive(-0.45, 0.45));
	  	rotateCounterClockWiseButton.whenReleased(new ManualDrive());
	  	// xboxAButton.whenPressed(new ManualAcquireFirstTote());
	  	// xboxBButton.whenPressed(new ManualAcquireBin());
	  	// xboxBButton.whenPressed(new ManualAcquireBin());
	  	xboxAButton.whileHeld(new QuickLiftDown(Level.Ground));
	  	xboxXButton.whenPressed(new LiftToHeight(Level.OneTote));
	  	xboxYButton.whenPressed(new EjectTote());
	  	xboxYButton.whenReleased(new ManualAcquireBase(true));
	  	if(quick){
	  		xboxLBumper.whenPressed(new IncrementalQuickLift(false, 0.052));
	  		xboxRBumper.whenPressed(new IncrementalQuickLift(true, 0.03));
	  	}else{
	  		xboxLBumper.whenPressed(new IncrementalLift(false));
	  		xboxRBumper.whenPressed(new IncrementalLift(true));
	  	}
	  	xboxLThumbstickButton.whenPressed(new ManualAcquireBase(false, 0.5));
	  	xboxLThumbstickButton.whenReleased(new ManualAcquireBase(true));
	  	xboxStartButton.whenPressed(new InterruptAll() );
	  	unsealArms.whenPressed(new PressurizePincher());
	}
	
	public double getLeftJSY() {
		return getYAxis(leftJoystick);
	}

	public double getRightJSY() {
		return getYAxis(rightJoystick);
	}
	
	public double getYAxis(Joystick joy) {
		// pushing forward on the joystick gives a negative y value, so we invert it
		double value = -joy.getY();
		if (Math.abs(value) <= DRIVE_DEADZONE) {
			return 0;
		} else {
			return value;
		}
	}

	public double getXboxLX() {
		return getXboxAxis(0);
	}

	public double getXboxLY() {
		return getXboxAxis(1)/1.33;
	}

	public double getXboxLT() {
		return getXboxAxis(2);
	}

	public double getXboxRT() {
		return getXboxAxis(3);
	}
	
	
	public double getXboxRX() {
		return getXboxAxis(4);
	}

	public double getXboxRY() {
		return getXboxAxis(5)/1.33;
	}
	
	public double getXboxAxis(int axis) {
		double value = xboxController.getRawAxis(axis);
		if (Math.abs(value) <= MANIPULATOR_DEADZONE){
			return 0;
		} else {
			return value;
		}
	}
}