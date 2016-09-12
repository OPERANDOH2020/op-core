package eu.operando.core.watchdog;

import java.util.Vector;

public class WdApp
{
	// Location of properties file.
	private static final String PROPERTIES_FILE_WATCHDOG = "config.properties";

	public static void main(String[] args)
	{
		IWdConfiguration configuration = new WdConfigurationFromPropertiesFile(PROPERTIES_FILE_WATCHDOG);
		WdApp app = new WdApp(configuration);
		app.startAllTasks();
	}
	
	private Vector<WdTaskScheduler> wdTaskSchedulers = new Vector<WdTaskScheduler>();

	public WdApp(IWdConfiguration configuration)
	{
		this.wdTaskSchedulers = configuration.getWdTaskSchedulers();
	}
	
	public void startAllTasks()
	{
		for (WdTaskScheduler scheduler : wdTaskSchedulers)
		{
			scheduler.startTask();
		}
	}
}
