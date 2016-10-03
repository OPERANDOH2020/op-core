package eu.operando.core.watchdog;

import java.util.Timer;

public class WdTaskScheduler
{
	private Timer timer = new Timer();
	private WdTask task = null;
	private int periodMillis = -1;

	public WdTaskScheduler(WdTask task, int periodMillis)
	{
		this.task = task;
		this.periodMillis  = periodMillis;
	}

	public void startTask()
	{
		timer.scheduleAtFixedRate(task, 0, periodMillis);
	}
}
