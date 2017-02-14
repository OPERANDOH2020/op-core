package eu.operando.core.watchdog;

import java.util.Vector;

public interface IWdConfiguration
{
	/**
	 * Get the scehdulers for all tasks to be registered for the watchdog.
	 */
	Vector<WdTaskScheduler> getWdTaskSchedulers();
}