package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DelayForBinClear extends CommandBase{
	boolean goneDown = false;
	double pot;
	public DelayForBinClear(double i){
		pot = i;
	}
	@Override
	protected void initialize() {
		goneDown = false;
	}

	@Override
	protected void execute() {
		if(!goneDown &&  RobotMap.scissorLiftPotentiometer.get() < ScissorLift.Level.TwoInch.voltage) goneDown = true;
		if(!goneDown)
		{
			SmartDashboard.putString("Autonomous Failure", "Had not gone down");
		}
		else
		{
			SmartDashboard.putString("Autonomous Failure", "Had gone down");
		}
		
	}
	double previousValue = 0;
	@Override
	protected boolean isFinished() {
		//Practice Base: 0.505
		if (goneDown && RobotMap.scissorLiftPotentiometer.get() > (pot) && previousValue > (pot)){
			return true;
		}else{
			previousValue = RobotMap.scissorLiftPotentiometer.get();
			return false;
		}
	}

	@Override
	protected void end() {
		SmartDashboard.putString("Autonomous Failure", "Delay Finshed");
	}

	@Override
	protected void interrupted() {
		
	}
}
