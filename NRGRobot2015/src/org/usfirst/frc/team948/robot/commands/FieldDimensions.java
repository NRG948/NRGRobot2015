package org.usfirst.frc.team948.robot.commands;

public interface FieldDimensions {
	// All dimensions are in feet
	public static final double TOTE_TO_SCORING = 4.0;
	public static final double SCORING_WIDTH = (Math.cos(16 * Math.PI / 180) * 2 * 7.125 + 20) / 12;
	public static final double AUTOZONE_WIDTH = 6.5;
	public static final double BIN_TOTE_GROUP_WIDTH = 1.75;
	public static final double BIN_TOTE_GROUP_LENGTH = 4.0;
	public static final double FIELD_WIDTH = 27.0;
	public static final double BIN_TOTE_GROUP_TO_LANDMARK = 8 + 11 / 12.0;
}
