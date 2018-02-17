package org.usfirst.frc.team948.robot.utilities;

public class LogConsoleOutput {
	public enum LoggerLevel
	{
		FINE(0),
		INFO(1),
		WARNING(2),
		ERROR(3),
		NONE(4);
		
		public final int logLevel;

		LoggerLevel(int logLevel) {
			this.logLevel = logLevel;
		}
	}
	
	private static LoggerLevel lowestLoggerLevel;
	
	public static void setLowestLoggingLevel(LoggerLevel level)
	{
		lowestLoggerLevel = level;
	}
	
	public static void error(String fmt, Object... args)
	{
		if (LoggerLevel.ERROR.logLevel >= lowestLoggerLevel.logLevel)
		{
			TimeStamper.printStatementWithTime(String.format(fmt, args));
		}
	}
	
	public static void warning(String fmt, Object... args)
	{
		if (LoggerLevel.WARNING.logLevel >= lowestLoggerLevel.logLevel)
		{
			TimeStamper.printStatementWithTime(String.format(fmt, args));
		}
	}
	
	public static void info(String fmt, Object... args)
	{
		if (LoggerLevel.INFO.logLevel >= lowestLoggerLevel.logLevel)
		{
			TimeStamper.printStatementWithTime(String.format(fmt, args));
		}
	}
	
	public static void fine(String fmt, Object... args)
	{
		if (LoggerLevel.FINE.logLevel >= lowestLoggerLevel.logLevel)
		{
			TimeStamper.printStatementWithTime(String.format(fmt, args));
		}
	}
}
