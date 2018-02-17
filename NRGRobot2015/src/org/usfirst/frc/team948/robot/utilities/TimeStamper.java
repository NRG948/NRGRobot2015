package org.usfirst.frc.team948.robot.utilities;

public class TimeStamper {
	private static long startTime;

	public static void setStartTime()
	{
		startTime = System.currentTimeMillis();
	}
	
	public static void printStatementWithTime(String printStatement)
	{
		System.out.printf("%d.%d : %s", ((System.currentTimeMillis() - startTime) - ((System.currentTimeMillis() - startTime) % 1000)) / 1000,
				(System.currentTimeMillis() - startTime) % 1000, printStatement);
	}
}
