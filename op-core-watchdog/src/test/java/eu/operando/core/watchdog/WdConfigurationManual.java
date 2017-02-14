package eu.operando.core.watchdog;

import java.util.Vector;

public class WdConfigurationManual implements IWdConfiguration
{
	private Vector<WdTaskScheduler> taskSchedulers;

	public WdConfigurationManual(Vector<WdTaskScheduler> taskSchedulers)
	{
		this.taskSchedulers = taskSchedulers;
	}

	@Override
	public Vector<WdTaskScheduler> getWdTaskSchedulers()
	{
		return taskSchedulers;
	}
}
