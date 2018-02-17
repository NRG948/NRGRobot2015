package org.usfirst.frc.team948.robot.subsystems;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.commands.ManualPinch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pincher extends Subsystem{
	public enum Position { 
		Open, Close
	}
	
	public void setBoth(Position position)
	{
		setLeft(position);
		setRight(position);
	}
	
	public void setLeft(Position position) {
		RobotMap.leftAcquirerPiston
				.set((position == Position.Open) ? DoubleSolenoid.Value.kReverse
						: DoubleSolenoid.Value.kForward);
	}

	public void setRight(Position position) {
		RobotMap.rightAcquirerPiston
				.set((position == Position.Open) ? DoubleSolenoid.Value.kReverse
						: DoubleSolenoid.Value.kForward);
	}
	
	//Values may be reversed
	public void sealPincher() {
		RobotMap.pincherPressurizer.set(DoubleSolenoid.Value.kForward);
	}

	public void pressurizePincher() {
		RobotMap.pincherPressurizer.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualPinch());
	}
}