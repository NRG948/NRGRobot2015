package org.usfirst.frc.team948.robot.utilities;

import org.usfirst.frc.team948.robot.DS2015;

import edu.wpi.first.wpilibj.buttons.Button;

public class DSButton extends Button {
	private int buttonPort;
	private boolean invert;
	public DSButton(int buttonPort, boolean invert) {
		this.buttonPort = buttonPort;
		this.invert = invert;
	}

	@Override
	public boolean get() {
		return invert? !DS2015.launchPad.getRawButton(buttonPort): DS2015.launchPad.getRawButton(buttonPort);
	}

}
